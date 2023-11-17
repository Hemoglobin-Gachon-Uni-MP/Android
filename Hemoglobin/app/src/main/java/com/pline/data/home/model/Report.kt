package com.pline.data.home.model

import com.google.gson.annotations.SerializedName
import com.pline.config.BaseResponse

data class postReportReqBody(
    var category: String, // F, C, R
    val feedOrCommentId: Int, // 게시물이나 댓글 답글 어쩌구 아이
    val memberId: Int, // 신고자
    var reason: String,
    var reportedMemberId: Int // 당한사람
)

data class PostReportResponse(
    @SerializedName("result") val result: String
): BaseResponse()

data class PostReportResult(
    @SerializedName("result") val result: String
)