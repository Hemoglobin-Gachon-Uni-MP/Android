package com.pline.data.home.model

import android.view.inspector.IntFlagMapping
import com.google.gson.annotations.SerializedName
import com.pline.config.BaseResponse

data class Feeds(
    @SerializedName("result") val result: FeedsResponse
)

data class postFeedReqBody(
    var abo: Int,
    var context: String,
    var isReceiver: String,
    var location: String,
    var rh: Int,
    var userId: Int
)

data class FeedListResult(
    val abo: Int?,
    val commentCnt: Int,
    val context: String,
    val date: String,
    val feedId: Int,
    val isReceiver: Boolean,
    val location: String?,
    val nickname: String,
    val profileImg: String,
    val rh: Int?,
    val userId: Int
)

data class GetFeedListResponse(
    val result: ArrayList<FeedListResult>
): BaseResponse()

data class FeedsResponse(
    val result: FeedsResult
):BaseResponse()

data class FeedsResult(
    @SerializedName("result") val result: Int
)

data class GetFeedInfoResponse(
    val result: FeedInfoResult
): BaseResponse()

data class Comment(
    var commentId: Int,
    var userId: Int,
    var profileImg: Int,
    var nickname: String,
    var context: String,
    var replyList: ArrayList<Reply>,
    var date: String
)

data class Reply(
    var context: String,
    var date: String,
    var nickname: String,
    var profileImg: Int,
    var replyId: Int,
    var userId: Int
)

data class FeedInfoResult(
    @SerializedName("feedId") var feedId: Int,
    @SerializedName("userId") var userId: Int,
    @SerializedName("profileImg") var profileImg: String,
    @SerializedName("nickname") var nickname: String,
    @SerializedName("context") var context: String,
    @SerializedName("commentCnt") var commentCnt: Int,
    @SerializedName("commentList") var commentList: ArrayList<Comment>?,
    @SerializedName("date") var date: String,
    @SerializedName("abo") var abo: Int,
    @SerializedName("rh") var rh: Int,
    @SerializedName("location") var location: String,
    @SerializedName("isReceiver") var isReceiver: String
)

data class baseUserIdReq(
    val userId: Int
)

data class DeleteFeedResponse(
    @SerializedName("result") val result: String
): BaseResponse()

data class PostCommentReqBody(
    var context: String,
    val userId: Int
)

data class PostNewCommentResponse(
    @SerializedName("result") val result: Int
): BaseResponse()


data class EditPostResponse(
    @SerializedName("result") val result: String
): BaseResponse()

data class DeleteCommentResponse(
    @SerializedName("result") val result: String
): BaseResponse()

data class PostReplyReqBody(
    @SerializedName("context") var context: String,
    @SerializedName("feedId") val feedId: Int,
    @SerializedName("userId") val userId: Int
)

data class PostNewReplyResponse(
    @SerializedName("result") val result: Int
): BaseResponse()

data class DeleteReplyResponse(
    @SerializedName("result") val result: String
): BaseResponse()