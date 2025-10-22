package com.example.hatd.ui.auth.otp // Khai báo package chứa file này (theo cấu trúc thư mục của project)

import androidx.compose.foundation.Image // Dùng để hiển thị hình ảnh trong Compose
import androidx.compose.foundation.background // Dùng để đặt màu nền cho layout
import androidx.compose.foundation.clickable // Cho phép phần tử có thể bấm (click)
import androidx.compose.foundation.layout.* // Chứa các thành phần layout như Box, Row, Column
import androidx.compose.foundation.shape.RoundedCornerShape // Tạo bo góc cho thành phần UI
import androidx.compose.foundation.text.BasicTextField // Ô nhập liệu cơ bản (không có style sẵn)
import androidx.compose.foundation.text.KeyboardOptions // Cấu hình loại bàn phím (số, chữ, mật khẩu,…)
import androidx.compose.material3.* // Thư viện Material 3 (nút, text, màu,…)
import androidx.compose.runtime.* // Dùng cho state (remember, mutableStateOf,…)
import androidx.compose.ui.* // Các modifier cơ bản (fillMaxSize, offset,…)
import androidx.compose.ui.draw.alpha // Điều chỉnh độ trong suốt
import androidx.compose.ui.draw.drawBehind // Cho phép tự vẽ đằng sau nội dung composable
import androidx.compose.ui.focus.FocusRequester // Đối tượng để yêu cầu focus (nhập liệu tự động)
import androidx.compose.ui.focus.focusRequester // Modifier để liên kết FocusRequester
import androidx.compose.ui.geometry.CornerRadius // Dùng để vẽ bo góc khi custom viền
import androidx.compose.ui.graphics.* // Dùng cho màu sắc, stroke, pathEffect
import androidx.compose.ui.graphics.drawscope.Stroke // Kiểu vẽ đường viền
import androidx.compose.ui.layout.ContentScale // Kiểm soát tỉ lệ hiển thị hình ảnh
import androidx.compose.ui.res.painterResource // Dùng để lấy ảnh từ resource (drawable)
import androidx.compose.ui.text.TextStyle // Kiểu chữ (font size, màu, canh lề,…)
import androidx.compose.ui.text.font.FontWeight // Độ đậm của chữ
import androidx.compose.ui.text.input.KeyboardType // Loại bàn phím (số, email,…)
import androidx.compose.ui.text.style.TextAlign // Căn giữa, trái, phải cho text
import androidx.compose.ui.unit.* // Dùng cho đơn vị đo (dp, sp,…)
import com.example.hatd.R // Thư mục chứa tài nguyên (ảnh, màu, string,…)

//MÀN HÌNH NHẬP MÃ OTP
@Composable
fun OtpScreen() {
    var maOTP by remember { mutableStateOf("") } // Biến trạng thái lưu chuỗi mã OTP người dùng nhập

    Box( // Layout chính bao trùm toàn màn hình
        modifier = Modifier
            .fillMaxSize() // Chiếm toàn bộ kích thước màn hình
            .background(Color(0xFFF5F5F5)) // Nền xám nhạt
            .padding(16.dp) // Cách mép màn hình 16dp
    ) {
        // -----Nút quay lại -----
        Icon(
            painter = painterResource(id = R.drawable.back), // Ảnh icon "đóng"
            contentDescription = "Quay lại", // Mô tả cho trình đọc màn hình
            modifier = Modifier
                .offset(y = 27.dp) // Đẩy icon xuống 27dp
                .size(50.dp) // Kích thước 35x35dp
                .clickable { /* Quay lại */ }, // Khi bấm vào icon (chưa có xử lý)
            tint = Color.Black // Màu đen cho icon
        )

        // -----Nội dung chính -----
        Column(
            modifier = Modifier
                .fillMaxWidth() // Chiếm hết chiều ngang
                .align(Alignment.TopCenter) // Căn giữa phía trên
                .offset(y = 80.dp), // Đẩy toàn bộ cột xuống 80dp
            horizontalAlignment = Alignment.CenterHorizontally // Căn giữa các phần tử theo trục ngang
        ) {
            // -----Box tiêu đề OTP -----
            BoxDecor( // Viền ngoài
                viền = 5.dp,
                màu = Color(0xFF00BCD4),
                bo = 24.dp,
                pad = 10.dp
            ) {
                BoxDecor( // Lớp trong
                    viền = 4.dp,
                    màu = Color(0xFFBAB7B7),
                    bo = 16.dp,
                    nền = Color(0xFFE9E9E9),
                    pad = 16.dp
                ) {
                    Text("Mã xác thực OTP", fontSize = 22.sp, fontWeight = FontWeight.Bold) // Dòng chữ tiêu đề
                }
            }

            // -----Box chứa nội dung OTP -----
            BoxDecor(height = 500.dp, offsetY = 50.dp, viền = 5.dp) { // Box lớn chứa phần nhập OTP
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Khung mô tả hướng dẫn
                    BoxDecor(
                        viền = 4.dp,
                        màu = Color(0xFFBAB7B7),
                        bo = 16.dp,
                        nền = Color(0xFFE9E9E9),
                        pad = 30.dp
                    ) {
                        Text(
                            "Nhập mã gồm 6 chữ số được gửi đến\nthông qua tin nhắn.",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            lineHeight = 20.sp
                        )
                    }

                    Spacer(Modifier.height(32.dp)) // Khoảng cách 32dp

                    Box(Modifier.offset(y = 30.dp)) { // Đẩy ô nhập OTP xuống
                        OTPInputField(maOTP) { if (it.length <= 6) maOTP = it } // Trường nhập mã OTP
                    }
                }
            }

            // ----- Nút "Gửi lại mã" -----
            BoxDecor(
                viền = 4.dp,
                màu = Color(0xFF00BCD4),
                bo = 12.dp,
                pad = 8.dp,
                offsetY = (-190).dp, // Đẩy nút lên trên
                clickable = { /* Gửi lại mã */ },
                modifier = Modifier.width(100.dp)
            ) {
                Text("Gửi lại mã", fontSize = 14.sp, fontWeight = FontWeight.Medium)
            }

            // ----- Ảnh minh họa -----
            Image(
                painter = painterResource(R.drawable.cat_chubby),
                contentDescription = null, // Không cần mô tả
                contentScale = ContentScale.FillBounds, // Ảnh lấp đầy kích thước
                modifier = Modifier
                    .width(170.dp)
                    .height(250.dp)
                    .offset(x = (-120).dp, y = (-200).dp) // Dịch trái và lên trên
            )
        }
    }
}

