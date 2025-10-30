//package com.example.myhatd.ui.navigation
//
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.platform.LocalContext
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.example.myhatd.ui.home_user.HomeUserScreen
//import com.example.myhatd.ui.otp.PhoneAuthScreen
//import com.example.myhatd.ui.otp.VerifyOtpScreen
//import com.example.myhatd.ui.otp.findActivity
//import com.example.myhatd.viewmodel.AuthViewModel
//
//@Composable
//fun AppNavigation() {
//    val navController = rememberNavController()
//    val context = LocalContext.current
//    val activity = context.findActivity() ?: return
//    val viewModel: AuthViewModel = viewModel(factory = AuthViewModel.Factory(activity))
//
//    NavHost(
//        navController = navController,
//        startDestination = NavigationRoutes.PHONE_AUTH
//    ) {
//
//        composable(NavigationRoutes.HOME) {
//            HomeUserScreen()
//        }
//        composable(NavigationRoutes.PHONE_AUTH) {
//            PhoneAuthScreen()
//        }
//        composable(NavigationRoutes.VERIFY_OTP) {
//            VerifyOtpScreen(viewModel = viewModel)
//        }
//    }
//}
package com.example.myhatd.ui.navigation

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myhatd.ui.home_user.HomeUserScreen
import com.example.myhatd.ui.otp.PhoneAuthScreen
import com.example.myhatd.ui.otp.VerifyOtpScreen
import com.example.myhatd.ui.otp.GioiThieuScreen
import com.example.myhatd.ui.otp.intro.IntroScreen
import com.example.myhatd.ui.otp.xacnhanotpScreen
import com.example.myhatd.ui.otp.findActivity
import com.example.myhatd.viewmodel.AuthViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val activity = context.findActivity() ?: return
    val viewModel: AuthViewModel = viewModel(factory = AuthViewModel.Factory(activity))
    val state = viewModel.state // ✅ Compose sẽ tự recompose

    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.THONG_TIN_USER
    ) {
        composable(NavigationRoutes.PHONE_AUTH) {
            PhoneAuthScreen( navController = navController,
                viewModel = viewModel)
        }
        composable(NavigationRoutes.VERIFY_OTP) {
            VerifyOtpScreen(navController = navController,
                viewModel = viewModel)
        }
        composable(NavigationRoutes.HOME) {
            HomeUserScreen()
        }
        composable(NavigationRoutes.GIOI_THIEU) {
            GioiThieuScreen(navController = navController)
        }
        composable(NavigationRoutes.INTRO) {
            IntroScreen(navController = navController)
        }
        composable(NavigationRoutes.THONG_TIN_USER) {
            xacnhanotpScreen(navController = navController)
        }
    }

    // ✅ Đặt LaunchedEffect SAU NavHost
    LaunchedEffect(state.isOtpSent, state.isAuthenticated) {
        println("DEBUG >>> isOtpSent=${state.isOtpSent}, isAuthenticated=${state.isAuthenticated}")
        when {
            state.isAuthenticated -> {
                navController.navigate(NavigationRoutes.THONG_TIN_USER) {
                    popUpTo(NavigationRoutes.PHONE_AUTH) { inclusive = true }
                }
            }
            state.isOtpSent -> {
                navController.navigate(NavigationRoutes.VERIFY_OTP)
            }
        }
    }
}

