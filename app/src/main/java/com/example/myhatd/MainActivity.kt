////// File: MainActivity.kt (ĐÃ CẬP NHẬT)
////
////package com.example.myhatd
////
////import android.os.Bundle
////import androidx.activity.ComponentActivity
////import androidx.activity.compose.setContent
////import androidx.compose.material3.MaterialTheme
////import androidx.compose.material3.Surface
////import androidx.compose.ui.Modifier
////import androidx.lifecycle.viewmodel.compose.viewModel
////import com.example.myhatd.ui.otp.PhoneAuthScreen
////import com.example.myhatd.ui.otp.VerifyOtpScreen
////import com.example.myhatd.ui.theme.MyHatdTheme
////import com.example.myhatd.ui.otp.findActivity
////import com.example.myhatd.viewmodel.AuthViewModel
////import com.example.myhatd.ui.MapScreen
////
////class MainActivity : ComponentActivity() {
////    override fun onCreate(savedInstanceState: Bundle?) {
////        super.onCreate(savedInstanceState)
////        setContent {
////            MyHatdTheme {
////                Surface(
////                    modifier = Modifier,
////                    color = MaterialTheme.colorScheme.background
////                ) {
////                    // Lấy Activity an toàn (Dùng findActivity() từ Utils.kt)
////                    val activity = this.findActivity() ?: return@Surface
////
////                    // Khởi tạo ViewModel (Dùng Factory)
////                    val viewModel: AuthViewModel = viewModel(factory = AuthViewModel.Factory(activity))
////                    val state = viewModel.state
////
////                    // Logic điều hướng dựa trên trạng thái (Navigation Logic)
////                    if (state.isAuthenticated) {
////                        // Đăng nhập thành công
////                        MapScreen()
////                    } else if (state.isOtpSent) {
////                        // Mã đã gửi, chuyển sang màn hình nhập OTP
////                        VerifyOtpScreen(viewModel)
////                    } else {
////                        // Mặc định: Màn hình nhập số điện thoại
////                        PhoneAuthScreen()
////                    }
////                }
////            }
////        }
////    }
////}
//
//
//// File: MainActivity.kt (KẾT HỢP — GIỮ NGUYÊN CODE FILE 1, THÊM NAVIGATION FILE 2)
//
//package com.example.myhatd
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.runtime.SideEffect
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.core.view.WindowCompat
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.example.myhatd.ui.otp.PhoneAuthScreen
//import com.example.myhatd.ui.otp.VerifyOtpScreen
//import com.example.myhatd.ui.theme.MyHatdTheme
//import com.example.myhatd.ui.otp.findActivity
//import com.example.myhatd.viewmodel.AuthViewModel
//import com.example.myhatd.ui.MapScreen
//import com.google.accompanist.systemuicontroller.rememberSystemUiController
//import com.example.myhatd.ui.home_user.HomeUserScreen
//
//// ================== IMPORT CÁC MÀN HÌNH TỪ FILE 2 ==================
////import com.example.hatd.ui.auth.gioithieu.GioiThieuScreen
////import com.example.hatd.ui.auth.intro.IntroScreen
////import com.example.hatd.ui.auth.otp.OtpScreen
////import com.example.hatd.ui.auth.singup.SingupScreen
////import com.example.hatd.ui.home.home_user.HomeUserScreen
////import com.example.hatd.ui.user.HenGio.HenGioScreen
////import com.example.hatd.ui.user.TaoYeuCauChuyenDiScreen.TaoYeuCauChuyenDiScreen
////import com.example.hatd.ui.user.DanhGiaDriver.DanhGiaDriverScreen
////import com.example.hatd.ui.user.ThanhToan.ThanhToanScreen
////import com.example.hatd.ui.user.ChiTietLichSuChuyenDi.ChiTietLichSuChuyenDiScreen
////import com.example.hatd.ui.user.ChiTietChuyenDi.ChiTietChuyenDiScreen
////import com.example.hatd.ui.user.XacNhanDiemDon.XacNhanDiemDonScreen
////import com.example.hatd.ui.user.TheoDoiLoTrinh.TheoDoiLoTrinhScreen
////import com.example.hatd.ui.user.Call.CallScreen
////import com.example.hatd.ui.user.HoSoUser.HoSoUserScreen
////import com.example.hatd.ui.user.ThongBao.ThongBaoScreen
////import com.example.hatd.ui.user.GhiChu.GhiChuScreen
////import com.example.hatd.ui.user.LichSuChuyenDi.LichSuChuyenDiScreen
////import com.example.hatd.ui.driver.CallDriver
////import com.example.hatd.ui.driver.HoSoDriver.HoSoDriverScreen
////import com.example.hatd.ui.driver.TaoChuyenDi.TaoChuyenDiScreen
////import com.example.hatd.ui.driver.XacNhanGhep.XacNhanGhepScreen
////import com.example.hatd.ui.driver.TheoDoiLoTrinhChungUser.TheoDoiLoTrinhChungUserScreen
////import com.example.hatd.ui.driver.XacNhanHoanThanh.XacNhanHoanThanhScreen
//
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        WindowCompat.setDecorFitsSystemWindows(window, false)
//
//        setContent {
//            val systemUiController = rememberSystemUiController()
//            SideEffect {
//                systemUiController.setSystemBarsColor(
//                    color = Color.White,
//                    darkIcons = true
//                )
//            }
//
//            MyHatdTheme {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    // Giữ nguyên logic gốc của FILE 1
//                    val activity = this.findActivity() ?: return@Surface
//                    val viewModel: AuthViewModel =
//                        viewModel(factory = AuthViewModel.Factory(activity))
//                    val state = viewModel.state
//
//                    // Navigation controller (được thêm từ file 2)
//                    val navController = rememberNavController()
//
//                    // Điều hướng giữ nguyên logic FILE 1
//                    if (state.isAuthenticated) {
//                        HomeUserScreen() // Giữ nguyên
//                    } else if (state.isOtpSent) {
//                        VerifyOtpScreen(viewModel) // Giữ nguyên
//                    } else {
//                        PhoneAuthScreen() // Giữ nguyên
//                    }
//                }
//            }
//        }
//    }
//}
//
////                    // ================== BỔ SUNG NAVIGATION FILE 2 ==================
////                    NavHost(
////                        navController = navController,
////                        startDestination = "intro"
////                    ) {
////                        composable("gioithieu") { GioiThieuScreen(navController) }
////                        composable("intro") { IntroScreen(navController) }
////                        composable("singup") { SingupScreen(navController) }
////                        composable("otp") { OtpScreen() }
////                        composable("HenGio") { HenGioScreen(navController) }
////                        composable("home_user") { HomeUserScreen() }
////                        composable("TaoYeuCauChuyenDi") { TaoYeuCauChuyenDiScreen() }
////                        composable("DanhGiaDriver") { DanhGiaDriverScreen() }
////                        composable("ThanhToan") { ThanhToanScreen() }
////                        composable("ChiTietLichSuChuyenDi") { ChiTietLichSuChuyenDiScreen() }
////                        composable("ChiTietChuyenDi") { ChiTietChuyenDiScreen(navController) }
////                        composable("XacNhanDiemDon") { XacNhanDiemDonScreen(navController) }
////                        composable("TheoDoiLoTrinh") { TheoDoiLoTrinhScreen() }
////                        composable("Call") { CallScreen() }
////                        composable("HoSoUser") { HoSoUserScreen() }
////                        composable("ThongBao") { ThongBaoScreen() }
////                        composable("GhiChu") { GhiChuScreen(navController) }
////                        composable("LichSuChuyenDi") { LichSuChuyenDiScreen() }
////                        composable("CallDriver") { CallDriver() }
////                        composable("HoSoDriver") { HoSoDriverScreen(navController) }
////                        composable("TaoChuyenDi") { TaoChuyenDiScreen() }
////                        composable("XacNhanGhep") { XacNhanGhepScreen() }
////                        composable("TheoDoiLoTrinhChungUser") { TheoDoiLoTrinhChungUserScreen() }
////                        composable("XacNhanHoanThanh") { XacNhanHoanThanhScreen() }
////                    }
////                }
////            }
////        }
//


package com.example.myhatd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.example.myhatd.ui.navigation.AppNavigation
import com.example.myhatd.ui.theme.MyHatdTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val systemUiController = rememberSystemUiController()
            SideEffect {
                systemUiController.setSystemBarsColor(
                    color = Color.White,
                    darkIcons = true
                )
            }

            MyHatdTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Chỉ gọi AppNavigation để xử lý toàn bộ navigation
                    AppNavigation()
                }
            }
        }
    }
}
