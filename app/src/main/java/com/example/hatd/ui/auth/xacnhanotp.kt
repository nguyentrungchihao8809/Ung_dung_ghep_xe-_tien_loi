package com.example.hatd.ui.auth.xacnhanotp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun xacnhanotpScreen(
    onBack: () -> Unit = {},
    onContinue: () -> Unit = {}
) {
    var name by remember { mutableStateOf("") }
    var cccd by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .background(Color.White), // nền toàn bộ màn hình trắng
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // 🔹 Nút back
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
        }

        // 🔹 TextField Tên
        Text(
            text = "Tên",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.align(Alignment.Start)
        )
        TextField(
            value = name,
            onValueChange = { name = it },
            placeholder = { Text("Bạn muốn được gọi bằng tên gì ?", color = Color.Gray) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 16.dp)
                .background(Color.White) // nền trắng cho TextField
        )

        // 🔹 TextField CCCD
        Text(
            text = "CCCD",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.align(Alignment.Start)
        )
        TextField(
            value = cccd,
            onValueChange = { cccd = it },
            placeholder = { Text("", color = Color.Gray) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 16.dp)
                .background(Color.White) // nền trắng cho TextField
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 🔹 Đoạn mô tả
        Text(
            text = "Bằng việc tiếp tục, bạn xác nhận rằng bạn đã đọc và đồng ý với " +
                    "Điều Khoản Dịch Vụ và Thông Báo Bảo Mật của chúng tôi về cách chúng tôi thu thập, sử dụng, tiết lộ và xử lý dữ liệu cá nhân của bạn.",
            color = Color.Black,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 🔹 Nút Tiếp tục
        OutlinedButton(
            onClick = onContinue,
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(48.dp),
            shape = RoundedCornerShape(30.dp),
            border = BorderStroke(2.dp, Color(0xFF4ABDE0))
        ) {
            Text(
                text = "Tiếp tục",
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

