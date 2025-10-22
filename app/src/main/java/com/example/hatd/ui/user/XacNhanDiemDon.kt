package com.example.hatd.ui.user.XacNhanDiemDon // Khai báo package chứa file này

// Import các thư viện cần thiết của Jetpack Compose và Android
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import com.example.hatd.R // Import tài nguyên hình ảnh từ thư mục res

@Composable // Đánh dấu đây là một hàm Composable (dùng để tạo UI trong Compose)
fun XacNhanDiemDonScreen() { // Hàm chính hiển thị màn hình xác nhận điểm đón
    Box( // Dùng Box để chồng nhiều phần tử UI lên nhau (z-index)
        modifier = Modifier.fillMaxSize() // Chiếm toàn bộ kích thước màn hình
    ) {
        // Nút Back ở góc trái trên màn hình
        Image(
            painter = painterResource(id = R.drawable.back), // Ảnh icon "back"
            contentDescription = "Quay lại", // Mô tả ảnh (cho trợ năng)
            modifier = Modifier
                .offset(x = 20.dp, y = 55.dp) // Dịch vị trí xuống dưới một chút
                .size(40.dp) // Kích thước icon là 40x40dp
        )

        // Tạo Bottom Sheet (khung trắng có góc bo tròn ở dưới cùng)
        Box(
            modifier = Modifier
                .fillMaxWidth() // Chiều ngang phủ toàn bộ màn hình
                .align(Alignment.BottomCenter) // Căn ở phía dưới giữa màn hình
                .graphicsLayer { // Tùy chỉnh hiệu ứng hiển thị của layer
                    shadowElevation = 16f // Đổ bóng nhẹ cho khung
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp) // Bo tròn góc trên
                    clip = true // Cắt theo viền bo tròn
                }
                .background( // Đặt màu nền trắng cho khung
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                )
        ) {
            // Ảnh nền cho phần Bottom Sheet (hiển thị phía sau)
            Image(
                painter = painterResource(id = R.drawable.xacnhan), // Ảnh minh họa nền
                contentDescription = "Điểm đón", // Mô tả ảnh
                modifier = Modifier
                    .fillMaxWidth() // Phủ toàn bộ chiều ngang
                    .height(300.dp) // Chiều cao là 300dp
                    .align(Alignment.TopCenter), // Căn giữa theo trục ngang, nằm trên
                contentScale = ContentScale.Crop // Cắt và phóng ảnh sao cho vừa khung
            )

            // Phần nội dung text + button hiển thị đè lên ảnh nền
            Column(
                modifier = Modifier
                    .fillMaxWidth() // Chiều ngang full
                    .padding(24.dp), // Cách lề trong 24dp
                horizontalAlignment = Alignment.Start // Căn lề trái
            ) {
                // Box chứa tiêu đề và địa chỉ điểm đón
                Box(
                    modifier = Modifier
                        .fillMaxWidth() // Chiều ngang phủ hết
                        .clip(RoundedCornerShape(12.dp)) // Bo tròn các góc
                        .background( // Nền trắng cho box
                            color = Color.White,
                            shape = RoundedCornerShape(12.dp)
                        )
                ) {
                    // Ảnh nền cho box nhỏ bên trong
                    Image(
                        painter = painterResource(id = R.drawable.bxacnhan), // Ảnh nền trang trí
                        contentDescription = "Nền box", // Mô tả ảnh
                        modifier = Modifier
                            .fillMaxWidth() // Chiều ngang full
                            .height(90.dp), // Giảm chiều cao để box gọn hơn
                        contentScale = ContentScale.Crop // Cắt và phóng ảnh phù hợp khung
                    )

                    // Nội dung chữ nằm đè lên ảnh nền
                    Column(
                        modifier = Modifier.padding(16.dp) // Cách lề trong 16dp
                    ) {
                        // Dòng tiêu đề “Điểm đón”
                        Text(
                            text = "Điểm đón", // Nội dung chữ
                            fontSize = 18.sp, // Kích thước chữ
                            fontWeight = FontWeight.Bold, // Chữ đậm
                            color = Color(0xFF000000), // Màu đen
                            modifier = Modifier.fillMaxWidth() // Chiều ngang full
                        )

                        Spacer(modifier = Modifier.height(8.dp)) // Tạo khoảng cách 8dp giữa 2 dòng

                        // Dòng địa chỉ điểm đón
                        Text(
                            text = "Đại học Giao Thông Vận Tải TP. Hồ Chí Minh", // Địa chỉ
                            fontSize = 14.sp, // Cỡ chữ nhỏ hơn
                            fontWeight = FontWeight.Normal, // Chữ thường
                            color = Color(0xFF333333), // Màu xám đậm
                            lineHeight = 22.sp, // Khoảng cách giữa các dòng
                            modifier = Modifier.fillMaxWidth() // Chiều ngang full
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp)) // Khoảng cách giữa box và dòng chữ dưới

                // Dòng chữ “Thêm chi tiết” (người dùng có thể bấm vào)
                Text(
                    text = "Thêm chi tiết", // Nội dung chữ
                    fontSize = 13.sp, // Cỡ chữ nhỏ
                    fontWeight = FontWeight.Normal, // Chữ thường
                    color = Color(0xFF3085E0), // Màu xanh dương
                    modifier = Modifier
                        .fillMaxWidth() // Chiều ngang full
                        .offset(y = 50.dp) // Dịch xuống một chút cho dễ nhìn
                        .clickable { /* Xử lý khi người dùng bấm vào (ví dụ: mở nhập ghi chú) */ }
                )

                Spacer(modifier = Modifier.height(24.dp)) // Khoảng cách trước nút

                // Nút “Xác nhận điểm đón”
                Button(
                    onClick = { /* Xử lý khi bấm nút xác nhận điểm đón */ },
                    modifier = Modifier
                        .fillMaxWidth() // Chiều ngang full
                        .offset(y = 40.dp) // Dịch nút xuống một chút
                        .height(56.dp), // Chiều cao của nút
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF5C9BE3)
                    ),
                    shape = RoundedCornerShape(10.dp) // Bo tròn nhẹ các góc nút
                ) {
                    // Nội dung chữ trên nút
                    Text(
                        text = "Xác nhận điểm đón", // Chữ hiển thị
                        fontSize = 16.sp, // Kích thước chữ
                        fontWeight = FontWeight.SemiBold, // Chữ đậm vừa
                        color = Color.White // Màu chữ trắng
                    )
                }
            }
        }
    }
}
