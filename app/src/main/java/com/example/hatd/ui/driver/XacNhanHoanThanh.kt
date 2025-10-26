package com.example.hatd.ui.driver.XacNhanHoanThanh
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hatd.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            XacNhanHoanThanhScreen()
        }
    }
}

@Composable
fun XacNhanHoanThanhScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEFF3F7)) // nền giả map
    ) {
        // 🔹 Nút quay lại
        IconButton(
            onClick = { /* TODO: Back */ },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopStart)
                .background(Color.White, CircleShape)
                .size(40.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.Black
            )
        }

        // 🔹 Khung thông tin user
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .padding(horizontal = 12.dp, vertical = 16.dp)
        ) {
            Text(
                text = "Đã đến",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black
            )
            Text(
                text = "Chung Cư Khang Gia - Đại học Giao Thông Vận Tải",
                fontSize = 13.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 🔸 Card thông tin user
            UserInfoCard()

            Spacer(modifier = Modifier.height(12.dp))



                // 🔹 Nút “ĐÃ HOÀN THÀNH”
                Button(
                    onClick = { /* TODO: xử lý xác nhận hoàn thành */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFBFEBFF)),
                    shape = RoundedCornerShape(50),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .padding(horizontal = 24.dp)
                ) {
                    Text(
                        text = "ĐÃ HOÀN THÀNH",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF43699A)
                    )
                }
            }

        }
    }






@Composable
fun UserInfoCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        shape = RoundedCornerShape(24.dp),
        border = BorderStroke(1.dp, Color(0xFF0081F1))
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            // Ảnh nền trong card user
            Image(
                painter = painterResource(id = R.drawable.nenuser),
                contentDescription = "Background User",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Avatar + xe + tên
            Row(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically, // Căn giữa theo chiều dọc
                        modifier = Modifier.padding(start = 16.dp, top = 1.dp)

                    ) {
                        // Avatar
                        Image(
                            painter = painterResource(id = R.drawable.avtuser),
                            contentDescription = "Ảnh User",
                            modifier = Modifier
                                .size(100.dp)
                                .offset(y = (-15).dp) // 🔹 Xích avatar lên 10.dp
                                .clip(RoundedCornerShape(50.dp))
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        // Tên, SĐT và đánh giá sao
                        Column {
                            Text(
                                text = "Kim Bâu",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp,
                                color = Color(0xFF333333)
                            )
                            Text(
                                text = "SĐT: 0908 123 456",
                                fontSize = 13.sp,
                                color = Color.Gray
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "⭐ 5.0",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.Black
                            )
                        }
                    }

                }
            }


            // Logo góc phải dưới
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo HATD",
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.BottomEnd)
                    .offset(x = 10.dp, y = 30.dp)
                    .padding(bottom = 8.dp, end = 16.dp)
            )
        }
    }
}



