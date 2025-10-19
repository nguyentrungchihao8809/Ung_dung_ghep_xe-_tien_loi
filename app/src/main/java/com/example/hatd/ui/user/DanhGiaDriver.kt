package com.example.hatd.ui.user.DanhGiaDriverScreen

// 🧩 Các import cần thiết cho Compose UI
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hatd.R
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.geometry.CornerRadius

// 🔹 Cho phép dùng FlowRow (vẫn là experimental API)
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DanhGiaDriverScreen(onClose: () -> Unit = {}) {

    // State quản lý số sao người dùng chọn
    var rating by remember { mutableStateOf(0) }

    // Danh sách các lời khen
    val compliments = listOf(
        "Thân thiện", "Cẩn thận", "Đúng giờ",
        "Thái độ tốt", "Sạch sẽ", "Đồng phục gọn gàng"
    )

    // ✅ State quản lý các lời khen đã được chọn
    var selectedCompliments by remember { mutableStateOf(setOf<String>()) }

    // 🔹 Toàn bộ giao diện được đặt trong Column chính
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5FAFE)) // nền xanh nhạt tổng thể
            .padding(16.dp)
    ) {

        // Thanh tiêu đề có nút “X” đóng
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            // Nút "X" ở góc trái
            Text(
                text = "X",
                color = Color.Gray,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .clickable { onClose() } // khi bấm gọi hàm đóng
                    .padding(start = 8.dp)
            )

            // Tiêu đề căn giữa
            Text(
                text = "Đánh giá tài xế",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = Color(0xFF000000),
                modifier = Modifier.align(Alignment.Center)
            )
        }

        //  Thông tin tài xế
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

                // Biển số xe + tên xe
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

        // Khung đánh giá tổng thể
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                //KHUNG NÉT ĐỨT bao quanh 3 phần: thông điệp, sao và lời khen
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp)
                        .drawBehind {
                            val stroke = Stroke(
                                width = 6f, // độ dày nét
                                pathEffect = PathEffect.dashPathEffect(floatArrayOf(28f, 14f)) // nét đứt
                            )
                            drawRoundRect(
                                color = Color.Black, // màu đen
                                style = stroke,
                                cornerRadius = CornerRadius(48f, 48f) // bo góc mềm
                            )
                        }
                        .padding(15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    // Hộp thông điệp gợi ý
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFEEEEEE)),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.dp, Color(0xFF0081F1)) // viền xanh
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "HATD luôn lắng nghe điều bạn nói.",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Có điều gì bạn muốn nhắn gửi đến HATD không?\n" +
                                        "Chia sẻ ngay cảm nghĩ của bạn giúp chúng tôi mang đến\n" +
                                        "những chuyến đi ngày càng tuyệt hơn.",
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // HÀNG SAO ĐÁNH GIÁ
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color(0xFF2196F3), RoundedCornerShape(12.dp)) // viền xanh dương đậm
                            .padding(vertical = 12.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            for (i in 1..5) {
                                IconButton(onClick = { rating = i }) {
                                    Icon(
                                        painter = painterResource(
                                            id = if (i <= rating) R.drawable.sao else R.drawable.saorong
                                        ),
                                        contentDescription = "Sao $i",
                                        tint = Color(0xFFFFC107),
                                        modifier = Modifier.size(40.dp)
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // LỜI KHEN
                    Text(
                        text = "Gửi lời khen của bạn",
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    // Hàng chứa các “chip” lời khen
                    FlowRow(modifier = Modifier.fillMaxWidth()) {
                        compliments.forEach { compliment ->
                            val isSelected = compliment in selectedCompliments
                            Surface(
                                shape = RoundedCornerShape(50),
                                color = if (isSelected) Color(0xFF0081F1) else Color(0xFFF0F0F0),
                                modifier = Modifier
                                    .padding(horizontal = 4.dp, vertical = 4.dp)
                                    .clickable {
                                        selectedCompliments =
                                            if (isSelected) selectedCompliments - compliment
                                            else selectedCompliments + compliment
                                    }
                            ) {
                                Text(
                                    text = compliment,
                                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                                    color = if (isSelected) Color.White else Color.Black,
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // NÚT GỬI ĐI
                    Button(
                        onClick = { /* TODO: Gửi dữ liệu đánh giá */ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0081F1)),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .padding(top = 40.dp)
                    ) {
                        Text(
                            text = "Gửi đi",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
