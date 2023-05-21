package com.pline.model

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {
    @POST("/kakao/sign-in")
    fun postLogIn(@Header("K-ACCESS-TOKEN") kAccessToken : String): Call<LoginResponse>

    @POST("/kakao/sign-up")
    fun postRegister(@Header("K-ACCESS-TOKEN") kAccessToken : String, @Body registerRequest: RegisterRequest): Call<RegisterResponse>
}