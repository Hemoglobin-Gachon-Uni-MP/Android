package com.pline.model

import com.pline.config.BaseResponse


data class RegisterRequest(
    val abo: Int,
    val birth: String,
    val gender: String,
    val location: String,
    val name: String,
    val nickname: String,
    val phone: String,
    val rh: Int,
    val idToken: String,
)

data class RegisterResponse(
    val result: RegisterResult
) : BaseResponse()

data class RegisterResult(
    val jwt: String,
    val userId: Int
)