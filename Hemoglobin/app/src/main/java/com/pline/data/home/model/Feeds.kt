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
    var isReceiver: Boolean,
    var location: String,
    var rh: Int
)

data class FeedListResult(
    val abo: Int?,
    val commentCnt: Int,
    val context: String,
    val date: String,
    val feedId: Int,
    val isReceiver: Boolean,
    val location: String?,
    val memberId: Int,
    val nickname: String,
    val profileImg: String,
    val rh: Int?
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
    var context: String,
    var date: String,
    var isReportedFromUser: Boolean,
    var memberId: Int,
    var nickname: String,
    var profileImg: Int,
    var replyList: ArrayList<Reply>
)

data class Reply(
    var context: String,
    var date: String,
    val isReportedFromUser: Boolean,
    var memberId: Int,
    var nickname: String,
    var profileImg: Int,
    var replyId: Int
)

data class FeedInfoResult(
    @SerializedName("abo") var abo: Int,
    @SerializedName("commentCnt") var commentCnt: Int,
    @SerializedName("commentList") var commentList: ArrayList<Comment>?,
    @SerializedName("context") var context: String,
    @SerializedName("date") var date: String,
    @SerializedName("feedId") var feedId: Int,
    @SerializedName("isReceiver") var isReceiver: Boolean,
    @SerializedName("isReportedFromUser") var isReportedFromUser: Boolean,
    @SerializedName("location") var location: String,
    @SerializedName("memberId") var memberId: Int,
    @SerializedName("nickname") var nickname: String,
    @SerializedName("profileImg") var profileImg: String,
    @SerializedName("rh") var rh: Int
)

data class baseUserIdReq(
    val userId: Int
)

data class DeleteFeedResponse(
    @SerializedName("result") val result: String
): BaseResponse()

data class PostCommentReqBody(
    var context: String
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
    @SerializedName("feedId") val feedId: Int
)

data class PostNewReplyResponse(
    @SerializedName("result") val result: Int
): BaseResponse()

data class DeleteReplyResponse(
    @SerializedName("result") val result: String
): BaseResponse()