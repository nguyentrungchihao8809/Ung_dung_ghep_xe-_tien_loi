// Thư mục: ui
// File: MapScreen.kt (Phiên bản cập nhật)

package com.example.myhatd.ui

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.*

@Composable
fun MapScreen() {
    val context = LocalContext.current
    val fLpc = remember { LocationServices.getFusedLocationProviderClient(context) }

    // 1. Trạng thái vị trí hiện tại
    var userLocation by remember { mutableStateOf<LatLng?>(null) }

    // Vị trí mặc định (nếu chưa có vị trí người dùng)
    val defaultLocation = LatLng(21.028511, 105.804817) // Hà Nội

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(defaultLocation, 12f)
    }

    // Hàm lấy vị trí cuối cùng
    val fetchLastLocation: () -> Unit = {
        fLpc.lastLocation.addOnSuccessListener { location: Location? ->
            if (location != null) {
                val latLng = LatLng(location.latitude, location.longitude)
                userLocation = latLng
                // Cập nhật camera để trỏ đến vị trí người dùng
                cameraPositionState.position = CameraPosition.fromLatLngZoom(latLng, 15f)
            }
        }
    }

    // 2. Trình khởi chạy xin cấp quyền
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            fetchLastLocation()
        }
        // Nếu từ chối, bản đồ sẽ hiển thị vị trí mặc định (Hà Nội)
    }

    // 3. Side effect: Kiểm tra và xin quyền khi màn hình được tạo
    LaunchedEffect(Unit) {
        when {
            // Đã có quyền
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                fetchLastLocation()
            }
            // Chưa có quyền, yêu cầu quyền
            else -> {
                permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    // 4. Composable GoogleMap
    GoogleMap(
        modifier = Modifier
            .fillMaxWidth()
            .height(850.dp)
            .padding(top = 170.dp),
        cameraPositionState = cameraPositionState
    ) {
        // Hiển thị Marker tại vị trí người dùng (nếu có)
        userLocation?.let { location ->
            Marker(
                state = MarkerState(position = location),
                title = "Vị trí của bạn",
                snippet = "Vị trí hiện tại"
            )
        }
    }
}