package com.example.hatd.ui.auth.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import com.example.hatd.R
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.background


@Composable
fun loginintro() {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        // 🖼 Hình nền
        Image(
            painter = painterResource(id = R.drawable.bgintro),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Image(
            painter = painterResource(id = R.drawable.logo), // đổi tên file thật
            contentDescription = "Logo HATD",
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.TopStart)
                .padding(16.dp)
        )

        // 🔹 Nút Login
        OutlinedButton(
            onClick = { /* TODO */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4ABDE0)),
            border = BorderStroke(2.dp, Color.White),
            modifier = Modifier
                .padding(start = 60.dp, top = 680.dp)
                .width(300.dp)
                .height(50.dp)
        ) {
            Text(
                text = "Login",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // 🔹 Tiêu đề
        Text(
            "Chào Mừng đến với HATD!",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 50.dp, top = 400.dp)
        )

        // 🔹 Ảnh minh họa
        Image(
            painter = painterResource(id = R.drawable.bg2intro),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 55.dp, top = 200.dp)
                .width(300.dp)
                .height(180.dp)
                .clip(RoundedCornerShape(20.dp)),
            contentScale = ContentScale.Crop
        )

        // 🔹 Đoạn mô tả
        Text(
            text = "Cùng chúng tôi đồng hành, mọi chuyến đi của bạn sẽ trở nên đơn giản hơn, tiết kiệm hơn và tràn đầy những kết nối tuyệt vời trên suốt hành trình.",
            color = Color.Black,
            fontSize = 15.sp,
            lineHeight = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 450.dp, start = 20.dp, end = 20.dp)
        )

        // 🔹 Dấu chấm chỉ trang
        DotsIndicator(
            totalDots = 5,
            selectedIndex = 0, // chấm thứ 2 sáng
            modifier = Modifier
                .align(Alignment.TopCenter)  // 📍 căn giữa theo chiều ngang
                .padding(top = 530.dp)       // 📍 đặt ngay dưới đoạn mô tả (bạn có thể thử 490–520dp)
        )

        // 🔹 Logo nhỏ "Form HATD"
        Row(
            modifier = Modifier
                .padding(top = 795.dp)
                .align(Alignment.TopCenter),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Form", fontWeight = FontWeight.Bold)
            Spacer(Modifier.width(4.dp))
            Text(
                "HATD",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color(0xFF2A5EE1)
            )
        }
    }
}

@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        repeat(totalDots) { index ->
            val color = if (index == selectedIndex) Color(0xFF4FC3F7) else Color.LightGray
            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .size(if (index == selectedIndex) 10.dp else 8.dp)
                    .background(color, shape = CircleShape)
            )

        }
    }
}
