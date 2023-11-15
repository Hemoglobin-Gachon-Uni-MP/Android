package com.pline.model

import retrofit2.Call
import retrofit2.http.*

interface MemberApiInterface {
    @POST("/accounts/login")
    fun postLogin(@Body loginRequest : LoginRequest): Call<LoginResponse>

    @POST("/accounts/sign-up/kakao")
    fun postRegister(@Body registerRequest: RegisterRequest): Call<RegisterResponse>
}