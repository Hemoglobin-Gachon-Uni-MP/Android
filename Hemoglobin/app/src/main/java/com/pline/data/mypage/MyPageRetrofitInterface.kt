package com.pline.data.mypage

import com.pline.data.mypage.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

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

    // POST API for posting my certification
    @Multipart
    @POST("/mypages/certification")
    fun postMyCert(
        @Header("Authorization") xAccessToken : String,
        @Part image: MultipartBody.Part,
        @Part("certification") name: RequestBody
    ): Call<PostCertResponse>

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