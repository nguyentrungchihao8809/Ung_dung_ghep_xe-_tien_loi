package com.example.hatd.ui.user.HoSoUser

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hatd.R

@Composable
fun HoSoUserScreen(
    onBackClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Nút quay lại
        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 48.dp, start = 10.dp)
                .size(40.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Quay lại",
                tint = Color.Black,
                modifier = Modifier.size(34.dp)
            )
        }

        // Nội dung chính
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            // KHUNG THÔNG TIN USER
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color(0xFF4ABDE0), RoundedCornerShape(16.dp)),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .clip(RoundedCornerShape(16.dp))
                ) {
                    // Ảnh nền của khung user
                    Image(
                        painter = painterResource(id = R.drawable.anhnensauuser),
                        contentDescription = "Ảnh nền khung user",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.matchParentSize(),
                        alpha = 0.4f // làm mờ ảnh nền
                    )

                    // Ảnh user + thông tin
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.anhuser),
                            contentDescription = "Avatar",
                            modifier = Modifier
                                .size(100.dp)
                                .clip(RoundedCornerShape(50.dp))
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "Kiim Bầu - 5.0 ",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    color = Color.Black
                                )

                                Image(
                                    painter = painterResource(id = R.drawable.ngoisao),
                                    contentDescription = "Đánh giá 5 sao",
                                    modifier = Modifier
                                        .size(20.dp)
                                        .padding(start = 2.dp)
                                )
                            }

                            Text(
                                text = "0375242005",
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // CHI TIẾT NGƯỜI DÙNG — viền nét đứt 
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
            ) {
                // Nội dung trong khung
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    ProfileRow(label = "Họ tên", value = "Kiim Bâu")
                    Divider(color = Color.LightGray)
                    ProfileRow(label = "Giới tính", value = "Chọn")
                    Divider(color = Color.LightGray)
                    ProfileRow(label = "Số căn cước công dân", value = "08XXX0017XX")
                    Divider(color = Color.LightGray)
                    ProfileRow(label = "Số điện thoại", value = "0375242005")
                }

                // Vẽ viền nét đứt thủ công
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .drawBehind {
                            val stroke = 3.dp.toPx()
                            val dash = 15.dp.toPx()
                            val gap = 10.dp.toPx()

                            // top edge
                            var x = 0f
                            while (x < size.width) {
                                val xEnd = (x + dash).coerceAtMost(size.width)
                                drawLine(
                                    color = Color.LightGray,
                                    start = Offset(x, 0f),
                                    end = Offset(xEnd, 0f),
                                    strokeWidth = stroke
                                )
                                x += dash + gap
                            }

                            // bottom edge
                            x = 0f
                            val yBottom = size.height
                            while (x < size.width) {
                                val xEnd = (x + dash).coerceAtMost(size.width)
                                drawLine(
                                    color = Color.LightGray,
                                    start = Offset(x, yBottom),
                                    end = Offset(xEnd, yBottom),
                                    strokeWidth = stroke
                                )
                                x += dash + gap
                            }

                            // left edge
                            var y = 0f
                            while (y < size.height) {
                                val yEnd = (y + dash).coerceAtMost(size.height)
                                drawLine(
                                    color = Color.LightGray,
                                    start = Offset(0f, y),
                                    end = Offset(0f, yEnd),
                                    strokeWidth = stroke
                                )
                                y += dash + gap
                            }

                            // right edge
                            y = 0f
                            val xRight = size.width
                            while (y < size.height) {
                                val yEnd = (y + dash).coerceAtMost(size.height)
                                drawLine(
                                    color = Color.LightGray,
                                    start = Offset(xRight, y),
                                    end = Offset(xRight, yEnd),
                                    strokeWidth = stroke
                                )
                                y += dash + gap
                            }
                        }
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            //  HỘP CẢM ƠN
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(14.dp))
                    .border(1.5.dp, Color(0xFF4ABDE0), RoundedCornerShape(14.dp))
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(Color(0xFFFEE8E8), Color(0xFFD5F1FF))
                        )
                    )
                    .padding(vertical = 26.dp, horizontal = 20.dp)
            ) {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Cảm ơn bạn đã tin tưởng và đồng hành cùng HATD.",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "HATD rất vui khi được cùng bạn chia sẻ những khoảnh khắc đáng nhớ.",
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                }

                //  ảnh ở góc dưới
                Image(
                    painter = painterResource(id = R.drawable.thatim),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .offset(x = 25.dp, y = 25.dp)
                        .size(60.dp)
                )
            }

            Spacer(modifier = Modifier.height(35.dp))

            //  NÚT ĐĂNG XUẤT
            TextButton(onClick = onLogoutClick) {
                Text(
                    text = "Đăng xuất",
                    fontSize = 22.sp, // chữ to hơn
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

// DÒNG THÔNG TIN USER
@Composable
fun ProfileRow(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 10.dp)) {
        Text(text = label, fontSize = 15.sp, color = Color.Gray)
        Text(
            text = value,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 17.sp
        )
    }
}
