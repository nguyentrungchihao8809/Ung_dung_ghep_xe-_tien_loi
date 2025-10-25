package com.example.hatd.ui.user.ChiTietChuyenDi

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hatd.R
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController


@Composable
fun ChiTietChuyenDiScreen(navController: NavController? = null) {
    // trạng thái dialog
    var showLyDoHuy by remember { mutableStateOf(false) }
    var showXacNhanHuy by remember { mutableStateOf(false) }
    var showHoanTatHuy by remember { mutableStateOf(false) }
    var lyDoChon by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp)
    ) {
        // 🔹 Thanh tiêu đề
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* Quay lại */ }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
            Text(
                text = "Chi tiết chuyến đi",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 🔹 Thông tin tài xế
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

                // Biển số xe, tên xe
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
        // 🔹 Khung nền trắng bo tròn chứa cả chat + call
        Card(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .wrapContentHeight()
                .shadow(4.dp, RoundedCornerShape(30.dp)), // Bo mềm, bóng nhẹ
            shape = RoundedCornerShape(30.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                // 🔸 Nút Chat với tài xế
                OutlinedButton(
                    onClick = { /* TODO: Chat với tài xế */ },
                    shape = RoundedCornerShape(50), // Bo tròn đều
                    border = BorderStroke(1.dp, Color.Black),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                    modifier = Modifier.height(40.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.chat),
                        contentDescription = "Chat",
                        tint = Color.Black,
                        modifier = Modifier.size(55.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Chat với tài xế",
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                }

                // 🔸 Nút gọi sát bên phải
                IconButton(
                    onClick = { /* TODO: Gọi tài xế */ },
                    modifier = Modifier
                        .size(55.dp)
                        .padding(start = 6.dp) // Giúp nút call gần hơn
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.call),
                        contentDescription = "Call",
                        tint = Color.Black,
                        modifier = Modifier.size(100.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        // 🔹 Thông tin chuyến đi
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color(0xFF4ABDE0), RoundedCornerShape(16.dp))
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
        ) {
            Box {
                //  Ảnh nền mờ phía sau phần chi tiết chuyến đi
                Image(
                    painter = painterResource(id = R.drawable.anhnenchitietchuyendi),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop,
                    alpha = 0.1f
                )

                Column(modifier = Modifier.padding(16.dp)) {
                    // 🔹 Loại chuyến + thời gian
                    Text(
                        text = "HATD bike",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween // căn hai đầu
                    ) {
                        Text(
                            text = "Thời gian:",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            color = Color.Black
                        )
                        Text(
                            text = "__/__/____ • __:__ __",
                            color = Color(0xFF9E9E9E), // xám nhạt
                            fontSize = 14.sp
                        )
                    }

                    Spacer(Modifier.height(12.dp))


                    // 🔹 Điểm đón
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.diembatdau),
                            contentDescription = null,
                            tint = Color(0xFF000000),
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Column {
                            Text("...", fontWeight = FontWeight.Bold)
                            Text("...", color = Color.Gray, fontSize = 13.sp)
                        }
                    }

                    // 🔹 Ảnh đường nối giữa điểm đón và điểm đến
                    Image(
                        painter = painterResource(id = R.drawable.duonggachnoi),
                        contentDescription = "Đường nối giữa điểm đón và điểm đến",
                        modifier = Modifier
                            .padding(start = 2.dp, top = 2.dp, bottom = 2.dp)
                            .height(40.dp)
                    )

                    // 🔹 Điểm đến
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.diemden),
                            contentDescription = null,
                            tint = Color(0xFF2196F3),
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Column {
                            Text("...", fontWeight = FontWeight.Bold)
                            Text("...", color = Color.Gray, fontSize = 13.sp)
                        }
                    }

                    Spacer(Modifier.height(12.dp))

                    // 🔹 Tiền mặt
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.tien),
                            contentDescription = null,
                            tint = Color(0xFF4CAF50),
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text("Tiền mặt", fontWeight = FontWeight.Bold)
                        Spacer(Modifier.weight(1f))
                        Text("...", fontWeight = FontWeight.Bold)
                    }

                    Spacer(Modifier.height(12.dp))

                    // 🔹 Ghi chú
                    Text("Ghi chú", fontWeight = FontWeight.Bold)
                    Text("...", color = Color.Gray, fontSize = 14.sp)
                }


                //  Hình xe nhỏ góc dưới bên phải
                Image(
                    painter = painterResource(id = R.drawable.xegocduoiphai),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(90.dp)
                        .padding(end = 4.dp, bottom = 4.dp)
                )

                //  Hình xe nhỏ góc dưới bên trái
                Image(
                    painter = painterResource(id = R.drawable.xegoctrai),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .size(90.dp)
                        .padding(start = 4.dp, bottom = 4.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 🔹 Nút hủy chuyến
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                // bật dialog chọn lý do hủy — giữ nguyên vị trí nút / màu / style
                showLyDoHuy = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4ABDE0))
        ) {
            Text("Hủy chuyến xe", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }

    // ===== Dialogs: Hiển thị chồng lên giao diện gốc (mờ nền), bo tròn, trắng =====

    if (showLyDoHuy) {
        HopThoaiLyDoHuy(
            onChon = { lyDo ->
                lyDoChon = lyDo
                showLyDoHuy = false
                // chuyển sang dialog xác nhận
                showXacNhanHuy = true
            },
            onHuy = {
                showLyDoHuy = false
            }
        )
    }

    if (showXacNhanHuy) {
        HopThoaiXacNhanHuy(
            lyDo = lyDoChon,
            onXacNhan = {
                // TODO: gọi API hủy ở đây nếu cần
                showXacNhanHuy = false
                showHoanTatHuy = true
            },
            onQuayLai = {
                showXacNhanHuy = false
                showLyDoHuy = true
            }
        )
    }

    if (showHoanTatHuy) {
        HopThoaiHoanTatHuy(
            onDatLai = {
                showHoanTatHuy = false
                // điều hướng về "TaoYeuCauChuyenDi" nếu NavController được truyền
                navController?.navigate("TaoYeuCauChuyenDi")
            },
            onDong = {
                showHoanTatHuy = false
            }
        )
    }
}