// HÀM HỖ TRỢ VẼ BOX CÓ VIỀN
@Composable
fun BoxDecor(
    modifier: Modifier = Modifier, // Modifier tùy chỉnh
    viền: Dp = 4.dp, // Độ dày viền
    màu: Color = Color(0xFF00BCD4), // Màu viền
    bo: Dp = 24.dp, // Bo góc
    nền: Color = Color.White, // Màu nền
    pad: Dp = 24.dp, // Khoảng cách bên trong (padding)
    height: Dp? = null, // Chiều cao tùy chọn
    offsetY: Dp = 0.dp, // Dịch theo trục Y
    clickable: (() -> Unit)? = null, // Cho phép click nếu truyền vào
    content: @Composable BoxScope.() -> Unit // Nội dung bên trong box
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .then(if (height != null) Modifier.height(height) else Modifier) // Nếu có chiều cao thì áp dụng
            .offset(y = offsetY) // Dịch vị trí
            .VienChamCham(viền, màu, bo) // Gọi hàm vẽ viền chấm chấm
            .background(nền, RoundedCornerShape(bo)) // Nền và bo góc
            .then(if (clickable != null) Modifier.clickable { clickable() } else Modifier) // Cho phép click nếu có hàm
            .padding(pad), // Khoảng cách bên trong box
        contentAlignment = Alignment.Center // Căn giữa nội dung
    ) {
        content() // Hiển thị nội dung truyền vào
    }
}
//TRƯỜNG NHẬP MÃ OTP
@Composable
fun OTPInputField(otp: String, onOtpChange: (String) -> Unit) {
    val focusRequester = remember { FocusRequester() } // Dùng để yêu cầu focus tự động vào ô nhập

    LaunchedEffect(Unit) { focusRequester.requestFocus() } // Khi composable hiển thị, tự động focus

    Box(Modifier.fillMaxWidth()) {
        Row( // 6 ô nhập ký tự OTP
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly // Căn đều các ô
        ) {
            repeat(6) { i -> // Tạo 6 ô nhập
                val c = otp.getOrNull(i)?.toString().orEmpty() // Lấy ký tự ở vị trí i (nếu có)
                val chọn = i == otp.length // Kiểm tra ô hiện tại có phải ô đang nhập hay không

                Box(
                    Modifier
                        .size(50.dp) // Mỗi ô vuông 50dp
                        .VienChamCham(2.dp, if (chọn) Color(0xFF00BCD4) else Color(0xFFCCCCCC), 12.dp) // Viền chấm chấm (màu khác nếu đang chọn)
                        .background(
                            if (c.isNotEmpty()) Color(0xFFE0F7FA) else Color.White, // Màu nền đổi khi có ký tự
                            RoundedCornerShape(12.dp)
                        )
                        .clickable { focusRequester.requestFocus() }, // Khi bấm sẽ focus vào ô nhập ẩn
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        c, // Ký tự hiển thị
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (c.isNotEmpty()) Color(0xFF00BCD4) else Color.Gray // Đổi màu chữ nếu có ký tự
                    )
                }
            }
        }

        // Trường nhập thật (ẩn đi, nhưng nhận dữ liệu)
        BasicTextField(
            value = otp, // Giá trị OTP hiện tại
            onValueChange = { if (it.all(Char::isDigit)) onOtpChange(it) }, // Chỉ chấp nhận số
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.01f) // Làm trong suốt gần như hoàn toàn
                .focusRequester(focusRequester), // Liên kết focus
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword), // Bàn phím số
            textStyle = TextStyle(color = Color.Transparent) // Ẩn chữ nhập thật
        )
    }
}
//HÀM VẼ VIỀN CHẤM CHẤM TUỲ CHỈNH
fun Modifier.VienChamCham(độDày: Dp, màu: Color, bo: Dp) = drawBehind {
    drawRoundRect( // Vẽ khung chữ nhật bo góc
        color = màu, // Màu viền
        style = Stroke( // Chỉ vẽ viền, không tô nền
            width = độDày.toPx(), // Độ dày viền
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(30f, 10f)) // Tạo hiệu ứng chấm chấm (30f nét, 10f khoảng)
        ),
        cornerRadius = CornerRadius(bo.toPx()) // Bo góc theo tham số
    )
}
