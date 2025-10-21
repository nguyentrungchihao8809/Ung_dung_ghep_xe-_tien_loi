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
import com.example.hatd.ui.auth.singup.SingupScreen
import com.example.hatd.ui.user.TaoYeuCauChuyenDiScreen.TaoYeuCauChuyenDiScreen
import com.example.hatd.ui.user.DanhGiaDriverScreen.DanhGiaDriverScreen
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
                        composable("TaoYeuCauChuyenDi") {TaoYeuCauChuyenDiScreen() }
                        composable("DanhGiaDriver") {DanhGiaDriverScreen() }


                    }
                }
            }
        }
    }
}
