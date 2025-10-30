// Thư mục: viewmodel
// File: AuthViewModel.kt (ĐÃ CẬP NHẬT)

package com.example.myhatd.viewmodel

import android.app.Activity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myhatd.service.AuthCallback
import com.example.myhatd.service.FirebaseAuthService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.launch

data class AuthState(
    val phoneNumber: String = "+84",
    val otpCode: String = "", // Thêm trường lưu OTP
    val isLoading: Boolean = false,
    val verificationId: String? = null,
    val error: String? = null,
    val isOtpSent: Boolean = false,
    val isAuthenticated: Boolean = false // Thêm trạng thái xác thực thành công
)

class AuthViewModel(private val authService: FirebaseAuthService) : ViewModel(), AuthCallback {

    private val auth = FirebaseAuth.getInstance()
    var state by mutableStateOf(AuthState())
        private set

    init {
        authService.callback = this
    }

    fun resetState() {
        state = AuthState() // ✅ Reset về mặc định
    }

    fun onPhoneNumberChange(newNumber: String) {
        state = state.copy(phoneNumber = newNumber, error = null)
    }

    fun onOtpCodeChange(newCode: String) { // Hàm cập nhật OTP
        state = state.copy(otpCode = newCode, error = null)
    }

    fun sendOtp() {
        if (state.phoneNumber.length < 10 || !state.phoneNumber.startsWith("+")) {
            state = state.copy(error = "Vui lòng nhập số điện thoại hợp lệ (+Mã quốc gia).")
            return
        }

        state = state.copy(isLoading = true, error = null)
        authService.sendVerificationCode(state.phoneNumber)
    }

    /**
     * Xác minh mã OTP do người dùng nhập.
     */
    fun verifyOtp() {
        val vId = state.verificationId
        val code = state.otpCode

        if (vId == null || code.length < 6) {
            state = state.copy(error = "Mã OTP không hợp lệ.", isLoading = false)
            return
        }

        state = state.copy(isLoading = true, error = null)

        // 1. Tạo PhoneAuthCredential
        val credential = PhoneAuthProvider.getCredential(vId, code)

        // 2. Đăng nhập bằng Credential
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                viewModelScope.launch {
                    if (task.isSuccessful) {
                        state = state.copy(
                            isLoading = false,
                            isAuthenticated = true, // CẬP NHẬT TRẠNG THÁI
                            error = null
                        )
                    } else {
                        state = state.copy(
                            isLoading = false,
                            error = task.exception?.message ?: "Xác minh OTP thất bại."
                        )
                    }
                }
            }
    }

    override fun onCodeSent(verificationId: String) {
        viewModelScope.launch {
            state = state.copy(
                isLoading = false,
                verificationId = verificationId,
                isOtpSent = true,
                error = null
            )
        }
    }

    override fun onVerificationFailed(message: String) {
        viewModelScope.launch {
            state = state.copy(
                isLoading = false,
                error = message
            )
        }
    }

    // Factory không thay đổi
    class Factory(private val activity: Activity) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
                val authService = FirebaseAuthService(activity)
                @Suppress("UNCHECKED_CAST")
                return AuthViewModel(authService) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}