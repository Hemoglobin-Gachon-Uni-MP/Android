package com.pline.data.mypage

import com.pline.data.mypage.model.MyAccountDeleteResponse
import com.pline.data.mypage.model.MyPageEditRequest
import com.pline.data.mypage.model.MyPageEditResponse
import com.pline.data.mypage.model.MyPageResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.Path

interface MyPageRetrofitInterface {

    // GET API for getting my page
    @GET("/mypages/{userId}")
    fun getMyPageInfo(
        @Header("X-ACCESS-TOKEN") xAccessToken : String,
        @Path("userId") userId: Int
    ): Call<MyPageResponse>

    // PATCH API for editing my page
    @PATCH("/mypages/{userId}")
    fun editMyPageInfo(
        @Header("X-ACCESS-TOKEN") xAccessToken : String,
        @Path("userId") userId: Int,
        @Body myPageEditRequest: MyPageEditRequest
    ): Call<MyPageEditResponse>

    // PATCH API for deleting my account
    @PATCH("/resign/{userId}")
    fun deleteMyAccount(
        @Header("X-ACCESS-TOKEN") xAccessToken : String,
        @Path("userId") userId: Int
    ): Call<MyAccountDeleteResponse>
}