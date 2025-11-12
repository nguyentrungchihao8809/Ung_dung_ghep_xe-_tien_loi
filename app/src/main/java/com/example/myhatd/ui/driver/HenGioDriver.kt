package com.example.myhatd.ui.driver

import androidx.compose.foundation.Image // Hiển thị ảnh
import androidx.compose.foundation.background // Vẽ nền cho Composable
import androidx.compose.foundation.clickable // Cho phép Composable có thể click
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.* // Layout: Box, Column, Row, Spacer...
import androidx.compose.foundation.lazy.LazyColumn // Danh sách cuộn theo chiều dọc
import androidx.compose.foundation.lazy.itemsIndexed // Hiển thị các item theo index
import androidx.compose.foundation.lazy.rememberLazyListState // Lưu trạng thái scroll của LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape // Bo tròn góc
import androidx.compose.material3.* // Material Design components (Button, Text, Card...)
import androidx.compose.runtime.* // State management trong Compose
import androidx.compose.ui.Alignment // Căn chỉnh nội dung
import androidx.compose.ui.Modifier // Thêm các thuộc tính cho Composable
import androidx.compose.ui.draw.drawBehind // Vẽ trực tiếp phía sau Composable
import androidx.compose.ui.geometry.CornerRadius // Góc bo khi vẽ
import androidx.compose.ui.graphics.Color // Màu sắc
import androidx.compose.ui.graphics.PathEffect // Hiệu ứng path (dashed line)
import androidx.compose.ui.graphics.drawscope.Stroke // Vẽ viền
import androidx.compose.ui.res.painterResource // Load ảnh từ resource
import androidx.compose.ui.text.font.FontWeight // Độ đậm chữ
import androidx.compose.ui.text.style.TextAlign // Căn chỉnh chữ
import androidx.compose.ui.unit.Dp // Đơn vị chiều dài DP
import androidx.compose.ui.unit.dp // DP
import androidx.compose.ui.unit.sp // SP
import androidx.navigation.NavController
import com.example.myhatd.R // Resource file
import java.text.SimpleDateFormat // Định dạng ngày giờ
import java.util.* // Calendar, Date
import kotlinx.coroutines.launch // Coroutine để chạy async
import com.example.myhatd.viewmodel.ChuyenDiViewModel // ✅ Thêm ViewModel
import com.example.myhatd.viewmodel.UserViewModel // ✅ Thêm ViewModel
import android.util.Log
import java.net.URLDecoder

