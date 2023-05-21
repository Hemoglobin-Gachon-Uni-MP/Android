package com.pline.src.main.myPage

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
// 내 글 데이터 객체
data class MyPost(
    val upload_date: String,
    val content: String,
    val commentNum: Int = 0
): Parcelable