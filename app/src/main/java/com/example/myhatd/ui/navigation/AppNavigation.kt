package com.example.myhatd.ui.navigation

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myhatd.data.network.RetrofitClient
import com.example.myhatd.data.storage.TokenManager
import com.example.myhatd.repository.AuthRepository
import com.example.myhatd.repository.DriverRepository
import com.example.myhatd.ui.driver.DangKyHatdScreen
import com.example.myhatd.ui.driver.HoSoDriverScreen
import com.example.myhatd.ui.driver.HomeDriverScreen
import com.example.myhatd.ui.home.HomeUserScreen
import com.example.myhatd.ui.otp.*
import com.example.myhatd.ui.otp.intro.IntroScreen
import com.example.myhatd.ui.customer.*
import com.example.myhatd.viewmodel.AuthViewModel
import com.example.myhatd.viewmodel.ChuyenDiViewModel
import com.example.myhatd.viewmodel.DriverViewModel
import com.example.myhatd.viewmodel.MapViewModel
import com.example.myhatd.viewmodel.UserViewModel
import com.example.myhatd.viewmodel.MainViewModel
import com.google.firebase.auth.FirebaseAuth
import com.example.myhatd.ui.driver.DriverTaoYeuCauChuyenDiScreen
import com.example.myhatd.ui.driver.DriverHenGioScreen

// LỖI ĐÃ ĐƯỢC XÓA: private val Boolean.value: Boolean

