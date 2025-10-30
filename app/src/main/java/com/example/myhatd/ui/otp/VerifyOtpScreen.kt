//// Thư mục: ui
//// File: VerifyOtpScreen.kt
//
//package com.example.myhatd.ui.otp
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//// Đã xóa import cho KeyboardType hoặc KeyboardOptions
//import androidx.compose.ui.unit.dp
//import com.example.myhatd.viewmodel.AuthViewModel
//
//@Composable
//fun VerifyOtpScreen(viewModel: AuthViewModel) {
//    val state = viewModel.state
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(24.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text(
//            text = "Nhập Mã OTP",
//            style = MaterialTheme.typography.headlineMedium
//        )
//        Text(
//            text = "Mã đã gửi đến ${state.phoneNumber}",
//            style = MaterialTheme.typography.bodySmall,
//            modifier = Modifier.padding(top = 8.dp, bottom = 24.dp)
//        )
//
//        // Input nhập OTP
//        OutlinedTextField(
//            value = state.otpCode,
//            onValueChange = { viewModel.onOtpCodeChange(it) },
//            label = { Text("Mã 6 chữ số") },
//            modifier = Modifier.fillMaxWidth(),
//            // ĐÃ BỎ hoàn toàn tham số keyboardOptions, sử dụng bàn phím mặc định (text/số)
//            singleLine = true
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Nút Xác minh
//        Button(
//            onClick = viewModel::verifyOtp,
//            enabled = !state.isLoading && state.otpCode.length == 6,
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            if (state.isLoading) {
//                CircularProgressIndicator(modifier = Modifier.size(20.dp), color = MaterialTheme.colorScheme.onPrimary)
//            } else {
//                Text("Xác minh")
//            }
//        }
//
//        // Hiển thị lỗi
//        if (state.error != null) {
//            Text(text = "Lỗi: ${state.error}", color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(top = 12.dp))
//        }
//    }
//}

package com.example.myhatd.ui.otp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.example.myhatd.R
import com.example.myhatd.viewmodel.AuthViewModel
import androidx.navigation.NavController
import com.example.myhatd.ui.navigation.NavigationRoutes

@Composable
fun VerifyOtpScreen( navController: NavController,
                     viewModel: AuthViewModel) {
    val state = viewModel.state

    LaunchedEffect(state.isAuthenticated) {
        if (state.isAuthenticated) {
            navController.navigate(NavigationRoutes.THONG_TIN_USER) {
                popUpTo(NavigationRoutes.PHONE_AUTH) { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp)
    ) {
        // Nút quay lại
        Icon(
            painter = painterResource(id = R.drawable.back),
            contentDescription = "Quay lại",
            modifier = Modifier
                .offset(y = 27.dp)
                .size(50.dp)
                .clickable {  viewModel.resetState()
                    navController.popBackStack() },
            tint = Color.Black
        )

        // Toàn bộ nội dung
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(top = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Tiêu đề
            BoxDecor(viền = 5.dp, màu = Color(0xFF00BCD4), bo = 24.dp, pad = 10.dp) {
                BoxDecor(
                    viền = 4.dp,
                    màu = Color(0xFFBAB7B7),
                    bo = 16.dp,
                    nền = Color(0xFFE9E9E9),
                    pad = 16.dp
                ) {
                    Text("Mã xác thực OTP", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(Modifier.height(40.dp))

            // Nội dung khung OTP
            BoxDecor(
                viền = 5.dp,
                bo = 24.dp,
                màu = Color(0xFF00BCD4),
                nền = Color.White,
                pad = 24.dp
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        "Nhập mã gồm 6 chữ số được gửi đến\n${state.phoneNumber}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center
                    )

                    Spacer(Modifier.height(24.dp))

                    // ✅ OTP Input Field - đã fix
                    OTPInputField(
                        otp = state.otpCode,
                        onOtpChange = { if (it.length <= 6) viewModel.onOtpCodeChange(it) }
                    )

                    Spacer(Modifier.height(32.dp))

                    // ✅ Nút xác minh - bây giờ đã hiển thị đúng
                    Button(
                        onClick = viewModel::verifyOtp,
                        enabled = !state.isLoading && state.otpCode.length == 6,
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF00BCD4)
                        )
                    ) {
                        if (state.isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                color = Color.White
                            )
                        } else {
                            Text("Xác minh", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    if (state.error != null) {
                        Text(
                            text = "Lỗi: ${state.error}",
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.padding(top = 12.dp)
                        )
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            // Nút gửi lại mã
            BoxDecor(
                viền = 3.dp,
                màu = Color(0xFF00BCD4),
                bo = 12.dp,
                pad = 8.dp,
                clickable = { /* viewModel.resendOtp() nếu cần */ },
                modifier = Modifier.width(120.dp)
            ) {
                Text("Gửi lại mã", fontSize = 14.sp, fontWeight = FontWeight.Medium)
            }

            Spacer(Modifier.height(20.dp))

            // Ảnh mèo bên dưới
            Image(
                painter = painterResource(R.drawable.cat_chubby),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .offset(x = -60.dp, y = -150.dp)
                    .width(560.dp)
                    .height(400.dp)
            )
        }
    }
}

// -------------------- HỖ TRỢ GIAO DIỆN -------------------- //

@Composable
fun OTPInputField(otp: String, onOtpChange: (String) -> Unit) {
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) { focusRequester.requestFocus() }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp) // ✅ Giới hạn chiều cao
    ) {
        // Hàng 6 ô OTP
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            repeat(6) { i ->
                val c = otp.getOrNull(i)?.toString().orEmpty()
                val chọn = i == otp.length
                Box(
                    Modifier
                        .size(50.dp)
                        .VienChamCham(
                            2.dp,
                            if (chọn) Color(0xFF00BCD4) else Color(0xFFCCCCCC),
                            12.dp
                        )
                        .background(
                            if (c.isNotEmpty()) Color(0xFFE0F7FA) else Color.White,
                            RoundedCornerShape(12.dp)
                        )
                        .clickable { focusRequester.requestFocus() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        c,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (c.isNotEmpty()) Color(0xFF00BCD4) else Color.Gray
                    )
                }
            }
        }

        // ✅ BasicTextField chỉ phủ lên hàng OTP (50dp), không phủ toàn màn hình
        BasicTextField(
            value = otp,
            onValueChange = { if (it.all(Char::isDigit)) onOtpChange(it) },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp) // ✅ Cùng chiều cao với Box cha
                .alpha(0.01f)
                .focusRequester(focusRequester),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            textStyle = TextStyle(color = Color.Transparent)
        )
    }
}

@Composable
fun BoxDecor(
    modifier: Modifier = Modifier,
    viền: Dp = 4.dp,
    màu: Color = Color(0xFF00BCD4),
    bo: Dp = 24.dp,
    nền: Color = Color.White,
    pad: Dp = 24.dp,
    height: Dp? = null,
    clickable: (() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .then(if (height != null) Modifier.height(height) else Modifier)
            .VienChamCham(viền, màu, bo)
            .background(nền, RoundedCornerShape(bo))
            .then(if (clickable != null) Modifier.clickable { clickable() } else Modifier)
            .padding(pad),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

fun Modifier.VienChamCham(độDày: Dp, màu: Color, bo: Dp) = drawBehind {
    drawRoundRect(
        color = màu,
        style = Stroke(
            width = độDày.toPx(),
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(30f, 10f))
        ),
        cornerRadius = CornerRadius(bo.toPx())
    )
}
