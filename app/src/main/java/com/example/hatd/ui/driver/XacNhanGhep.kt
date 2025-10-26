package com.example.hatd.ui.driver.XacNhanGhep

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hatd.R

@Composable
fun XacNhanGhepScreen(navController: NavController? = null) {
    var showRejectDialog by remember { mutableStateOf(false) }
    var showConfirmDialog by remember { mutableStateOf(false) }
    var trangThai by remember { mutableStateOf("Đang chờ xác nhận") }
    var trangThaiThanhToan by remember { mutableStateOf("Chưa thanh toán") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp)
    ) {
        // 🔹 Thanh tiêu đề
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController?.popBackStack() }) {
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

        // 🔹 Thông tin user
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            shape = RoundedCornerShape(24.dp),
            border = BorderStroke(1.dp, Color(0xFF0081F1))
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = R.drawable.nenthongtindriver),
                    contentDescription = "Background Driver",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 24.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.avtuser),
                        contentDescription = "Ảnh User",
                        modifier = Modifier
                            .size(120.dp)
                            .offset(x = -20.dp, y = 1.dp)
                            .clip(RoundedCornerShape(50.dp))
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                    // 🔸 Hàng chứa tên, sdt, đánh giá sao
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column {
                            Text("Kim Bâu", fontWeight = FontWeight.SemiBold, fontSize = 16.sp, )
                            
                            Text("SĐT: 0908 123 456", fontSize = 13.sp, color = Color.Gray)
                        }
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = "⭐ 5.0",
                            color = Color.Black,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp

                        )
                    }
                }

                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo HATD",
                    modifier = Modifier
                        .size(110.dp)
                        .align(Alignment.BottomEnd)
                        .offset(x = 10.dp, y = 30.dp)
                        .padding(bottom = 8.dp, end = 16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // 🔹 Thông tin chuyến đi
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color(0xFF4ABDE0), RoundedCornerShape(16.dp))
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
        ) {
            Image(
                painter = painterResource(id = R.drawable.anhnenchitietchuyendi),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop,
                alpha = 0.1f
            )

            Column(modifier = Modifier.padding(16.dp)) {
                Text("HATD bike", fontWeight = FontWeight.Bold, fontSize = 16.sp)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Thời gian:", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                    Text("__/__/____ • __:__ __", color = Color(0xFF9E9E9E), fontSize = 14.sp)
                }

                Spacer(Modifier.height(12.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.diembatdau),
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Column {
                        Text("Điểm đón: Đại học GTVT", fontWeight = FontWeight.Bold)
                        Text("268 Lý Thường Kiệt, Q.10", color = Color.Gray, fontSize = 13.sp)
                    }
                }

                Image(
                    painter = painterResource(id = R.drawable.duonggachnoi),
                    contentDescription = "Đường nối",
                    modifier = Modifier
                        .padding(start = 2.dp, top = 2.dp, bottom = 2.dp)
                        .height(40.dp)
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.diemden),
                        contentDescription = null,
                        tint = Color(0xFF2196F3),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Column {
                        Text("Điểm đến: Bến xe Miền Đông", fontWeight = FontWeight.Bold)
                        Text("292 Đinh Bộ Lĩnh, Bình Thạnh", color = Color.Gray, fontSize = 13.sp)
                    }
                }

                Spacer(Modifier.height(12.dp))
                Text("Ghi chú", fontWeight = FontWeight.Bold)
                Text("Khách mang theo hành lý nhỏ", color = Color.Gray, fontSize = 14.sp)

                Spacer(Modifier.height(12.dp))
                Text("Trạng thái thanh toán", fontWeight = FontWeight.Bold)
                Text(
                    text = trangThaiThanhToan,
                    color = if (trangThaiThanhToan == "Đã thanh toán") Color(0xFF2E7D32) else Color(0xFFD32F2F),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 🔹 Hai nút hành động
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { showRejectDialog = true },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDE7C34)),
                shape = RoundedCornerShape(12.dp),
                enabled = trangThai == "Đang chờ xác nhận",
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
            ) {
                Text("Từ chối", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.width(12.dp))

            Button(
                onClick = { showConfirmDialog = true },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFBFEBFF)),
                shape = RoundedCornerShape(12.dp),
                enabled = trangThai == "Đang chờ xác nhận",
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
            ) {
                Text("Xác nhận", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }

        // 🔸 Dialog Từ chối
        if (showRejectDialog) {
            AlertDialog(
                onDismissRequest = { showRejectDialog = false },
                title = { Text("Xác nhận từ chối chuyến") },
                text = { Text("Bạn có chắc muốn từ chối chuyến đi này không?") },
                confirmButton = {
                    TextButton(onClick = {
                        trangThai = "Đã từ chối"
                        showRejectDialog = false
                    }) {
                        Text("Đồng ý", color = Color(0xFFDE7C34), fontWeight = FontWeight.Bold)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showRejectDialog = false }) {
                        Text("Hủy", color = Color.Gray)
                    }
                }
            )
        }

        // 🔸 Dialog Xác nhận
        if (showConfirmDialog) {
            AlertDialog(
                onDismissRequest = { showConfirmDialog = false },
                title = { Text("Xác nhận chuyến đi") },
                text = { Text("Bạn có chắc muốn xác nhận chuyến đi này không?") },
                confirmButton = {
                    TextButton(onClick = {
                        trangThai = "Đã xác nhận"
                        trangThaiThanhToan = "Đã thanh toán"
                        showConfirmDialog = false
                    }) {
                        Text("Đồng ý", color = Color(0xFF007AFF), fontWeight = FontWeight.Bold)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showConfirmDialog = false }) {
                        Text("Hủy", color = Color.Gray)
                    }
                }
            )
        }
    }
}
