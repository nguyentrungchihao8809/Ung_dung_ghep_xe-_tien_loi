package com.example.hatd.ui.driver.CallDriver

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hatd.R

@Composable
fun CallDriverScreen(onBackClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // 🔹 Nút X ở góc trái trên
        Image(
            painter = painterResource(id = R.drawable.x),
            contentDescription = "Close",
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 60.dp, start = 20.dp)
                .size(60.dp)
                .clickable { onBackClick() } // cho phép nhấn để quay lại
        )

        // 🔹 Ảnh driver + chữ Driver + trạng thái cuộc gọi
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (-170).dp) // kéo cụm này cao lên
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Ảnh driver
            Image(
                painter = painterResource(id = R.drawable.anhuser),
                contentDescription = "Driver Image",
                modifier = Modifier
                    .size(260.dp) // phóng to ảnh
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Tên tài xế
            Text(
                text = "Driver",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            // Trạng thái gọi
            Text(
                text = "Đang gọi...",
                fontSize = 20.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }

        // 🔹 3 nút chức năng: loa - mic - kết thúc
        Surface(
            shape = RoundedCornerShape(50),
            color = Color(0xFFD5E8F8),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 60.dp)
                .height(75.dp)
                .width(300.dp) // 🔸 tăng chiều ngang để 3 nút dãn xa nhau hơn
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp), // 🔸 thêm khoảng cách hai bên
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // 🔸 Nút loa
                Image(
                    painter = painterResource(id = R.drawable.loa),
                    contentDescription = "Speaker",
                    modifier = Modifier
                        .size(60.dp)
                        .clickable { /* TODO: bật loa */ }
                )

                // 🔸 Nút mic
                Image(
                    painter = painterResource(id = R.drawable.mic),
                    contentDescription = "Mute",
                    modifier = Modifier
                        .size(60.dp)
                        .clickable { /* TODO: tắt mic */ }
                )

                // 🔸 Nút kết thúc cuộc gọi
                Image(
                    painter = painterResource(id = R.drawable.ketthuc),
                    contentDescription = "End call",
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .clickable { /* TODO: kết thúc */ }
                )
            }
        }
    }
}
