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
                .padding(top = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // ✅ Sửa phần này: thay IconButton bằng Image (ảnh nút quay lại)
            Image(
                painter = painterResource(id = R.drawable.back), // đổi tên nếu ảnh bạn khác
                contentDescription = "Quay lại",
                modifier = Modifier
                    .size(28.dp)
                    .clickable { onBackClick() }
            )

            Text(
                text = "Chi tiết chuyến đi",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 🔹 Thẻ thông tin tài xế
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Box {
                // Ảnh nền (background)
                Image(
                    painter = painterResource(id = R.drawable.anhnensaudriver),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(20.dp)),
                    contentScale = ContentScale.Crop,
                    alpha = 0.35f
                )

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Ảnh tài xế
                    Image(
                        painter = painterResource(id = R.drawable.anhdriver),
                        contentDescription = "Driver",
                        modifier = Modifier
                            .size(70.dp)
                            .padding(end = 12.dp),
                        contentScale = ContentScale.Crop
                    )

                    // Thông tin bên trái
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Hứa Anh Tới Đón", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Spacer(Modifier.height(4.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("5.0", fontSize = 14.sp)
                            Icon(
                                painter = painterResource(id = R.drawable.ngoisao),
                                contentDescription = "Star",
                                tint = Color(0xFFFFD700),
                                modifier = Modifier
                                    .padding(start = 2.dp)
                                    .size(16.dp)
                            )
                        }
                        Spacer(Modifier.height(6.dp))
                        Text("EVO.HATD CYAN", fontSize = 13.sp, color = Color.Gray)
                    }

                    // Biển số và logo
                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            "59TA-113.15",
                            color = Color(0xFF005BBB),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            modifier = Modifier
                                .border(1.dp, Color(0xFF005BBB), RoundedCornerShape(8.dp))
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        )
                        Spacer(Modifier.height(8.dp))
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = null,
                            modifier = Modifier
                                .size(60.dp)
                                .alpha(0.5f), // logo mờ nhẹ hòa vào nền driver
                            contentScale = ContentScale.Crop
                        )
                    }
                }
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
                            painter = painterResource(id = R.drawable.dola),
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
