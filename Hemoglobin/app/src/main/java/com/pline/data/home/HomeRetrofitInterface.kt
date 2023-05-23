package com.pline.data.home

import com.pline.data.home.model.DeleteFeedResponse
import com.pline.data.home.model.EditPostResponse
import com.pline.data.home.model.FeedsResponse
import com.pline.data.home.model.GetFeedInfoResponse
import com.pline.data.home.model.GetFeedListResponse
import com.pline.data.home.model.PostCommentReqBody
import com.pline.data.home.model.PostNewCommentResponse
import com.pline.data.home.model.baseUserIdReq
import com.pline.data.home.model.postFeedReqBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface HomeRetrofitInterface {
    // 게시물 생성 api
    @POST("feeds")
    fun createNewPost(
        @Body data: postFeedReqBody,
        @Header("X-ACCESS-TOKEN") xAccessToken: String
    ): Call<FeedsResponse>

    // 게시물 수정 api
    @PATCH("feeds/{feedId}")
    fun editPost(
        @Path("feedId") feedId: Int,
        @Body body: PostCommentReqBody,
        @Header("X-ACCESS-TOKEN") xAccessToken: String
    ):Call<EditPostResponse>

    // 게시물 삭제 api
    @PATCH("feeds/{feedId}/status")
    fun deletePost(
        @Body body: baseUserIdReq,
        @Path("feedId") feedId: Int,
        @Header("X-ACCESS-TOKEN") xAccessToken: String
    ): Call<DeleteFeedResponse>

    // 댓글 삭제 api

    // 댓글 달기 api
    @POST("feeds/comment/{feedId}")
    fun postNewComment(
        @Path("feedId") feedId: Int,
        @Body body: PostCommentReqBody,
        @Header("X-ACCESS-TOKEN") xAccessToken: String
    ): Call<PostNewCommentResponse>

    // 게시물 목록 반환 api
    @GET("feeds/info-list")
    fun getFeedList(
    ): Call<GetFeedListResponse>

    // 게시물 정보 반환 api
    @GET("feeds/info/{feedId}")
    fun getFeedInfo(
        @Path("feedId") feedId: Int
    ): Call<GetFeedInfoResponse>

    // 답글 달기 api

    // 답글 삭제 api

}