package com.example.hatd.ui.user.XacNhanDiemDon // Khai báo package chứa file này

// Import các thư viện cần thiết của Jetpack Compose và Android
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import androidx.navigation.NavController
import com.example.hatd.R // Import tài nguyên hình ảnh từ thư mục res

@Composable
fun XacNhanDiemDonScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Nút Back ở góc trái trên màn hình
        Image(
            painter = painterResource(id = R.drawable.backicon),
            contentDescription = "Quay lại",
            modifier = Modifier
                .offset(x = 20.dp, y = 55.dp)
                .size(40.dp)
        )

        // Tạo Bottom Sheet
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
            // Ảnh nền cho phần Bottom Sheet
            Image(
                painter = painterResource(id = R.drawable.xacnhan),
                contentDescription = "Điểm đón",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .align(Alignment.TopCenter),
                contentScale = ContentScale.Crop
            )

            // Phần nội dung text + button
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.Start
            ) {
                // Box chứa tiêu đề và địa chỉ điểm đón
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(12.dp)
                        )
                ) {
                    // Ảnh nền cho box nhỏ bên trong
                    Image(
                        painter = painterResource(id = R.drawable.bxacnhan),
                        contentDescription = "Nền box",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(90.dp),
                        contentScale = ContentScale.Crop
                    )

                    // Nội dung chữ
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Điểm đón",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF000000),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Đại học Giao Thông Vận Tải TP. Hồ Chí Minh",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFF333333),
                            lineHeight = 22.sp,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Dòng chữ "Thêm chi tiết" - Xóa hiệu ứng xám
                Text(
                    text = "Thêm chi tiết",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF3085E0),
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = 50.dp)
                        .clickable(
                            indication = null,
                            interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() }
                        ) {
                            navController.navigate("GhiChu")
                        }
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Nút "Xác nhận điểm đón"
                Button(
                    onClick = { navController.navigate("XacNhanDatXe") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = 40.dp)
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF5C9BE3)
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "Xác nhận điểm đón",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
            }
        }
    }
}
