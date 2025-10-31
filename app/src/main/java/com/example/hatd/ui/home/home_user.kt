package com.example.hatd.ui.home.home_user

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.hatd.R
import androidx.compose.foundation.border
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController


@Composable
fun HomeUserScreen(navController: NavController) {
    // State cho thanh tìm kiếm
    var searchText by remember { mutableStateOf(TextFieldValue("")) }

    // Box chứa toàn bộ giao diện
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Hình ảnh đầu trang
        Image(
            painter = painterResource(id = R.drawable.header),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .align(Alignment.TopStart)
                .width(490.dp)
                .height(200.dp)
        )

        // Logo góc trái trên cùng
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .offset(x = 1.dp, y = 10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.size(120.dp)
            )
        }

        // Hình ảnh bản đồ
         val context = LocalContext.current
        val fLpc = remember { LocationServices.getFusedLocationProviderClient(context) }

// Trạng thái vị trí người dùng
        var userLocation by remember { mutableStateOf<LatLng?>(null) }
        val defaultLocation = LatLng(21.028511, 105.804817) // Hà Nội
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(defaultLocation, 12f)
        }

// Hàm lấy vị trí cuối cùng
        val fetchLastLocation: () -> Unit = {
            fLpc.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    val latLng = LatLng(location.latitude, location.longitude)
                    userLocation = latLng
                    cameraPositionState.position = CameraPosition.fromLatLngZoom(latLng, 15f)
                }
            }
        }

