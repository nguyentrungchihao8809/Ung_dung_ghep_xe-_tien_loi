package com.example.hatd.ui.user.GhiChu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hatd.R

@Composable
fun GhiChuScreen(navController: NavController) {
    var noteText by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        // Nút Back ở góc trái trên
        Image(
            painter = painterResource(id = R.drawable.backicon),
            contentDescription = "Quay lại",
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 24.dp, y = 40.dp)
                .size(40.dp)
                .clickable(
                    indication = null,
                    interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() }
                ) {
                    navController.navigate("XacNhanDiemDon")
                }
        )

        // Box lớn chứa toàn bộ header và khung ghi chú
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .offset(y = 600.dp) // Di chuyển xuống
                .border(2.dp, Color.Black, RoundedCornerShape(12.dp))
                .background(Color.White, RoundedCornerShape(12.dp))
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                // Header
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Icon đóng
                    Image(
                        painter = painterResource(id = R.drawable.close),
                        contentDescription = "Dong",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(
                                indication = null,
                                interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() }
                            ) { navController.navigate("XacNhanDiemDon") }
                    )

                    Spacer(Modifier.width(16.dp))

                    Text(
                        "Ghi chú cho tài xế",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Start
                    )

                    Text(
                        "Xong",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF00BCD4),
                        modifier = Modifier.clickable(
                            indication = null,
                            interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() }
                        ) { navController.navigate("GhiChu") }
                    )
                }

                // Khung nhập ghi chú với viền nét đứt
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 16.dp)
                        .height(150.dp)
                        .VienChamCham(2.dp, Color(0xFF00BCD4), 12.dp)
                        .background(Color.White, RoundedCornerShape(12.dp))
                        .padding(12.dp)
                ) {
                    if (noteText.isEmpty()) {
                        Text(
                            "Thêm ghi chú cho tài xế",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }

                    BasicTextField(
                        value = noteText,
                        onValueChange = { noteText = it },
                        modifier = Modifier.fillMaxSize(),
                        textStyle = TextStyle(
                            fontSize = 14.sp,
                            color = Color.Black
                        )
                    )
                }
            }
        }

    }
}

// Hàm vẽ viền chấm chấm
fun Modifier.VienChamCham(độDày: Dp, màu: Color, bo: Dp) = drawBehind {
    drawRoundRect(
        color = màu,
        style = Stroke(
            width = độDày.toPx(),
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(30f, 10f))
        ),
        cornerRadius = CornerRadius(bo.toPx())
    )
}
