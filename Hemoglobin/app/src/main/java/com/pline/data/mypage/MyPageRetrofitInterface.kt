package com.pline.data.mypage

import com.pline.data.mypage.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.Path

interface MyPageRetrofitInterface {

    // GET API for getting my page
    @GET("/mypages")
    fun getMyPageInfo(
        @Header("Authorization") xAccessToken : String
    ): Call<MyPageResponse>

    // GET API for getting my medals
    @GET("/mypages/certification/reward-list")
    fun getMyMedalList(
        @Header("Authorization") xAccessToken : String
    ): Call<MyMedalListResponse>

    // GET API for getting my certifications
    @GET("/mypages/certification/list")
    fun getMyCertifications(
        @Header("Authorization") xAccessToken : String
    ): Call<MyCertListResponse>

    // PATCH API for editing my page
    @PATCH("/mypages")
    fun editMyPageInfo(
        @Header("Authorization") xAccessToken : String,
        @Body myPageEditRequest: MyPageEditRequest
    ): Call<MyPageEditResponse>

    // PATCH API for deleting my account
    @PATCH("/resign")
    fun deleteMyAccount(
        @Header("Authorization") xAccessToken : String
    ): Call<MyAccountDeleteResponse>
}