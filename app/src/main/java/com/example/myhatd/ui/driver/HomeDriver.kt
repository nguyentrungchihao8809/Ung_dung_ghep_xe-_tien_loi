package com.example.myhatd.ui.driver

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.myhatd.R
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState


/**
 * 1. Composable Tái sử dụng: Ô tìm kiếm chỉ đọc có thể nhấp
 * Sử dụng Spacer + clickable để đảm bảo sự kiện chạm được kích hoạt.
 */
@Composable
fun RowScope.ClickableSearchBox(
    onFieldClick: () -> Unit
) {
    val displayValue = "" // Dùng để hiển thị placeholder

    Box(
        modifier = Modifier
            .weight(1f)
            .padding(horizontal = 12.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        // 1. BasicTextField (Dùng để hiển thị giao diện Placeholder/Text, nhưng không nhận click)
        BasicTextField(
            value = displayValue,
            onValueChange = { /* Bỏ qua vì nó là chỉ đọc */ },
            readOnly = true,
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(
                color = Color(0xFF333333),
                fontSize = 15.sp
            ),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (displayValue.isEmpty()) {
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

        // 2. 🚀 Lớp phủ trong suốt (Spacer) (Nhận toàn bộ sự kiện click và kích hoạt điều hướng)
        Spacer(
            modifier = Modifier
                .matchParentSize() // Chiếm toàn bộ không gian của BasicTextField
                .clickable {
                    onFieldClick() // ✅ BỘ PHẬN NÀY ĐẢM BẢO CHUYỂN TRANG
                }
        )
    }
}

// -----------------------------------------------------------
// 2. Màn hình chính: HomeUserScreen
// -----------------------------------------------------------

@Composable
fun HomeDriverScreen(navController: NavController) {

    // --- Logic Bản đồ và Vị trí ---
    val context = LocalContext.current
    val fLpc = remember { LocationServices.getFusedLocationProviderClient(context) }
    var userLocation by remember { mutableStateOf<LatLng?>(null) }
    val defaultLocation = LatLng(21.028511, 105.804817) // Hà Nội
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(defaultLocation, 12f)
    }

    val fetchLastLocation: () -> Unit = {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fLpc.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    val latLng = LatLng(location.latitude, location.longitude)
                    userLocation = latLng
                    cameraPositionState.position = CameraPosition.fromLatLngZoom(latLng, 15f)
                }
            }
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) fetchLastLocation()
    }

    LaunchedEffect(Unit) {
        when {
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED -> fetchLastLocation()
            else -> permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    // -----------------------------------------------------------

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Hình ảnh đầu trang và Logo (Lớp dưới cùng)
        Image(
            painter = painterResource(id = R.drawable.header),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .align(Alignment.TopStart)
                .width(490.dp)
                .height(200.dp)
        )

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

        // ⚠️ Google Map (Lớp 2 - Vẽ trước thanh tìm kiếm)
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

        // 🚀 Thanh tìm kiếm (Row) (Lớp 3 - Được vẽ sau Map và nhận sự kiện click)
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
                modifier = Modifier.size(30.dp)
            )

            // Compose đã sửa lỗi (ClickableSearchBox)
            ClickableSearchBox(
                onFieldClick = {
                    navController.navigate("driver_tao_yeu_cau_chuyen_di")
                }
            )

            // Icon kính lúp (bên phải)
            Image(
                painter = painterResource(id = R.drawable.glass),
                contentDescription = "Tìm kiếm",
                modifier = Modifier.size(30.dp)
            )
        }

        // Box chứa Chế độ Driver
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
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxSize()
                        .clickable {
                            navController.navigate("home")
                        }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.usermode),
                        contentDescription = "Chế độ Người đi",
                        modifier = Modifier
                            .size(60.dp)
                            .offset(x = -5.dp, y = 0.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Chế độ Người đi",
                        color = Color(0xFF787B79),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 19.sp,
                        modifier = Modifier.offset(x = -8.dp, y = 0.dp)
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
                            fontWeight = FontWeight.Bold,
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
                            fontWeight = FontWeight.Medium,
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
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bell),
                    contentDescription = "Thông báo",
                    modifier = Modifier.size(70.dp)
                        .clickable {
                            navController.navigate("thong_bao_user")
                        }
                )
            }

            // Icon 2 - History
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.clocki),
                    contentDescription = "Lịch sử",
                    modifier = Modifier.size(70.dp)
                        .clickable {
                            navController.navigate("hen_gio_user")
                        }
                )
            }

            // Icon 3 - Profile
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Hồ sơ",
                    modifier = Modifier.size(70.dp)
                        .clickable {
                            navController.navigate("ho_so_driver")
                        }
                )
            }
        }
    }
}

// -----------------------------------------------------------
// 3. Function tạo viền đứt nét
// -----------------------------------------------------------

fun Modifier.dashedBorder(
    strokeWidth: Dp = 4.dp,
    color: Color = Color(0xFF00BCD4),
    cornerRadius: Dp = 16.dp
) = this.drawBehind {
    val stroke = strokeWidth.toPx()
    val dashWidth = 20f
    val dashGap = 5f
    val radius = cornerRadius.toPx()

    drawRoundRect(
        color = color,
        style = Stroke(
            width = stroke,
            pathEffect = PathEffect.dashPathEffect(
                floatArrayOf(dashWidth, dashGap), 0f
            )
        ),
        cornerRadius = CornerRadius(radius)
    )
}