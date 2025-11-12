package com.example.myhatd.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhatd.data.model.ChuyenDiInfoRequest
import com.example.myhatd.data.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// State cho màn hình gửi thông tin chuyến đi (Giữ nguyên)
data class ChuyenDiState(
    val isLoading: Boolean = false,
    val successMessage: String? = null,
    val errorMessage: String? = null
)

class ChuyenDiViewModel : ViewModel() {

    private val _state = MutableStateFlow(ChuyenDiState())
    val state: StateFlow<ChuyenDiState> get() = _state

    // ✅ CHỈNH SỬA: Thêm scheduleTime (Long?) vào tham số hàm
    fun sendChuyenDi(
        phoneNumber: String,
        role: String,
        diemDi: String,
        diemDen: String,
        viDo: Double?,
        kinhDo: Double?,
        scheduleTime: Long? = null // ✅ THÊM: Long? (Unix Timestamp - null nếu là chuyến đi ngay lập tức)
    ) {
        // Kiểm tra vị trí trước khi gửi
        if (viDo == null || kinhDo == null) {
            _state.value = ChuyenDiState(
                isLoading = false,
                errorMessage = "Không thể lấy được vị trí hiện tại (Kinh độ/Vĩ độ)."
            )
            return
        }

        _state.value = ChuyenDiState(isLoading = true)

        viewModelScope.launch {
            try {
                // ThoiGian là thời gian request được gửi đi (hoặc thời gian hiện tại)
                val nowString = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
                    .format(Date())

                val request = ChuyenDiInfoRequest(
                    phoneNumber = phoneNumber,
                    role = role,
                    viDo = viDo,
                    kinhDo = kinhDo,
                    thoiGian = nowString,
                    diemDi = diemDi,
                    diemDen = diemDen,
                    thoiGianDriver = scheduleTime // ✅ ÁNH XẠ: Gửi Long Timestamp đã chọn
                )

                val response = RetrofitClient.apiService.sendChuyenDiInfo(request)

                if (response.isSuccessful) {
                    _state.value = ChuyenDiState(
                        isLoading = false,
                        successMessage = "Gửi yêu cầu thành công"
                    )
                } else {
                    // Cải tiến xử lý lỗi (nếu có thể lấy được nội dung lỗi từ response.errorBody())
                    _state.value = ChuyenDiState(
                        isLoading = false,
                        errorMessage = "Lỗi server: ${response.code()} ${response.message()}"
                    )
                }
            } catch (e: Exception) {
                _state.value = ChuyenDiState(
                    isLoading = false,
                    errorMessage = "Lỗi mạng: ${e.localizedMessage}"
                )
            }
        }
    }

    // Reset trạng thái sau khi hiển thị thông báo (Giữ nguyên)
    fun resetState() {
        _state.value = ChuyenDiState()
    }
}