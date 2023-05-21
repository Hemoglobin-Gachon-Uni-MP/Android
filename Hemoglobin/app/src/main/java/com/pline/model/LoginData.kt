package com.pline.model

import com.pline.config.BaseResponse

data class LoginResponse(
    val result: LoginResult
) : BaseResponse()

data class LoginResult(
    val jwt: String,
    val userId: Int
)