/**
 * Dialog: HopThoaiLyDoHuy
 * Dạng popup chính giữa, nền dim tự động do Dialog
 */
@Composable
fun HopThoaiLyDoHuy(onChon: (String) -> Unit, onHuy: () -> Unit) {
    Dialog(onDismissRequest = onHuy) {
        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Tại sao bạn lại hủy bỏ?", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = "Hãy cho HATD biết chuyện gì xảy ra.", color = Color.Gray, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(12.dp))

                val danhSach = listOf(
                    "Thay đổi phương thức thanh toán",
                    "Tôi muốn thay đổi địa điểm",
                    "Các vấn đề ưu đãi",
                    "Tài xế ở quá xa"
                )

                danhSach.forEach { lyDo ->
                    OutlinedButton(
                        onClick = { onChon(lyDo) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.dp, Color(0xFF4ABDE0)),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color(0xFFE8F6FC),
                            contentColor = Color.Black
                        )
                    ) {
                        Text(text = lyDo)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    TextButton(onClick = onHuy) {
                        Text("Đóng")
                    }
                }
            }
        }
    }
}

/**
 * Dialog: HopThoaiXacNhanHuy
 */
@Composable
fun HopThoaiXacNhanHuy(lyDo: String, onXacNhan: () -> Unit, onQuayLai: () -> Unit) {
    Dialog(onDismissRequest = onQuayLai) {
        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // dùng ảnh avtdriver làm biểu tượng; nếu bạn có ảnh khác, thay vào R.drawable.*
                Image(
                    painter = painterResource(id = R.drawable.avtdriver),
                    contentDescription = "Icon xác nhận",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(8.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Bạn có chắc muốn hủy chuyến?", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = "Lý do: $lyDo", color = Color.Gray, fontSize = 14.sp)

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onXacNhan,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4ABDE0)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Xác nhận hủy", color = Color.White)
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedButton(
                    onClick = onQuayLai,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Quay lại")
                }
            }
        }
    }
}

/**
 * Dialog: HopThoaiHoanTatHuy
 * Hiển thị thông báo hoàn tất và dòng "Vui lòng đặt lại" dẫn về route "TaoYeuCauChuyenDi"
 */
@Composable
fun HopThoaiHoanTatHuy(onDatLai: () -> Unit, onDong: () -> Unit) {
    Dialog(onDismissRequest = onDong) {
        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // ảnh hoàn tất (dùng logo làm ví dụ)
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Hoàn tất",
                    modifier = Modifier.size(100.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Lần sau vẫn đồng hành cùng HATD nhé!",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(12.dp))

                TextButton(onClick = onDatLai) {
                    Text(
                        text = "Vui lòng đặt lại",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF4ABDE0)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                TextButton(onClick = onDong) {
                    Text("Đóng")
                }
            }
        }
    }
}
