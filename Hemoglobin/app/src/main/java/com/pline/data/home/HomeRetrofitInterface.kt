package com.pline.data.home

import com.pline.data.home.model.FeedsResponse
import com.pline.data.home.model.GetFeedListResponse
import com.pline.data.home.model.postFeedReqBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface HomeRetrofitInterface {
    // 게시물 생성 api
    @POST("feeds")
    fun createNewPost(
        @Body data: postFeedReqBody
    ): Call<FeedsResponse>

    // 게시물 수정 api

    // 게시물 삭제 api

    // 댓글 삭제 api

    // 댓글 달기 api

    // 게시물 목록 반환 api
    @GET("feeds/info-list")
    fun getFeedList(
    ): Call<GetFeedListResponse>

    // 게시물 정보 반환 api

    // 답글 달기 api

    // 답글 삭제 api

}