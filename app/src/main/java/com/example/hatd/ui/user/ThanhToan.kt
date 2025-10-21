package com.example.hatd.ui.user.ThanhToan

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hatd.R

@Composable
fun ThanhToanScreen(
    onBackClick: () -> Unit = {},
    onSelectPayment: (String) -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // 🖼 Ảnh nền toàn màn hình
        Image(
            painter = painterResource(id = R.drawable.dongtien),
            contentDescription = "Ảnh nền thanh toán",
            modifier = Modifier
                .fillMaxSize(), // phủ toàn màn hình
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            // 🔙 Nút quay lại
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .size(44.dp)
                    .background(Color.White.copy(alpha = 0.8f), CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(220.dp))

            // 🏷 Tiêu đề + gạch xanh
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Phương thức thanh toán khả dụng",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .width(180.dp)
                        .height(3.dp)
                        .background(Color(0xFF1565C0), RoundedCornerShape(50))
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
            val iconSize = 48.dp

            // 💵 Thanh toán tiền mặt
            ThanhToanItem(
                icon = R.drawable.dola,
                text = "Thanh toán tiền mặt",
                iconSize = iconSize,
                onClick = { onSelectPayment("cash") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // --- or ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                        .background(Color.Gray.copy(alpha = 0.5f))
                )
                Text(
                    text = "  or  ",
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                        .background(Color.Gray.copy(alpha = 0.5f))
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Các phương thức khác
            ThanhToanItem(R.drawable.momo, "Thanh toán MoMo", iconSize) { onSelectPayment("momo") }
            Spacer(modifier = Modifier.height(12.dp))
            ThanhToanItem(R.drawable.zalopay, "Thanh toán Zalo Pay", iconSize) { onSelectPayment("zalopay") }
            Spacer(modifier = Modifier.height(12.dp))
            ThanhToanItem(R.drawable.vnpay, "Thanh toán VNPAY", iconSize) { onSelectPayment("vnpay") }
            Spacer(modifier = Modifier.height(12.dp))
            ThanhToanItem(R.drawable.vietqr, "Thanh toán VIETQR", iconSize) { onSelectPayment("vietqr") }
        }
    }
}

@Composable
fun ThanhToanItem(
    icon: Int,
    text: String,
    iconSize: Dp = 64.dp,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(3.dp, RoundedCornerShape(20.dp))
            .border(1.dp, Color.Black, RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White.copy(alpha = 0.9f)) // làm trong nhẹ để thấy nền
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = text,
            modifier = Modifier
                .size(iconSize)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = text,
            fontSize = 17.sp,
            color = Color.Black,
            fontWeight = FontWeight.Medium
        )
    }
}