// Hàm mở rộng để lấy Activity từ Context
fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val activity = context.findActivity() ?: return

    // --- KHỞI TẠO REPOSITORY VÀ MANAGER CHUNG VÀ NHỚ (REMEMBER) ---
    val apiService = RetrofitClient.apiService

    val authRepository = remember { AuthRepository(apiService = apiService) }
    val driverRepository = remember { DriverRepository(apiService = apiService) }

    // 1. KHỞI TẠO TOKEN MANAGER
    val tokenManager = remember {
        TokenManager(context = context)
    }

    // 2. KHỞI TẠO VÀ CHẠY MAIN VIEWMODEL ĐỂ KIỂM TRA PHIÊN
    val mainViewModel: MainViewModel = viewModel(
        factory = MainViewModel.Factory(tokenManager = tokenManager)
    )
    // ✅ Dùng 'by' để truy cập giá trị Boolean trực tiếp (isLoggedIn là Boolean)
    val isLoggedIn by mainViewModel.isUserLoggedIn

    // 3. KHỞI TẠO AUTH VIEWMODEL (TRUYỀN TOKEN MANAGER)
    val authViewModel: AuthViewModel = viewModel(
        factory = AuthViewModel.Factory(
            activity = activity,
            authRepository = authRepository,
            tokenManager = tokenManager
        )
    )

    // State của AuthViewModel
    val authState by authViewModel.state.collectAsStateWithLifecycle()

    // 4. ✅ SỬA LỖI TRUY CẬP: Dùng isLoggedIn trực tiếp
    val startDestination = if (isLoggedIn) {
        NavigationRoutes.HOME
    } else {
        NavigationRoutes.GIOI_THIEU
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(NavigationRoutes.PHONE_AUTH) {
            PhoneAuthScreen(navController = navController, viewModel = authViewModel)
        }
        composable(NavigationRoutes.VERIFY_OTP) {
            VerifyOtpScreen(navController = navController, viewModel = authViewModel)
        }
        composable(NavigationRoutes.THONG_TIN_USER) {
            xacnhanotpScreen(navController = navController, viewModel = authViewModel)
        }
        composable(NavigationRoutes.HOME) {
            val mapViewModel: MapViewModel = viewModel()
            HomeUserScreen(
                navController = navController,
                mapViewModel = mapViewModel
            )
        }

        composable(NavigationRoutes.TAO_YEU_CAU_CHUYEN_DI) {
            val chuyenDiViewModel: ChuyenDiViewModel = viewModel()
            val userViewModel: UserViewModel = viewModel<UserViewModel>(
                factory = UserViewModel.Factory(authRepository)
            )
            val phoneNumber = FirebaseAuth.getInstance().currentUser?.phoneNumber ?: ""
            LaunchedEffect(phoneNumber) {
                userViewModel.loadUser(phoneNumber)
            }

            TaoYeuCauChuyenDiScreen(
                navController = navController,
                chuyenDiViewModel = chuyenDiViewModel,
                userViewModel = userViewModel,
            )
        }

        composable(NavigationRoutes.DRIVER_TAO_YEU_CAU_CHUYEN_DI) {
            val chuyenDiViewModel: ChuyenDiViewModel = viewModel()
            val userViewModel: UserViewModel = viewModel<UserViewModel>(
                factory = UserViewModel.Factory(authRepository)
            )
            val phoneNumber = FirebaseAuth.getInstance().currentUser?.phoneNumber ?: ""
            LaunchedEffect(phoneNumber) {
                userViewModel.loadUser(phoneNumber)
            }

            DriverTaoYeuCauChuyenDiScreen(
                navController = navController,
                chuyenDiViewModel = chuyenDiViewModel,
                userViewModel = userViewModel,
            )
        }

        // --- ROUTE MÀN HÌNH HẸN GIỜ (DRIVER) ---
        composable(
            route = "driver_hen_gio/{diemDi}/{diemDen}/{viDo}/{kinhDo}",
            arguments = listOf(
                navArgument("diemDi") { type = NavType.StringType },
                navArgument("diemDen") { type = NavType.StringType },
                navArgument("viDo") { type = NavType.FloatType },
                navArgument("kinhDo") { type = NavType.FloatType }
            )
        ) { backStackEntry ->
            val diemDiParam = backStackEntry.arguments?.getString("diemDi") ?: ""
            val diemDenParam = backStackEntry.arguments?.getString("diemDen") ?: ""
            val viDoParam = backStackEntry.arguments?.getFloat("viDo")?.toDouble() ?: 0.0
            val kinhDoParam = backStackEntry.arguments?.getFloat("kinhDo")?.toDouble() ?: 0.0

            val chuyenDiViewModel: ChuyenDiViewModel = viewModel()
            val userViewModel: UserViewModel = viewModel<UserViewModel>(
                factory = UserViewModel.Factory(authRepository)
            )

            val phoneNumber = FirebaseAuth.getInstance().currentUser?.phoneNumber ?: ""
            LaunchedEffect(phoneNumber) {
                userViewModel.loadUser(phoneNumber)
            }

            DriverHenGioScreen(
                navController = navController,
                chuyenDiViewModel = chuyenDiViewModel,
                userViewModel = userViewModel,
                diemDi = diemDiParam,
                diemDen = diemDenParam,
                viDo = viDoParam,
                kinhDo = kinhDoParam
            )
        }
        // --- KẾT THÚC ROUTE MÀN HÌNH HẸN GIỜ (DRIVER) ---


        composable(NavigationRoutes.GIOI_THIEU) {
            GioiThieuScreen(navController = navController)
        }
        composable(NavigationRoutes.INTRO) {
            IntroScreen(navController = navController)
        }
        composable(NavigationRoutes.THONG_BAO_USER) {
            ThongBaoScreen(navController = navController)
        }
        composable(NavigationRoutes.HEN_GIO_USER) {
            HenGioScreen(navController = navController)
        }
        composable(NavigationRoutes.HOME_DRIVER) {
            HomeDriverScreen(navController = navController)
        }

        composable(NavigationRoutes.DANG_KY_DRIVER) {
            val phoneNumber = authState.phoneNumber
            val driverViewModel: DriverViewModel = viewModel(
                factory = DriverViewModel.Factory(driverRepository = driverRepository)
            )

            DangKyHatdScreen(
                navController = navController,
                phoneNumber = phoneNumber,
                driverViewModel = driverViewModel
            )
        }

        composable(NavigationRoutes.HO_SO_USER) {
            val userViewModel: UserViewModel = viewModel(
                factory = UserViewModel.Factory(authRepository)
            )
            val phoneNumber = FirebaseAuth.getInstance().currentUser?.phoneNumber ?: ""

            LaunchedEffect(phoneNumber) {
                userViewModel.loadUser(phoneNumber)
            }

            HoSoUserScreen(
                navController = navController,
                viewModel = userViewModel,
                phoneNumber = phoneNumber
            )
        }

        composable(NavigationRoutes.HO_SO_DRIVER) {
            val phoneNumber = FirebaseAuth.getInstance().currentUser?.phoneNumber ?: ""
            val userViewModel: UserViewModel = viewModel(
                factory = UserViewModel.Factory(authRepository)
            )
            val driverViewModel: DriverViewModel = viewModel(
                factory = DriverViewModel.Factory(driverRepository)
            )

            LaunchedEffect(phoneNumber) {
                if (phoneNumber.isNotEmpty()) {
                    userViewModel.loadUser(phoneNumber)
                    driverViewModel.fetchDriver(phoneNumber)
                }
            }

            HoSoDriverScreen(
                navController = navController,
                driverViewModel = driverViewModel,
                userViewModel = userViewModel
            )
        }
    }

    // --- LOGIC ĐIỀU HƯỚNG TẬP TRUNG SAU KHI XÁC THỰC THÀNH CÔNG ---
    LaunchedEffect(authState.isOtpSent, authState.isAuthenticated, authState.isInfoSaved) {
        when {
            // Đã lưu thông tin user và Token -> Về màn hình chính (HOME)
            authState.isInfoSaved -> {
                navController.navigate(NavigationRoutes.HOME) {
                    popUpTo(NavigationRoutes.PHONE_AUTH) { inclusive = true }
                }
            }
            // Đã xác thực Firebase nhưng chưa lưu thông tin -> Điền thông tin (THONG_TIN_USER)
            authState.isAuthenticated -> {
                navController.navigate(NavigationRoutes.THONG_TIN_USER) {
                    popUpTo(NavigationRoutes.PHONE_AUTH) { inclusive = true }
                }
            }
            // Mã OTP đã được gửi -> Xác minh OTP (VERIFY_OTP)
            authState.isOtpSent -> {
                val currentRoute = navController.currentDestination?.route
                if (currentRoute != NavigationRoutes.VERIFY_OTP) {
                    navController.navigate(NavigationRoutes.VERIFY_OTP)
                }
            }
        }
    }
}