package com.pline.data.mypage.model

import com.pline.config.BaseResponse

// Response data class for getting my page info
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
    val memberId: Int
)

data class MyPageFeedResult(
    val commentCnt: Int,
    val context: String,
    val date: String,
    val feedId: Int,
    val isReceiver: String,
    val nickname: String,
    val profileImg: Int,
    val memberId: Int
)

// Request data class for editing my page info
data class MyPageEditRequest(
    val location: String,
    val nickname: String
) : BaseResponse()

// Response data class for editing my page info
data class MyPageEditResponse(
    val result: String
) : BaseResponse()

// Response data class for getting my medals
data class MyMedalListResponse(
    val result: MyMedalListResult
) : BaseResponse()

data class MyMedalListResult(
    val certificationCnt: Int,
    val memberId: Int,
    val rewardList: ArrayList<MedalInfo>
)
data class MedalInfo(
    val date: String,
    val medalImg: String,
    val name: String
)

// Response data class for getting my certifications
data class MyCertListResponse(
    val result: ArrayList<MyCertListResult>
) : BaseResponse()

data class MyCertListResult(
    val certificationId: Int,
    val certificationNum: String,
    val date: String,
    val memberId: Int,
    val name: String
)

// Response data class for deleting my account
data class MyAccountDeleteResponse(
    val result: String
) : BaseResponse()