package com.example.myhatd.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhatd.data.model.UserInfoRequest
import com.example.myhatd.data.network.RetrofitClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
// ✅ Các import cần thiết cho API lấy vị trí mới
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// Data Class giữ nguyên
data class MapUiState(
    val lastKnownLocation: LatLng? = null,
    val isLocationPermissionGranted: Boolean = false,
    val mapType: Int = GoogleMap.MAP_TYPE_NORMAL,
    val routePolyline: String? = null
)

class MapViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MapUiState())
    val uiState: StateFlow<MapUiState> = _uiState

    fun setLocationPermission(isGranted: Boolean) {
        _uiState.value = _uiState.value.copy(isLocationPermissionGranted = isGranted)
    }

    /**
     * 🚀 HÀM CHUẨN: Sử dụng getCurrentLocation (API hiện đại, tốt nhất để lấy vị trí một lần).
     * Hàm này tự động tối ưu hóa để trả về vị trí tốt nhất và nhanh nhất.
     */
    @SuppressLint("MissingPermission")
    fun requestCurrentLocation(context: Context) {
        // 1. Kiểm tra quyền
        if (!_uiState.value.isLocationPermissionGranted) return

        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

        // Cần thiết cho getCurrentLocation
        val cancellationTokenSource = CancellationTokenSource()

        // Gọi API getCurrentLocation: yêu cầu độ chính xác cao và token hủy
        fusedLocationClient.getCurrentLocation(
            LocationRequest.PRIORITY_HIGH_ACCURACY,
            cancellationTokenSource.token
        ).addOnSuccessListener { loc ->
            // Success Listener chỉ chạy khi có vị trí
            loc?.let {
                val latLng = LatLng(it.latitude, it.longitude)
                viewModelScope.launch {
                    // Cập nhật State để UI có thể đọc
                    _uiState.value = _uiState.value.copy(lastKnownLocation = latLng)
                }
            }
        }.addOnFailureListener { e ->
            // TODO: Xử lý lỗi (ví dụ: GPS bị tắt, timeout, v.v.)
            e.printStackTrace()
        }
    }


    fun setRoute(polyline: String) {
        _uiState.value = _uiState.value.copy(routePolyline = polyline)
    }

    fun setMapType(type: Int) {
        _uiState.value = _uiState.value.copy(mapType = type)
    }

    fun sendUserInfoToServer(
        soDienThoai: String,
        ten: String,
        cccd: String?,
        role: String
    ) {
        val location = _uiState.value.lastKnownLocation
        val viDo = location?.latitude
        val kinhDo = location?.longitude

        val request = UserInfoRequest(
            phoneNumber = soDienThoai,
            name = ten,
            cccd = cccd,
        )

        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.saveUserInfo(request)
                if (response.isSuccessful) {
                    val user = response.body()
                    // TODO: xử lý thành công (cập nhật UI, lưu local, ...)
                } else {
                    // TODO: xử lý lỗi server
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // TODO: xử lý lỗi mạng
            }
        }
    }
}