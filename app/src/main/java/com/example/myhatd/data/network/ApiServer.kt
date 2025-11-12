package com.example.myhatd.data.network

import com.example.myhatd.data.model.ChuyenDiInfoRequest
import com.example.myhatd.data.model.ChuyenDiResponse
import com.example.myhatd.data.model.UserInfoRequest
import com.example.myhatd.data.model.UserResponse
import com.example.myhatd.data.model.DriverInfoRequest
import com.example.myhatd.data.model.DriverResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    // Endpoint để gửi thông tin người dùng sau khi xác thực OTP
    @POST("api/users/save-info")
    suspend fun saveUserInfo(@Body request: UserInfoRequest): Response<UserResponse>

    @POST("api/drivers/save-info")
    suspend fun saveDriverInfo(@Body request: DriverInfoRequest): Response<Unit>

    @GET("api/users/me/{soDienThoai}")
    suspend fun getCurrentUser(@Path("soDienThoai", encoded = true) phone: String): Response<UserResponse>

    @GET("api/drivers/me/{soDienThoai}")
    suspend fun getDriverByPhone(
        @Path("soDienThoai") soDienThoai: String
    ): Response<DriverResponse>


    @POST("api/chuyen-di/send")
    suspend fun sendChuyenDiInfo(
        @Body request: ChuyenDiInfoRequest
    ): Response<ChuyenDiResponse>
}