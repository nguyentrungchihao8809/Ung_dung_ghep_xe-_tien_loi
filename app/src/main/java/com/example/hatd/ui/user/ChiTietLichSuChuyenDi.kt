package com.example.hatd.ui.user.ChiTietLichSuChuyenDi

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hatd.R
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.BorderStroke
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

@Composable
fun ChiTietLichSuChuyenDiScreen(onBackClick: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp)
            .padding(top = 28.dp)
    ) {
        // 🔹 Thanh tiêu đề
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* Quay lại */ }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
            Text(
                text = "Chi tiết chuyến đi",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 🔹 Thông tin tài xế
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .padding(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            shape = RoundedCornerShape(24.dp),
            border = BorderStroke(1.dp, Color(0xFF0081F1)) // viền xanh dương
        ) {
            Box(modifier = Modifier.fillMaxSize()) {

                // Ảnh nền trong card tài xế
                Image(
                    painter = painterResource(id = R.drawable.nenthongtindriver),
                    contentDescription = "Background Driver",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                // Avatar, xe máy và tên tài xế
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box {
                            // Avatar tài xế
                            Image(
                                painter = painterResource(id = R.drawable.avtdriver),
                                contentDescription = "Ảnh tài xế",
                                modifier = Modifier
                                    .size(80.dp)
                                    .offset(x = (-8).dp)
                                    .background(Color.White, CircleShape)
                                    .border(2.dp, Color(0xFF0081F1), CircleShape)
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

                        // Tên tài xế và đánh giá sao
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(start = 8.dp)
                        ) {
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

                // Biển số xe, tên xe
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

                // Logo góc dưới phải
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

        Spacer(modifier = Modifier.height(16.dp))

        // 🔹 Thông tin chuyến đi
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color(0xFF4ABDE0), RoundedCornerShape(16.dp))
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
        ) {
            Box {
                // Ảnh nền mờ phía sau phần chi tiết chuyến đi
                Image(
                    painter = painterResource(id = R.drawable.anhnenchitietchuyendi),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop,
                    alpha = 0.35f
                )

                Column(modifier = Modifier.padding(16.dp)) {
                    Text("HATD bike", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text("10/10/2025 • 7:00 CH", color = Color.Gray, fontSize = 14.sp)
                    Spacer(Modifier.height(12.dp))

                    // Điểm đón
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.diemdon),
                            contentDescription = null,
                            tint = Color(0xFF000000),
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Column {
                            Text("Chung Cư Khang Gia", fontWeight = FontWeight.Bold)
                            Text(
                                "Đường số 45 phường 14, quận Gò Vấp",
                                color = Color.Gray,
                                fontSize = 13.sp
                            )
                        }
                    }

                    // Ảnh đường nối giữa điểm đón và điểm đến
                    Image(
                        painter = painterResource(id = R.drawable.duonggachnoi),
                        contentDescription = "Đường nối giữa điểm đón và điểm đến",
                        modifier = Modifier
                            .padding(start = 2.dp, top = 2.dp, bottom = 2.dp)
                            .height(40.dp)
                    )

                    // Điểm đến
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.diemden),
                            contentDescription = null,
                            tint = Color(0xFF2196F3),
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Column {
                            Text("Đại học Giao Thông Vận Tải Tp. HCM", fontWeight = FontWeight.Bold)
                            Text(
                                "Số 70 đường Tô Ký, Hồ Chí Minh",
                                color = Color.Gray,
                                fontSize = 13.sp
                            )
                        }
                    }

                    Spacer(Modifier.height(12.dp))

                    // Tiền mặt
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.tien),
                            contentDescription = null,
                            tint = Color(0xFF4CAF50),
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text("Tiền mặt", fontWeight = FontWeight.Bold)
                        Spacer(Modifier.weight(1f))
                        Text("14.500đ", fontWeight = FontWeight.Bold)
                    }

                    Spacer(Modifier.height(12.dp))
                    Text("Ghi chú", fontWeight = FontWeight.Bold)
                }

                // Hình xe nhỏ góc dưới bên phải
                Image(
                    painter = painterResource(id = R.drawable.xegocduoiphai),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(90.dp)
                        .padding(end = 4.dp, bottom = 4.dp)
                )

                // Hình xe nhỏ góc dưới bên trái
                Image(
                    painter = painterResource(id = R.drawable.xegocduoitrai),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .size(90.dp)
                        .padding(start = 4.dp, bottom = 4.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Hai nút hành động dưới cùng
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedButton(
                onClick = { /* Đánh giá */ },
                modifier = Modifier
                    .width(150.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Đánh giá", color = Color.Black, fontSize = 16.sp)
            }

            Button(
                onClick = { /* Đặt lại */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2A5EE1)),
                modifier = Modifier
                    .width(150.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Đặt lại", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}
