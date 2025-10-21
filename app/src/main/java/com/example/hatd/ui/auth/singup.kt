package com.example.hatd.ui.auth.singup

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hatd.R
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.foundation.BorderStroke
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController





@Composable
fun SingupScreen(navController: NavController) {
    var phoneNumber by remember { mutableStateOf("") }

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
                onClick = {navController.navigate("intro") },
                Modifier.align(Alignment.Start)
                    .offset(x = -5.dp, y = (-1).dp) // sang phải , lên


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
                contentAlignment = Alignment.Center // căn giữa text và line
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
                        color = Color(0xFF2A5EE1), //  Đường gạch xanh
                        thickness = 2.dp,
                        modifier = Modifier
                            .padding(top = 2.dp)
                            .fillMaxWidth(0.8f) // độ dài đường gạch
                    )
                }
            }


            Spacer(modifier = Modifier.height(8.dp))

            // Mô tả
            Text(
                text = "Số điện thoại của bạn sẽ được sử dụng để xác minh và đăng nhập vào tài khoản.",
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(28.dp))
            // ✅ Dòng tiêu đề "Số điện thoại" với icon
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

            // ✅ Ô nhập số điện thoại
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
                // Icon điện thoại
                Image(
                    painter = painterResource(id = R.drawable.vn), // Thêm hình cờ Việt Nam vào drawable
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


                // Mã vùng
                Text(
                    text = "+84",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.width(12.dp))

                TextField(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
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

            // Nút Continue
            Spacer(modifier = Modifier.height(20.dp)) // 👈 Xích nút xuống một chút

            Button(
                onClick = { /* TODO: handle continue */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent, //  nền trong suốt
                    contentColor = Color.Black          //  chữ màu đen
                ),
                border = BorderStroke(2.dp, Color.Black) // viền đen
            ) {
                Text(
                    text = "Continue",
                    fontSize = 20.sp,
                    color = Color.Black
                )
            }

        }

        // Footer
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = (-43).dp) //  di chuyển lên , xuống
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
