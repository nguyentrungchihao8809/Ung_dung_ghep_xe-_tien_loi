package com.example.hatd

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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hatd.ui.auth.gioithieu.GioiThieuScreen
import com.example.hatd.ui.auth.intro.IntroScreen
import com.example.hatd.ui.auth.otp.OtpScreen 
import com.example.hatd.ui.home.home_user.HomeUserScreen 
import com.example.hatd.ui.user.HenGio.HenGioScreen 
import com.example.hatd.ui.auth.singup.SingupScreen
import com.example.hatd.ui.user.TaoYeuCauChuyenDiScreen.TaoYeuCauChuyenDiScreen
import com.example.hatd.ui.user.DanhGiaDriver.DanhGiaDriverScreen 
import com.example.hatd.ui.user.ThanhToan.ThanhToanScreen 
import com.example.hatd.ui.user.ChiTietLichSuChuyenDi.ChiTietLichSuChuyenDiScreen 
import com.example.hatd.ui.user.ChiTietChuyenDi.ChiTietChuyenDiScreen
import com.example.hatd.ui.user.XacNhanDiemDon.XacNhanDiemDonScreen 
import com.example.hatd.ui.user.TheoDoiLoTrinh.TheoDoiLoTrinhScreen
import com.example.hatd.ui.user.Call.CallScreen
import com.example.hatd.ui.user.HoSoUser.HoSoUserScreen
import com.example.hatd.ui.user.ThongBao.ThongBaoScreen
import com.example.hatd.ui.user.GhiChu.GhiChuScreen 
import com.example.hatd.ui.user.LichSuChuyenDi.LichSuChuyenDiScreen 
import com.example.hatd.ui.driver.CallDriver
import com.example.hatd.ui.driver.HoSoDriver.HoSoDriverScreen
import com.example.hatd.ui.driver.TaoChuyenDi.TaoChuyenDiScreen
import com.example.hatd.ui.driver.XacNhanGhep.XacNhanGhepScreen
import com.example.hatd.ui.driver.TheoDoiLoTrinhChungUser.TheoDoiLoTrinhChungUserScreen
import com.example.hatd.ui.driver.XacNhanHoanThanh.XacNhanHoanThanhScreen
import com.example.hatd.ui.user.Chat.ChatScreen
import com.example.hatd.ui.driver.ChatDriver.ChatDriverScreen
import com.example.hatd.ui.home.HomeDriver.HomeDriverScreen //
import com.example.hatd.ui.user.HoSoUser.HoSoUserScreen
import com.example.hatd.ui.user.ThongBao.ThongBaoScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val systemUiController = rememberSystemUiController()
            val useDarkIcons = true

            SideEffect {
                systemUiController.setSystemBarsColor(
                    color = Color.White,
                    darkIcons = useDarkIcons
                )
            }

            val navController = rememberNavController()

            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "intro"
                    ) {
                       composable("gioithieu") { GioiThieuScreen(navController) }
                        composable("intro") { IntroScreen(navController) }
                        composable("singup") { SingupScreen(navController) }
                        composable("otp") { OtpScreen() }
                        composable("HenGio") { HenGioScreen(navController) }
                        composable("home_user") { HomeUserScreen() }
                        composable("TaoYeuCauChuyenDi") {TaoYeuCauChuyenDiScreen() }
                        composable("DanhGiaDriver") {DanhGiaDriverScreen() }
                        composable("ThanhToan") {ThanhToanScreen() }
                        composable("ChiTietLichSuChuyenDi") {ChiTietLichSuChuyenDiScreen() }
                        composable("ChiTietChuyenDi") {ChiTietChuyenDiScreen(navController) }
                        composable("XacNhanDiemDon") {XacNhanDiemDonScreen(navController) }
                        composable("XacNhanDatXe") {XacNhanDatXeScreen(navController) }
                        composable("TheoDoiLoTrinh") {TheoDoiLoTrinhScreen() }
                        composable("Call") {CallScreen() }
                        composable("HoSoUser") {HoSoUserScreen() }
                        composable("ThongBao") {ThongBaoScreen() }
                        composable("GhiChu") { GhiChuScreen(navController) }
                        composable("LichSuChuyenDi") { LichSuChuyenDiScreen() }
                        composable("CallDriver") {CallDriverScreen() }
                        composable("HoSoDriver") {HoSoDriverScreen(navController) }
                        composable("TaoChuyenDi") {TaoChuyenDiScreen() }
                        composable("XacNhanGhep") {XacNhanGhepScreen() }
                        composable("TheoDoiLoTrinhChungUser") { TheoDoiLoTrinhChungUserScreen() }
                        composable("XacNhanHoanThanh") { XacNhanHoanThanhScreen() }
                        composable("Chat") {ChatScreen(navController) }
                        composable("ChatDriver") {ChatDriverScreen(navController) }
                        composable("HoSoUser") {HoSoUserScreen(navController) }
                        composable("ThongBao") {ThongBaoScreen(navController) }
                        composable("HomeDriver") { HomeDriverScreen(navController) }




                    }
                }
            }
        }
    }
}
