package com.pline.data.home

import com.pline.data.home.model.FeedsResponse
import com.pline.data.home.model.PostReportResponse
import com.pline.data.home.model.postReportReqBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ReportRetrofitInterface {
    // 신고하기
    @POST("reports")
    fun newPostReport(
        @Header("Authorization") xAccessToken: String,
        @Body body: postReportReqBody
    ): Call<PostReportResponse>
}