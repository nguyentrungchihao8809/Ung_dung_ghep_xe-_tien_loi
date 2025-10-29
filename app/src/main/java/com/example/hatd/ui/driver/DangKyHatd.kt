package com.example.hatd.ui.driver.DangKyHatd

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hatd.R

@Composable
fun DangKyHATDScreen(navController: NavController) {
    var anhChanDungMoRong by remember { mutableStateOf(false) }
    var bangLaiXeMoRong by remember { mutableStateOf(false) }
    var canCuocMoRong by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Ảnh nền
        Image(
            painter = painterResource(id = R.drawable.nendangkyhatd),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Tiêu đề
            Text(
                text = "Quy trình đăng ký HATDBIKE",
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF333333),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .offset(y = (35).dp)
                    .border(3.dp, Color(0xFF4ABDE0), RoundedCornerShape(12.dp))
                    .background(Color.White, RoundedCornerShape(12.dp))
                    .padding(horizontal = 24.dp, vertical = 18.dp)
            )

            Spacer(modifier = Modifier.height(100.dp))

            // Box để chứa Card và Ảnh chồng lên nhau
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopCenter
            ) {
                // Card trắng chính
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(680.dp)
                        .padding(top = 125.dp)
                        .border(3.dp, Color(0xFF4ABDE0), RoundedCornerShape(16.dp)),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .padding(top = 70.dp, bottom = 30.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Box màu xám với viền xanh chứa thông báo chào mừng và mô tả
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFFD4D4D4), RoundedCornerShape(12.dp))
                                .border(2.dp, Color(0xFF4CDFE6), RoundedCornerShape(12.dp))
                                .padding(5.dp)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                // Thông báo chào mừng
                                Text(
                                    text = "Chào mừng bạn trở thành tài xế của HATD",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF333333),
                                    textAlign = TextAlign.Center
                                )

                                Spacer(modifier = Modifier.height(12.dp))

                                // Mô tả
                                Text(
                                    text = "Hãy đảm bảo thông tin xe chính xác, tuân thủ luật giao thông và luôn giữ thái độ thân thiện với hành khách. Cùng HATD mang đến những chuyến đi an toàn và đáng tin cậy.",
                                    fontSize = 14.sp,
                                    color = Color(0xFF000000),
                                    textAlign = TextAlign.Center,
                                    lineHeight = 20.sp
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // Các mục dropdown
                        MucMoRong(
                            tieuDe = "Ảnh Chân Dung",
                            daMoRong = anhChanDungMoRong,
                            khiBamVao = { anhChanDungMoRong = !anhChanDungMoRong },
                            chieuDaiGachXanh = 135f // Tự chỉnh độ dài này
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        MucMoRong(
                            tieuDe = "Bằng Lái Xe",
                            daMoRong = bangLaiXeMoRong,
                            khiBamVao = { bangLaiXeMoRong = !bangLaiXeMoRong },
                            chieuDaiGachXanh = 105f // Tự chỉnh độ dài này
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        MucMoRong(
                            tieuDe = "Căn cước công dân",
                            daMoRong = canCuocMoRong,
                            khiBamVao = { canCuocMoRong = !canCuocMoRong },
                            chieuDaiGachXanh = 165f // Tự chỉnh độ dài này
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        // Nút Đăng ký
                        Button(
                            onClick = { /* ////// */ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .offset(y=20.dp)
                                .height(90.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF25C8CD)
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = "Đăng ký",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                }

                // Ảnh minh họa - đè lên Card
                Image(
                    painter = painterResource(id = R.drawable.dangkyhatd),
                    contentDescription = "HATD Illustration",
                    modifier = Modifier
                        .width(362.dp)
                        .height(250.dp)
                        .offset(x = 0.dp, y = -80.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .align(Alignment.TopCenter),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun MucMoRong(
    tieuDe: String,
    daMoRong: Boolean,
    khiBamVao: () -> Unit,
    chieuDaiGachXanh: Float = 160f // Thêm tham số này để chỉnh riêng từng cái
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    onClick = khiBamVao,
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                )
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = tieuDe,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF333333),
                modifier = Modifier
                    .offset(x = 8.dp)
                    .drawBehind {
                        val doLeY = -7.dp.toPx()
                        val doLeX = 0.dp.toPx()
                        val banKinhGoc = 8.dp.toPx()

                        // Tự điền số vào đây
                        val chieuDaiGachXanhPx = chieuDaiGachXanh.dp.toPx()
                        val chieuDaiGachDen = 330.dp.toPx()

                        // Gạch ngang xanh với bo góc
                        drawRoundRect(
                            color = Color(0xFF01A6AA),
                            topLeft = androidx.compose.ui.geometry.Offset(doLeX, size.height + doLeY - 2.dp.toPx()),
                            size = androidx.compose.ui.geometry.Size(chieuDaiGachXanhPx, 6.dp.toPx()),
                            cornerRadius = androidx.compose.ui.geometry.CornerRadius(banKinhGoc, banKinhGoc)
                        )

                        // Gạch ngang đen phía dưới
                        drawRoundRect(
                            color = Color(0xFF000000),
                            topLeft = androidx.compose.ui.geometry.Offset(doLeX, size.height + doLeY + 6.dp.toPx()),
                            size = androidx.compose.ui.geometry.Size(chieuDaiGachDen, 2.dp.toPx()),
                            cornerRadius = androidx.compose.ui.geometry.CornerRadius(banKinhGoc / 2, banKinhGoc / 2)
                        )
                    }
            )

            // Icon ảnh thay thế dấu cộng
            Image(
                painter = painterResource(id = R.drawable.cong),
                contentDescription = if (daMoRong) "Thu gọn" else "Mở rộng",
                modifier = Modifier
                    .size(28.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Fit
            )
        }
    }
}
