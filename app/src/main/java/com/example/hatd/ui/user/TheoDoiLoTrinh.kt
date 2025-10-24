package com.example.hatd.ui.user.TheoDoiLoTrinh
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
            TheoDoiLoTrinhScreen()
        }
    }
}

@Composable
fun TheoDoiLoTrinhScreen() {
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

        // 🔹 Khung thông tin tài xế
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .padding(horizontal = 12.dp, vertical = 16.dp)
        ) {
            Text(
                text = "Tài xế sắp đến",
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

            // 🔸 Card thông tin tài xế
            DriverInfoCard()

            Spacer(modifier = Modifier.height(12.dp))

            // 🔸 Hàng nút chat và gọi
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = 20.dp, y = 0.dp), // 👉 chỉnh vị trí ngang/dọc tùy ý
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 🔸 Nút Chat
                OutlinedButton(
                    onClick = { /* TODO: Chat với tài xế */ },
                    shape = RoundedCornerShape(50),
                    border = BorderStroke(1.dp, Color.Black),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                    modifier = Modifier.height(44.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.chat),
                        contentDescription = "Chat",
                        tint = Color.Black,
                        modifier = Modifier.size(45.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Chat với tài xế", fontSize = 14.sp)
                }

                Spacer(modifier = Modifier.width(8.dp))

                // 🔸 Nút gọi
                IconButton(
                    onClick = { /* TODO: Gọi tài xế */ },
                    modifier = Modifier
                        .size(55.dp)
                        .padding(start = 6.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.call),
                        contentDescription = "Call",
                        tint = Color.Black,
                        modifier = Modifier.size(45.dp)
                    )
                }
            }

        }
            }

        }




@Composable
fun DriverInfoCard() {
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

            // Ảnh nền trong card tài xế
            Image(
                painter = painterResource(id = R.drawable.nenthongtindriver),
                contentDescription = "Background Driver",
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
                    Box {
                        // Avatar
                        Image(
                            painter = painterResource(id = R.drawable.avtdriver),
                            contentDescription = "Ảnh tài xế",
                            modifier = Modifier
                                .size(80.dp)
                                .background(Color.White, CircleShape)
                                .border(2.dp, Color(0xFF0081F1), CircleShape)
                                .clip(CircleShape)
                        )

                        // Ảnh xe máy
                        Image(
                            painter = painterResource(id = R.drawable.xemay),
                            contentDescription = "Xe máy",
                            modifier = Modifier
                                .size(50.dp)
                                .align(Alignment.BottomEnd)
                                .offset(x = 6.dp, y = 6.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Tên tài xế và sao
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Hứa Anh Tới Đón",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            color = Color(0xFF333333)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "5.0⭐",
                            color = Color.Black,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp
                        )
                    }
                }
            }

            // Biển số xe + loại xe
            Column(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 16.dp, end = 16.dp),
                horizontalAlignment = Alignment.End
            ) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, Color(0xFF0081F1)),
                    color = Color(0xFFE3F2FD),
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text(
                        text = "59TA-113.15",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color(0xFF0081F1),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                Text(
                    text = "EVO.HATD CYAN",
                    fontSize = 12.sp,
                    color = Color(0xFF007ACC)
                )
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
