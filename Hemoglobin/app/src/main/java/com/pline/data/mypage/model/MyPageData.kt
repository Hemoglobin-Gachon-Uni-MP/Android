package com.pline.data.mypage.model

import com.pline.config.BaseResponse

// Response data class for get my page info
data class MyPageResponse(
    val result: MyPageResult
) : BaseResponse()

data class MyPageResult(
    val birth: String,
    val blood: String,
    val feedList: ArrayList<MyPageFeedResult>,
    val gender: String,
    val location: String,
    val name: String,
    val nickname: String,
    val phone: String,
    val profileImg: Int,
    val userId: Int
)

data class MyPageFeedResult(
    val commentCnt: Int,
    val context: String,
    val date: String,
    val feedId: Int,
    val isReceiver: Boolean,
    val nickname: String,
    val profileImg: Int,
    val userId: Int
)

// Request data class for edit my page info
data class MyPageEditRequest(
    val location: String,
    val nickname: String
) : BaseResponse()

// Response data class for edit my page info
data class MyPageEditResponse(
    val result: String
) : BaseResponse()