// ✅ ĐỔI TÊN HÀM VÀ THÊM THAM SỐ
@Composable
fun DriverHenGioScreen(
    navController: NavController,
    chuyenDiViewModel: ChuyenDiViewModel,
    userViewModel: UserViewModel,
    diemDi: String,
    diemDen: String,
    viDo: Double,
    kinhDo: Double
) {
    // Lấy State và User data
    val state by chuyenDiViewModel.state.collectAsState()
    val user by userViewModel.userData

    // Khôi phục chuỗi đã mã hóa từ NavController
    val decodedDiemDi = URLDecoder.decode(diemDi, "UTF-8")
    val decodedDiemDen = URLDecoder.decode(diemDen, "UTF-8")

    // State lưu vị trí/giờ/phút người dùng chọn
    var ViTriNgayDaChon by remember { mutableStateOf(0) } // Chỉ số ngày được chọn
    var GioDaChon by remember { mutableStateOf("00") } // Giờ được chọn
    var PhutDaChon by remember { mutableStateOf("00") } // Phút được chọn

    // Tạo danh sách các ngày từ hôm nay -> 30 ngày sau (GIỮ NGUYÊN)
    val DanhSachNgay = remember {
        val Lich = Calendar.getInstance()
        val DinhDangNgay = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val DanhSach = mutableListOf<TuyChonNgay>()

        // Khởi tạo Lich về đầu ngày hôm nay để đảm bảo tính toán thời gian hẹn giờ chính xác
        Lich.set(Calendar.HOUR_OF_DAY, 0)
        Lich.set(Calendar.MINUTE, 0)
        Lich.set(Calendar.SECOND, 0)
        Lich.set(Calendar.MILLISECOND, 0)
        val NgayBatDau = Lich.time
        Lich.time = NgayBatDau // Reset lại ngày hôm nay

        DanhSach.add(TuyChonNgay(0, "Hôm nay", DinhDangNgay.format(Lich.time)))

        Lich.add(Calendar.DAY_OF_MONTH, 1)
        DanhSach.add(TuyChonNgay(1, "Ngày mai", DinhDangNgay.format(Lich.time)))

        for (i in 2..29) {
            Lich.add(Calendar.DAY_OF_MONTH, 1)
            val ChuoiNgay = DinhDangNgay.format(Lich.time)
            DanhSach.add(TuyChonNgay(i, ChuoiNgay, ChuoiNgay))
        }
        DanhSach
    }

    // Danh sách giờ/phút (GIỮ NGUYÊN)
    val DanhSachGio = (0..23).map { it.toString().padStart(2, '0') }
    val DanhSachPhut = (0..59).map { it.toString().padStart(2, '0') }

    // Tính giờ đến nơi (GIỮ NGUYÊN)
    val GioDenNoi = remember(GioDaChon, PhutDaChon) {
        val Gio = GioDaChon.toInt()
        val Phut = PhutDaChon.toInt()
        val TongPhut = Gio * 60 + Phut + 23
        val GioDen = (TongPhut / 60) % 24
        val PhutDen = TongPhut % 60
        "${GioDen.toString().padStart(2, '0')}:${PhutDen.toString().padStart(2, '0')}"
    }

    // --- LOGIC TÍNH TOÁN THỜI GIAN HẸN (SCHEDULE TIME LONG) ---
    val ThoiGianHenDaChon: Long? = remember(ViTriNgayDaChon, GioDaChon, PhutDaChon) {
        val NgayThang = DanhSachNgay.getOrNull(ViTriNgayDaChon)?.NgayThang

        if (NgayThang != null) {
            val ChuoiThoiGian = "$NgayThang $GioDaChon:$PhutDaChon"
            // Dùng định dạng khớp với chuỗi đã tạo
            val DinhDangParse = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())

            try {
                // Parse thành Date và lấy Unix Timestamp (milliseconds)
                // BE thường nhận giây, nhưng Long ở Android là milliseconds, nên ta gửi milliseconds
                DinhDangParse.parse(ChuoiThoiGian)?.time
            } catch (e: Exception) {
                Log.e("SCHEDULE_TIME", "Lỗi Parse Date: $e")
                null
            }
        } else {
            null
        }
    }

    // Trạng thái scroll (GIỮ NGUYÊN)
    val TrangThaiDanhSachNgay = rememberLazyListState()
    val TrangThaiDanhSachGio = rememberLazyListState()
    val TrangThaiDanhSachPhut = rememberLazyListState()
    val PhamVi = rememberCoroutineScope()

    // Cập nhật trạng thái khi scroll (GIỮ NGUYÊN)
    LaunchedEffect(TrangThaiDanhSachNgay.firstVisibleItemIndex) {
        ViTriNgayDaChon = TrangThaiDanhSachNgay.firstVisibleItemIndex
    }
    LaunchedEffect(TrangThaiDanhSachGio.firstVisibleItemIndex) {
        snapshotFlow { TrangThaiDanhSachGio.firstVisibleItemIndex }
            .collect { ViTri ->
                if (ViTri in DanhSachGio.indices) { GioDaChon = DanhSachGio[ViTri] }
            }
    }
    LaunchedEffect(TrangThaiDanhSachPhut.firstVisibleItemIndex) {
        snapshotFlow { TrangThaiDanhSachPhut.firstVisibleItemIndex }
            .collect { ViTri ->
                if (ViTri in DanhSachPhut.indices) { PhutDaChon = DanhSachPhut[ViTri] }
            }
    }

    // Box cha chứa toàn bộ màn hình
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        // Nút Back
        Image(
            painter = painterResource(id = R.drawable.backicon),
            contentDescription = "Quay lại",
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 24.dp, y = 40.dp)
                .size(40.dp)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    navController.popBackStack() // ✅ QUAY LẠI MÀN HÌNH NHẬP ĐIỂM
                }
        )

        // Box chính chứa nội dung hẹn giờ
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp)
                .offset(y = (-16).dp)
                .VienChamCham(3.dp, Color(0xFF00BCD4), 24.dp)
                .background(Color.White, RoundedCornerShape(24.dp))
                .padding(20.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Row header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.close),
                        contentDescription = "Dong",
                        modifier = Modifier
                            .size(35.dp)
                            .offset(x = 10.dp, y = 5.dp)
                            .clickable { /* Dong */ }
                    )
                    Text(
                        "Hẹn giờ",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.offset(y = 15.dp)
                    )
                    Spacer(modifier = Modifier.size(24.dp))
                }

                Spacer(modifier = Modifier.height(24.dp))

                // ✅ BOX TÓM TẮT CHUYẾN ĐI
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = -40.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F8FF), contentColor = Color.Black),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("Từ: **${decodedDiemDi}**", fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                        Text("Đến: **${decodedDiemDen}**", fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                        Text("Vị trí khởi hành: ${viDo.toString().substring(0, 6)}, ${kinhDo.toString().substring(0, 6)}", fontSize = 13.sp, color = Color.Gray)
                    }
                }

                Spacer(Modifier.height(30.dp)) // Đẩy phần chọn giờ xuống

                // Box chứa các danh sách ngày, giờ, phút (GIỮ NGUYÊN)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp)
                        .offset(y = -70.dp) // Điều chỉnh offset sau khi thêm Card
                ) {
                    val KhoangCachGiua = 115.dp

                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // ... (Logic LazyColumn chọn ngày, giờ, phút giữ nguyên) ...
                        // ======== Cột chọn ngày ========
                        Box(modifier = Modifier.weight(1f)) {
                            LazyColumn(
                                state = TrangThaiDanhSachNgay,
                                modifier = Modifier.fillMaxSize().offset(y = 29.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(12.dp),
                                contentPadding = PaddingValues(vertical = KhoangCachGiua)
                            ) {
                                itemsIndexed(DanhSachNgay) { ViTri, Ngay ->
                                    val DaChon = ViTriNgayDaChon == ViTri
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(50.dp)
                                            .background(
                                                if (DaChon) Color(0xFFE0F7FA) else Color.Transparent,
                                                RoundedCornerShape(16.dp)
                                            )
                                            .clickable(
                                                indication = null,
                                                interactionSource = remember { MutableInteractionSource() }
                                            ) {
                                                ViTriNgayDaChon = ViTri
                                                PhamVi.launch {
                                                    TrangThaiDanhSachNgay.animateScrollToItem(ViTri)
                                                }
                                            },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = Ngay.Nhan,
                                            fontSize = if (DaChon) 16.sp else 14.sp,
                                            color = if (DaChon) Color(0xFF00BCD4) else Color.Gray
                                        )
                                    }
                                }
                            }
                        }

                        // ======== Cột chọn giờ ========
                        Box(modifier = Modifier.weight(0.6f)) {
                            LazyColumn(
                                state = TrangThaiDanhSachGio,
                                modifier = Modifier.fillMaxSize().offset(y = 29.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                                contentPadding = PaddingValues(vertical = KhoangCachGiua)
                            ) {
                                itemsIndexed(DanhSachGio) { ViTri, Gio ->
                                    val DaChon = GioDaChon == Gio
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(50.dp)
                                            .background(
                                                if (DaChon) Color(0xFFE0F7FA) else Color.Transparent,
                                                RoundedCornerShape(16.dp)
                                            )
                                            .clickable(
                                                indication = null,
                                                interactionSource = remember { MutableInteractionSource() }
                                            ) {
                                                GioDaChon = Gio
                                                PhamVi.launch { TrangThaiDanhSachGio.animateScrollToItem(ViTri)
                                                }
                                            },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = Gio,
                                            fontSize = if (DaChon) 20.sp else 14.sp,
                                            fontWeight = if (DaChon) FontWeight.Bold else FontWeight.Normal,
                                            color = if (DaChon) Color(0xFF00BCD4) else Color.Gray
                                        )
                                    }
                                }
                            }
                        }

                        // ======== Cột chọn phút ========
                        Box(modifier = Modifier.weight(0.6f)) {
                            LazyColumn(
                                state = TrangThaiDanhSachPhut,
                                modifier = Modifier.fillMaxSize().offset(y = 29.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                                contentPadding = PaddingValues(vertical = KhoangCachGiua)
                            ) {
                                itemsIndexed(DanhSachPhut) { ViTri, Phut ->
                                    val DaChon = PhutDaChon == Phut
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(50.dp)
                                            .background(
                                                if (DaChon) Color(0xFFE0F7FA) else Color.Transparent,
                                                RoundedCornerShape(16.dp)
                                            )
                                            .clickable(
                                                indication = null,
                                                interactionSource = remember { MutableInteractionSource() }
                                            ) {
                                                PhutDaChon = Phut
                                                PhamVi.launch { TrangThaiDanhSachPhut.animateScrollToItem(ViTri)
                                                }
                                            },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = Phut,
                                            fontSize = if (DaChon) 20.sp else 14.sp,
                                            fontWeight = if (DaChon) FontWeight.Bold else FontWeight.Normal,
                                            color = if (DaChon) Color(0xFF00BCD4) else Color.Gray
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // ===== Viền chọn dạng chấm ===== (GIỮ NGUYÊN)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center)
                            .offset(y = 30.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(
                            Modifier
                                .weight(1f)
                                .height(50.dp)
                                .VienChamCham(2.dp, Color(0xFF00BCD4), 16.dp)
                        )
                        Box(
                            Modifier
                                .weight(0.6f)
                                .height(50.dp)
                                .VienChamCham(2.dp, Color(0xFF00BCD4), 16.dp)
                        )
                        Box(
                            Modifier
                                .weight(0.6f)
                                .height(50.dp)
                                .VienChamCham(2.dp, Color(0xFF00BCD4), 16.dp)
                        )
                    }
                }

                Spacer(Modifier.height(20.dp))

                // Box thông tin xe đón (GIỮ NGUYÊN)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = -90.dp) // Điều chỉnh offset sau khi thêm Card
                        .VienChamCham(2.dp, Color(0xFF00BCD4), 16.dp)
                        .background(Color(0xFFE0F7FA), RoundedCornerShape(16.dp))
                        .padding(16.dp)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Xe đón bạn lúc $GioDaChon:$PhutDaChon - ${DanhSachNgay[ViTriNgayDaChon].NgayThang}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "Đến nơi lúc $GioDenNoi",
                            fontSize = 15.sp,
                            color = Color.DarkGray
                        )
                        Text(
                            "di chuyển khoảng 23 phút",
                            fontSize = 13.sp,
                            color = Color.Gray
                        )
                    }
                }

                Spacer(Modifier.height(16.dp))

                // Thông tin thêm (GIỮ NGUYÊN)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = -80.dp), // Điều chỉnh offset sau khi thêm Card
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painterResource(R.drawable.smile),
                            contentDescription = null,
                            Modifier.size(30.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            "Giá cước giờ thấp điểm luôn tốt hơn giờ cao điểm.",
                            fontSize = 14.sp,
                            color = Color.Gray,
                            textAlign = TextAlign.Center
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painterResource(R.drawable.calendar),
                            contentDescription = null,
                            Modifier.size(30.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            "Bạn có thể hủy chuyến đi trước khi HATD đến đón.",
                            fontSize = 14.sp,
                            color = Color.Gray,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Spacer(Modifier.height(16.dp))

                // Nút xác nhận
                Button(
                    onClick = {
                        val scheduleTime = ThoiGianHenDaChon

                        if (scheduleTime != null && user?.phoneNumber != null) {
                            // ✅ GỌI VIEWMODEL GỬI REQUEST VỚI THỜI GIAN HẸN
                            chuyenDiViewModel.sendChuyenDi(
                                phoneNumber = user!!.phoneNumber!!,
                                role = "DRIVER",
                                diemDi = decodedDiemDi,
                                diemDen = decodedDiemDen,
                                viDo = viDo,
                                kinhDo = kinhDo,
                                scheduleTime = scheduleTime // ✅ TRUYỀN LONG TIMESTAMP
                            )
                        } else {
                            Log.e("SCHEDULE_SEND", "Chưa chọn thời gian hoặc thiếu thông tin user.")
                            // Có thể thêm SnackBar thông báo lỗi
                        }
                    },
                    // ✅ KÍCH HOẠT NÚT khi có đủ thông tin và không đang loading
                    enabled = ThoiGianHenDaChon != null && user != null && !state.isLoading,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(58.dp)
                        .offset(y = 10.dp), // Điều chỉnh offset cuối cùng
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB3E5FC)),
                    shape = RoundedCornerShape(16.dp),
                    elevation = ButtonDefaults.buttonElevation(0.dp)
                ) {
                    Text(
                        // ✅ HIỂN THỊ TRẠNG THÁI LOADING
                        text = if (state.isLoading) "Đang gửi yêu cầu..." else "Xác nhận",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF00BCD4)
                    )
                }

                // ✅ XỬ LÝ ĐIỀU HƯỚNG SAU KHI GỬI THÀNH CÔNG
                LaunchedEffect(state.successMessage) {
                    if (state.successMessage != null) {
                        // Quay về màn hình home của driver
                        navController.popBackStack("home_driver", inclusive = false)
                        chuyenDiViewModel.resetState()
                    }
                }
                LaunchedEffect(state.errorMessage) {
                    if (state.errorMessage != null) {
                        Log.e("SCHEDULE_ERROR", state.errorMessage!!)
                        chuyenDiViewModel.resetState()
                    }
                }
            }
        }
    }
}

// Data class chứa thông tin một ngày (GIỮ NGUYÊN)
data class TuyChonNgay(val Ma: Int, val Nhan: String, val NgayThang: String)

// Hàm vẽ viền chấm cho Modifier (GIỮ NGUYÊN)
fun Modifier.VienChamCham(
    DoDay: Dp = 3.dp,
    MauSac: Color = Color(0xFF00BCD4),
    BanKinh: Dp = 16.dp
) = this.drawBehind {
    val DoRongVien = DoDay.toPx()
    val DoDaiNet = 30f
    val KhoangCach = 10f
    val BanKinhGoc = BanKinh.toPx()

    drawRoundRect(
        color = MauSac,
        style = Stroke(
            width = DoRongVien,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(DoDaiNet, KhoangCach), 0f)
        ),
        cornerRadius = CornerRadius(BanKinhGoc)
    )
}