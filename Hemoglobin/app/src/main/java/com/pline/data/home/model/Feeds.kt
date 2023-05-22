package com.pline.data.home.model

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
    val abo: Int,
    val commentCnt: Int,
    val context: String,
    val date: String,
    val feedId: Int,
    val isReceiver: String,
    val location: String,
    val nickname: String,
    val profileImg: Int,
    val rh: Int,
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
