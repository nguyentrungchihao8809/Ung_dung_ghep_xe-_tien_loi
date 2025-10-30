//// Thư mục: ui
//// File: PhoneAuthScreen.kt
//
//package com.example.myhatd.ui.otp
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.example.myhatd.viewmodel.AuthViewModel
//
//// KHÔNG CẦN import androidx.compose.ui.text.input.KeyboardOptions hay KeyboardType
//
//@Composable
//fun PhoneAuthScreen() {
//    val context = LocalContext.current
//
//    // 1. Lấy Activity bằng hàm mở rộng an toàn (YÊU CẦU FILE Utils.kt)
//    val activity = remember(context) { context.findActivity() }
//
//    if (activity == null) {
//        Text("Lỗi: Không thể tìm thấy Activity cho Firebase Auth.", color = MaterialTheme.colorScheme.error)
//        return
//    }
//
//    // 2. Khởi tạo ViewModel
//    val viewModel: AuthViewModel = viewModel(factory = AuthViewModel.Factory(activity))
//
//    val state = viewModel.state
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(24.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text(text = "Đăng nhập bằng Số điện thoại", style = MaterialTheme.typography.headlineMedium)
//        Spacer(modifier = Modifier.height(32.dp))
//
//        // Input nhập số điện thoại
//        OutlinedTextField(
//            value = state.phoneNumber,
//            onValueChange = viewModel::onPhoneNumberChange,
//            label = { Text("Số điện thoại (VD: +8490xxxxxxx)") },
//            modifier = Modifier.fillMaxWidth()
//            // ĐÃ XÓA hoàn toàn tham số keyboardOptions, bàn phím sẽ là mặc định (Text)
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Nút gửi OTP
//        Button(
//            onClick = viewModel::sendOtp,
//            enabled = !state.isLoading && !state.isOtpSent,
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            if (state.isLoading) {
//                CircularProgressIndicator(modifier = Modifier.size(20.dp), color = MaterialTheme.colorScheme.onPrimary)
//            } else {
//                Text(if (state.isOtpSent) "Mã đã gửi (Tiếp tục)" else "Gửi Mã OTP")
//            }
//        }
//
//        Spacer(modifier = Modifier.height(12.dp))
//
//        // Hiển thị thông báo
//        if (state.error != null) {
//            Text(text = "Lỗi: ${state.error}", color = MaterialTheme.colorScheme.error)
//        } else if (state.isOtpSent) {
//            Text(text = "Mã OTP đã gửi thành công! ID: ${state.verificationId}", color = MaterialTheme.colorScheme.primary)
//            Text(text = "Chuyển sang màn hình nhập OTP...", style = MaterialTheme.typography.bodySmall)
//        }
//    }
//}

package com.example.myhatd.ui.otp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myhatd.R
import com.example.myhatd.viewmodel.AuthViewModel
import androidx.compose.foundation.BorderStroke
import androidx.navigation.NavController
import com.example.myhatd.ui.navigation.NavigationRoutes



@Composable
fun PhoneAuthScreen(viewModel: AuthViewModel,
                    navController: NavController) {
    val context = LocalContext.current
    val activity = remember(context) { context.findActivity() }

    if (activity == null) {
        Text("Lỗi: Không thể tìm thấy Activity cho Firebase Auth.", color = MaterialTheme.colorScheme.error)
        return
    }

//    val viewModel: AuthViewModel = viewModel(factory = AuthViewModel.Factory(activity))
    val state = viewModel.state

    LaunchedEffect(state.isOtpSent) {
        if (state.isOtpSent) {
            navController.navigate(NavigationRoutes.VERIFY_OTP)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Ảnh nền full màn hình
        Image(
            painter = painterResource(id = R.drawable.bgsingup),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Nội dung chính
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .align(Alignment.Center)
                .offset(y = (-230).dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Nút quay lại
            IconButton(
                onClick = {navController.popBackStack() },
                Modifier.align(Alignment.Start)
                    .offset(x = -5.dp, y = (-1).dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Tiêu đề có gạch chân xanh
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Nhập số điện thoại của bạn",
                        fontSize = 24.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.None
                    )

                    Divider(
                        color = Color(0xFF2A5EE1),
                        thickness = 2.dp,
                        modifier = Modifier
                            .padding(top = 2.dp)
                            .fillMaxWidth(0.8f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Số điện thoại của bạn sẽ được sử dụng để xác minh và đăng nhập vào tài khoản.",
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Tiêu đề "Số điện thoại" với icon
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp)
            ) {
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Số điện thoại",
                    fontSize = 15.sp,
                    color = Color(0xFF1976D2),
                    fontWeight = FontWeight.Medium
                )
                Icon(
                    painter = painterResource(id = R.drawable.phone),
                    contentDescription = "Phone icon",
                    tint = Color(0xFF1976D2),
                    modifier = Modifier.size(18.dp)
                )
            }

            // Input nhập số điện thoại, giữ logic file 1
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(1.dp, Color.Gray, RoundedCornerShape(12.dp))
                    .background(Color.White)
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.vn),
                    contentDescription = "Vietnam flag",
                    modifier = Modifier
                        .size(26.dp)
                        .clip(RoundedCornerShape(4.dp))
                )
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .height(22.dp)
                        .background(Color.Gray)
                )
                Spacer(modifier = Modifier.width(10.dp))

//                Text(
//                    text = "+84",
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 16.sp,
//                    color = Color.Black
//                )

                Spacer(modifier = Modifier.width(12.dp))

                OutlinedTextField(
                    value = state.phoneNumber,
                    onValueChange = viewModel::onPhoneNumberChange,
                    placeholder = { Text("96691599", color = Color.Gray) },
                    textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier.weight(1f),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        errorContainerColor = Color.Transparent,
                        cursorColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent,
                        focusedPlaceholderColor = Color.Gray,
                        unfocusedPlaceholderColor = Color.Gray
                    )

                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Nút gửi OTP, giữ logic file 1
            Button(
                onClick = viewModel::sendOtp,
                enabled = !state.isLoading && !state.isOtpSent,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Black
                ),
                border = BorderStroke(2.dp, Color.Black)
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                } else {
                    Text(
                        text = if (state.isOtpSent) "Mã đã gửi (Tiếp tục)" else "Gửi Mã OTP",
                        fontSize = 20.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Hiển thị thông báo lỗi hoặc OTP ID
            if (state.error != null) {
                Text(text = "Lỗi: ${state.error}", color = MaterialTheme.colorScheme.error)
            } else if (state.isOtpSent) {
                Text(
                    text = "Mã OTP đã gửi thành công! ID: ${state.verificationId}",
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Chuyển sang màn hình nhập OTP...",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        // Footer
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = (-43).dp)
                .padding(bottom = 30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "From ",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 15.sp
            )
            Text(
                text = "HATD",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(0xFF2A5EE1)
            )
        }
    }
}
