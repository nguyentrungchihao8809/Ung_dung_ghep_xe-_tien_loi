package com.example.hatd.ui.user.XacNhanDatXe

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.hatd.R

@Composable
fun XacNhanDatXeScreen(navController: NavController) {
    var selectedVehicle by remember { mutableStateOf("bike") } // "bike" hoặc "car"

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Nút Back ở góc trái trên
        Image(
            painter = painterResource(id = R.drawable.backicon),
            contentDescription = "Quay lại",
            modifier = Modifier
                .offset(x = 20.dp, y = 55.dp)
                .size(40.dp)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    navController.navigate("XacNhanDiemDon")
                }
        )

        // Bottom Sheet chứa nội dung chính
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .graphicsLayer {
                    shadowElevation = 16f
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                    clip = true
                }
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                )
        ) {
            // Ảnh nền cho phần Bottom Sheet (hiển thị phía sau)
            Image(
                painter = painterResource(id = R.drawable.xacnhan),
                contentDescription = "Nền chọn xe",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .align(Alignment.TopCenter),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.Start
            ) {
                // Tiêu đề Xe máy
                Text(
                    text = "Xe máy",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF000000)
                )

                Spacer(modifier = Modifier.height(0.dp))

                // Card chọn xe máy (HATD bike)
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Box thông tin xe
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(
                                color = if (selectedVehicle == "bike") Color(0xFFE3F2FD) else Color(
                                    0xFFF5F5F5
                                ),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .border(
                                width = if (selectedVehicle == "bike") 2.dp else 0.dp,
                                color = if (selectedVehicle == "bike") Color(0xFF5C9BE3) else Color.Transparent,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .clickable { selectedVehicle = "bike" }
                    ) {
                        // Ảnh nền cho box xe máy
                        Image(
                            painter = painterResource(id = R.drawable.bxacnhan),
                            contentDescription = "Nền box",
                            modifier = Modifier
                                .fillMaxSize()
                                .offset(x = 0.dp, y = 0.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 90.dp, end = 10.dp, top = 10.dp, bottom = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Thông tin xe
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = "HATD bike",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color(0xFF000000)
                                )
                                Text(
                                    text = "Dự kiến lúc: 7:55",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color(0xFF666666)
                                )
                            }

                            Spacer(modifier = Modifier.width(0.dp))

                            // Giá tiền
                            Text(
                                text = "15.000đ",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF000000)
                            )
                        }
                    }

                    // Ảnh xe máy - đặt trên box với zIndex cao hơn
                    Image(
                        painter = painterResource(id = R.drawable.xemay),
                        contentDescription = "Xe máy",
                        modifier = Modifier
                            .size(130.dp)
                            .offset(x = -35.dp, y = -30.dp)
                            .align(Alignment.CenterStart)
                            .zIndex(1f)
                    )
                }

                Spacer(modifier = Modifier.height(0.dp))

                // Tiêu đề Xe ô tô
                Text(
                    text = "Xe ô tô",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF000000)
                )

                Spacer(modifier = Modifier.height(0.dp))

                // Card chọn xe ô tô (HATD car)
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Box thông tin xe
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(
                                color = if (selectedVehicle == "car") Color(0xFFE3F2FD) else Color(
                                    0xFFF5F5F5
                                ),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .border(
                                width = if (selectedVehicle == "car") 2.dp else 0.dp,
                                color = if (selectedVehicle == "car") Color(0xFF5C9BE3) else Color.Transparent,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .clickable { selectedVehicle = "car" }
                    ) {
                        // Ảnh nền cho box xe ô tô
                        Image(
                            painter = painterResource(id = R.drawable.bxacnhan),
                            contentDescription = "Nền box",
                            modifier = Modifier
                                .fillMaxSize()
                                .offset(x = 0.dp, y = 0.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 90.dp, end = 10.dp, top = 10.dp, bottom = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Thông tin xe
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = "HATD car",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color(0xFF000000)
                                )
                                Text(
                                    text = "Dự kiến lúc: 10:45",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color(0xFF666666)
                                )
                            }

                            Spacer(modifier = Modifier.width(0.dp))

                            // Giá tiền
                            Text(
                                text = "40.000đ",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF000000)
                            )
                        }
                    }

                    // Ảnh xe ô tô - đặt trên box với zIndex cao hơn
                    Image(
                        painter = painterResource(id = R.drawable.xeoto),
                        contentDescription = "Xe ô tô",
                        modifier = Modifier
                            .size(90.dp)
                            .offset(x = -15.dp, y = -10.dp)
                            .align(Alignment.CenterStart)
                            .zIndex(1f)
                    )
                }

                Spacer(modifier = Modifier.height(0.dp))

                // Row chứa các phương thức thanh toán
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Phương thức thanh toán 1 (icon + text)
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.dola),
                            contentDescription = "Phương thức thanh toán",
                            modifier = Modifier.size(27.dp)
                        )
                        Spacer(modifier = Modifier.width(0.dp))
                        Text(
                            text = "Phương thức toán",
                            fontSize = 12.sp,
                            color = Color(0xFF666666)
                        )
                    }

                    // Phương thức thanh toán 2
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            // Xử lý khi click vào Hẹn giờ
                            navController.navigate("HenGio")
                        }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.clock),
                            contentDescription = "Hẹn giờ",
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Hẹn giờ",
                            fontSize = 12.sp,
                            color = Color(0xFF666666)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Nút "Đặt xe"
                Button(
                    onClick = { /* Xử lý đặt xe */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF5C9BE3)
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "Đặt xe",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
            }
        }
    }
}