// Xin quyền
        val permissionLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) fetchLastLocation()
        }

        LaunchedEffect(Unit) {
            when {
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED -> fetchLastLocation()
                else -> permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }

// Google Map
        GoogleMap(
            modifier = Modifier
                .offset(y = 180.dp)
                .fillMaxWidth()
                .height(180.dp),
            cameraPositionState = cameraPositionState
        ) {
            userLocation?.let { location ->
                Marker(
                    state = MarkerState(position = location),
                    title = "Vị trí của bạn",
                    snippet = "Vị trí hiện tại"
                )
            }
        }

        // Thanh tìm kiếm trên bản đồ
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 160.dp)
                .padding(horizontal = 16.dp)
                .height(50.dp)
                .border(
                    width = 2.dp,
                    color = Color(0xFF3085E0),
                    shape = RoundedCornerShape(25.dp)
                )
                .background(
                    color = Color(0xFFFFFF),
                    shape = RoundedCornerShape(25.dp)
                )
                .graphicsLayer {
                    shadowElevation = 8f
                    shape = RoundedCornerShape(25.dp)
                    clip = true
                }
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Icon định vị (bên trái)
            Image(
                painter = painterResource(id = R.drawable.position),
                contentDescription = "Định vị",
                modifier = Modifier
                    .size(30.dp)

            )

            // TextField tìm kiếm (ở giữa)
            BasicTextField(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp),
                textStyle = TextStyle(
                    color = Color(0xFF333333),
                    fontSize = 15.sp
                ),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (searchText.text.isEmpty()) {
                            Text(
                                text = "Bạn muốn đi đâu",
                                color = Color(0xFF000000),
                                fontSize = 15.sp
                            )
                        }
                        innerTextField()
                    }
                }
            )

            // Icon kính lúp (bên phải)
            Image(
                painter = painterResource(id = R.drawable.glass),
                contentDescription = "Tìm kiếm",
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        navController.navigate("TaoYeuCauChuyenDi")
                    }
            )
        }

        // Box chứa Chế độ Driver (full width)
        Column(
            modifier = Modifier
                .width(300.dp)
                .padding(16.dp)
                .align(Alignment.BottomCenter)
                .offset(y = -440.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Box Chế độ Driver (full width)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(75.dp)
                    .dashedBorder(
                        strokeWidth = 4.dp,
                        color = Color(0xFF00BCD4),
                        cornerRadius = 16.dp
                    )
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clickable {
                        // Chuyển trang đến màn hình Driver
                        navController.navigate("DangKyHatd")
                    }
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                //////////////////////////////////////////////
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.drivermode),
                        contentDescription = "Chế độ Driver",
                        modifier = Modifier
                            .size(60.dp)
                            .offset(x = -5.dp, y = 0.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Chế độ Driver",
                        color = Color(0xFF787B79),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold,
                        fontSize = 19.sp,
                        modifier = Modifier.offset(x = -8.dp, y = 0.dp)  // <-- THÊM DÒNG NÀY cho text
                    )
                }
            }
        }

        // Khai báo trạng thái cho HorizontalPager
        val pagerState = rememberPagerState(
            initialPage = 0,
            pageCount = { 2 }
        )

        // Column để chứa cả Pager, Text và Indicator
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 475.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // HorizontalPager
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxWidth()
            ) { page ->
                Column {
                    // Box chứa ảnh
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(170.dp)
                            .padding(horizontal = 16.dp)
                            .clip(RoundedCornerShape(16.dp))
                    ) {
                        Image(
                            painter = painterResource(
                                id = when (page) {
                                    0 -> R.drawable.contentsale
                                    1 -> R.drawable.contentsale2
                                    else -> R.drawable.contentsale
                                }
                            ),
                            contentDescription = when (page) {
                                0 -> "Ưu đãi cực lớn lên đến 50%"
                                1 -> "Tiết kiệm-Thời gian-Chi phí-Môi trường"
                                else -> "Ưu đãi cực lớn lên đến 50%"
                            },
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    // Column chứa tiêu đề và mô tả cho từng ảnh
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        // Tiêu đề chính
                        Text(
                            text = when (page) {
                                0 -> "Ưu đãi cực lớn lên đến 50%"
                                1 -> "Tiết kiệm-Thời gian-Chi phí-Môi trường"
                                else -> "Ưu đãi cực lớn lên đến 50%"
                            },
                            style = MaterialTheme.typography.titleLarge,
                            color = Color(0xFF000000),
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                            fontSize = 18.sp
                        )

                        // Mô tả phụ (thời gian)
                        Text(
                            text = when (page) {
                                0 -> "Từ 3/10-10/10"
                                1 -> "Từ 6/10-15/10"
                                else -> "Từ 3/10-10/10"
                            },
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFF666666),
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Medium,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }

            // Indicator (các chấm tròn)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(2) { index ->
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .padding(horizontal = 4.dp)
                            .clip(RoundedCornerShape(50))
                            .background(
                                if (pagerState.currentPage == index) Color(0xFF00BCD4)
                                else Color(0xFFBDBDBD)
                            )
                    )
                }
            }
        }

        // Box ảnh độc lập phía dưới carousel
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 720.dp)
                .padding(horizontal = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFF5F5F5))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.footer),
                    contentDescription = "Banner độc lập",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        // Box chứa 3 icon ở FOOTER - Cuối màn hình
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .graphicsLayer {
                    shadowElevation = 12f
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomStart = 0.dp, bottomEnd = 0.dp)
                    clip = true
                }
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomStart = 0.dp, bottomEnd = 0.dp)
                )
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Icon 1 - Notification
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.clickable {
                    navController.navigate("ThongBao")
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bell),
                    contentDescription = "Thông báo",
                    modifier = Modifier.size(70.dp)
                )
            }

             // Icon 2 - History
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.clickable {
                    navController.navigate("LichSuChuyenDi")
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.clocki),
                    contentDescription = "Lịch sử",
                    modifier = Modifier.size(70.dp)
                )
            }

            // Icon 3 - Profile
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.clickable {
                    navController.navigate("HoSoUser")
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Hồ sơ",
                    modifier = Modifier.size(70.dp)
                )
            }
        }
    }
}

// Function tạo viền đứt nét
fun Modifier.dashedBorder(
    strokeWidth: Dp = 12.dp, // Độ dày viền (tăng để in đậm viền)
    color: Color = Color(0xFF00BCD4), // Màu viền
    cornerRadius: Dp = 16.dp // Góc bo viền
) = this.drawBehind {
    val stroke = strokeWidth.toPx() // Chuyển đổi độ dày từ Dp sang Px
    val dashWidth = 20f // Độ dài mỗi đoạn nét đứt
    val dashGap = 5f // Khoảng cách giữa các đoạn nét đứt
    val radius = cornerRadius.toPx() // Chuyển đổi góc bo từ Dp sang Px

    // Vẽ viền với hiệu ứng đường nét đứt
    drawRoundRect(
        color = color, // Màu viền
        style = Stroke(
            width = stroke, // Độ dày của viền
            pathEffect = PathEffect.dashPathEffect(
                floatArrayOf(dashWidth, dashGap), 0f // Hiệu ứng nét đứt
            )
        ),
        cornerRadius = CornerRadius(radius) // Góc bo tròn
    )
}

