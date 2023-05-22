package com.pline.data.mypage

import com.pline.data.mypage.model.MyPageResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface MyPageRetrofitInterface {

    // GET API for my page
    @GET("/mypages/{userId}")
    fun getMyPageInfo(
        @Header("X-ACCESS-TOKEN") xAccessToken : String,
        @Path("userId") userId: Int
    ): Call<MyPageResponse>
}