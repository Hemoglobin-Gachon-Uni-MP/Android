package com.pline.model

import retrofit2.Call
import retrofit2.http.*

interface MemberApiInterface {
    @POST("/accounts/login")
    fun postLogIn(@Body idToken : String): Call<LoginResponse>

    @POST("/accounts/sign-up/kakao")
    fun postRegister(@Body registerRequest: RegisterRequest): Call<RegisterResponse>
}