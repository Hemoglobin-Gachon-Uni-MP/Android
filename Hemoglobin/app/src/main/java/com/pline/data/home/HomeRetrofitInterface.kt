package com.pline.data.home

import com.pline.data.home.model.DeleteCommentResponse
import com.pline.data.home.model.DeleteFeedResponse
import com.pline.data.home.model.DeleteReplyResponse
import com.pline.data.home.model.EditPostResponse
import com.pline.data.home.model.FeedsResponse
import com.pline.data.home.model.GetFeedInfoResponse
import com.pline.data.home.model.GetFeedListResponse
import com.pline.data.home.model.PostCommentReqBody
import com.pline.data.home.model.PostNewCommentResponse
import com.pline.data.home.model.PostNewReplyResponse
import com.pline.data.home.model.PostReplyReqBody
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
        @Header("Authorization") xAccessToken: String,
        @Body data: postFeedReqBody
    ): Call<FeedsResponse>

    // 게시물 수정 api
    @PATCH("feeds/{feedId}")
    fun editPost(
        @Header("Authorization") xAccessToken: String,
        @Path("feedId") feedId: Int,
        @Body body: PostCommentReqBody,
    ):Call<EditPostResponse>

    // 게시물 삭제 api
    @PATCH("feeds/{feedId}/status")
    fun deletePost(
        @Header("Authorization") xAccessToken: String,
        //@Body body: baseUserIdReq,
        @Path("feedId") feedId: Int
    ): Call<DeleteFeedResponse>

    // 댓글 삭제 api
    @PATCH("feeds/comment/{commentId}/status")
    fun deleteComment(
        @Header("Authorization") xAccessToken: String,
//        @Body body: baseUserIdReq,
        @Path("commentId") commentId: Int
    ): Call<DeleteCommentResponse>

    // 댓글 달기 api
    @POST("feeds/comment/{feedId}")
    fun postNewComment(
        @Header("Authorization") xAccessToken: String,
        @Path("feedId") feedId: Int,
        @Body body: PostCommentReqBody
    ): Call<PostNewCommentResponse>

    // 게시물 목록 반환 api
    @GET("feeds/info-list")
    fun getFeedList(
        @Header("Authorization") xAccessToken: String
    ): Call<GetFeedListResponse>

    // 게시물 정보 반환 api
    @GET("feeds/info/{feedId}")
    fun getFeedInfo(
        @Header("Authorization") xAccessToken: String,
        @Path("feedId") feedId: Int
    ): Call<GetFeedInfoResponse>

    // 답글 달기 api
    @POST("feeds/reply/{commentId}")
    fun postNewReply(
        @Header("Authorization") xAccessToken: String,
        @Path("commentId") commentId: Int,
        @Body body: PostReplyReqBody,
    ): Call<PostNewReplyResponse>

    // 답글 삭제 api
    @PATCH("feeds/reply/{replyId}/status")
    fun deleteReply(
        @Header("Authorization") xAccessToken: String,
        @Path("replyId") replyId: Int
    ): Call<DeleteReplyResponse>
}