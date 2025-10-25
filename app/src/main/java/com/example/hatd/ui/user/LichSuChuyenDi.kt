package com.example.hatd.ui.user.LichSuChuyenDi

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hatd.R

// Data class cho chuyến đi
data class ChuyenDi(
    val ngay: String,
    val gio: String,
    val loaiXe: String,
    val soTien: String,
    val diemDon: String,
    val diemTra: String,
    val trangThai: String,
    val iconXe: Int
)

@Composable
fun LichSuChuyenDiScreen() {
    // Danh sách chuyến đi mẫu
    val danhSachChuyenDi = listOf(
        ChuyenDi(
            ngay = "10/10/2025",
            gio = "16:30",
            loaiXe = "HATD Bike",
            soTien = "57.000đ",
            diemDon = "Bến xe Miền Tây",
            diemTra = "Đại học Giao Thông Vận Tải Tp.HCM",
            trangThai = "Hoàn thành",
            iconXe = R.drawable.xemay
        ),
        ChuyenDi(
            ngay = "09/01/2025",
            gio = "08:30",
            loaiXe = "HATD Bike",
            soTien = "200.000đ",
            diemDon = "Chung Cu Khang Gia",
            diemTra = "Đại học Giao Thông Vận Tải Tp.HCM",
            trangThai = "Hoàn thành",
            iconXe = R.drawable.xemay
        ),
        ChuyenDi(
            ngay = "09/01/2025",
            gio = "15:20",
            loaiXe = "HATD Car",
            soTien = "150.000đ",
            diemDon = "Sân bay Tân Sơn Nhất",
            diemTra = "Đại học Giao Thông Vận Tải Tp.HCM",
            trangThai = "Hoàn thành",
            iconXe = R.drawable.otoxe
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Ảnh nền cho toàn màn hình
        Image(
            painter = painterResource(id = R.drawable.bglichsu),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop //
        )

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Header: Nút Back và Box tiêu đề
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 40.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Nút Back
                Icon(
                    painter = painterResource(id = R.drawable.backicon),
                    contentDescription = "Quay lại",
                    modifier = Modifier.size(32.dp),
                    tint = Color(0xFF333333)
                )

                // Box tiêu đề
                Box(
                    modifier = Modifier
                        .width(220.dp) // Bỏ weight(1f) để dùng width cố định
                        .height(55.dp) // Chiều cao
                        .offset(x = 25.dp, y = 45.dp) // Di chuyển box sang phải 20dp
                        .background(
                            color = Color(0xFF4ABDE0),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .border(
                            width = 2.dp,
                            color = Color(0xFF4ABDE0),
                            shape = RoundedCornerShape(16.dp)
                        ),
                    contentAlignment = Alignment.Center // Căn giữa chữ trong box
                ) {
                    Text(
                        text = "Lịch sử chuyến đi",
                        fontSize = 18.sp, // Giảm font nhỏ hơn cho vừa
                        fontWeight = FontWeight.Bold,
                        color = Color.White,

                        ) // Điều chỉnh vị trí chữ nếu cần

                }
            }

            // Danh sách chuyến đi
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(danhSachChuyenDi) { chuyenDi ->
                    ItemChuyenDi(chuyenDi)
                }
            }
        }
    }
}

@Composable
fun ItemChuyenDi(chuyenDi: ChuyenDi) {
    Box(
        modifier = Modifier.fillMaxWidth()
            .offset(y = 25.dp)
    ) {
        // Card chính với viền đen nét liền
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, Color.Black, RoundedCornerShape(12.dp)),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 0.dp)
            ) {
                // Dòng đầu tiên: Ngày giờ và trạng thái
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Ngày giờ
                    Text(
                        text = "${chuyenDi.ngay} • ${chuyenDi.gio}",
                        fontSize = 13.sp,
                        color = Color(0xFF666666),
                        modifier = Modifier.offset(x = 50.dp)////////
                    )

                    // Trạng thái
                    Surface(
                        color = Color(0xFF4ABDE0),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.offset(y = 12.dp)
                    ) {
                        Text(
                            text = chuyenDi.trangThai,
                            fontSize = 12.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(0.dp))//////

                // Dòng thứ hai: Giá tiền và loại xe (giống format ngày giờ)
                Text(
                    text = "${chuyenDi.soTien} • ${chuyenDi.loaiXe}",
                    fontSize = 13.sp,
                    color = Color(0xFF666666),
                    modifier = Modifier.offset(x = 50.dp)/////
                )

                Spacer(modifier = Modifier.height(0.dp))

                // Viền chấm chấm ngang
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .drawBehind {
                            drawLine(
                                color = Color(0xFF000000),
                                start = androidx.compose.ui.geometry.Offset(0f, 0f),
                                end = androidx.compose.ui.geometry.Offset(size.width, 0f),
                                strokeWidth = 2.dp.toPx(),
                                pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 10f))
                            )
                        },
                    color = Color.Transparent
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Thông tin điểm đón và điểm trả
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Điểm đón
                    Row(
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Icon điểm đón (ảnh chấm xanh)
                        Image(
                            painter = painterResource(id = R.drawable.dinhvi),
                            contentDescription = "Điểm đón",
                            modifier = Modifier
                                .size(19.dp)
                                .offset(y = 6.dp)
                        )

                        Column {
                            Text(
                                text = chuyenDi.diemDon,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF333333)
                            )
                        }
                    }

                    // Điểm trả
                    Row(
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Icon điểm trả (ảnh chấm đỏ)
                        Image(
                            painter = painterResource(id = R.drawable.dot),
                            contentDescription = "Điểm trả",
                            modifier = Modifier
                                .size(17.dp)
                                .offset(y = 6.dp)
                        )

                        Column {
                            Text(
                                text = chuyenDi.diemTra,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF333333)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(0.dp))

                // Viền chấm chấm ngang
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .drawBehind {
                            drawLine(
                                color = Color(0xFF000000),
                                start = androidx.compose.ui.geometry.Offset(0f, 0f),
                                end = androidx.compose.ui.geometry.Offset(size.width, 0f),
                                strokeWidth = 2.dp.toPx(),
                                pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 10f))
                            )
                        },
                    color = Color.Transparent
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Text Đặt lại và Đánh giá (đã đảo vị trí) với gạch chân màu xanh
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = -6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween


                ) {
                    // Text Đặt lại (bên trái)
                    Text(
                        text = "Đặt lại",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                        modifier = Modifier
                            .drawBehind {
                                drawLine(
                                    color = Color(0xFF5C9BE3),
                                    start = androidx.compose.ui.geometry.Offset(0f, size.height),
                                    end = androidx.compose.ui.geometry.Offset(size.width, size.height),
                                    strokeWidth = 2.dp.toPx()
                                )
                            }
                    )

                    // Text Đánh giá (bên phải)
                    Text(
                        text = "Đánh giá",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                        modifier = Modifier
                            .drawBehind {
                                drawLine(
                                    color = Color(0xFF5C9BE3),
                                    start = androidx.compose.ui.geometry.Offset(0f, size.height),
                                    end = androidx.compose.ui.geometry.Offset(size.width, size.height),
                                    strokeWidth = 2.dp.toPx()
                                )
                            }
                    )
                }
            }
        }

        // Icon xe đặt ngoài card, góc trên bên phải
        Image(
            painter = painterResource(id = chuyenDi.iconXe),
            contentDescription = chuyenDi.loaiXe,
            modifier = Modifier
                .size(90.dp)
                .align(Alignment.TopEnd)
                .offset(x = -303.dp, y = -10.dp)
        )
    }
}
