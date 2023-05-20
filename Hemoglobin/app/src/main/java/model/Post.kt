package model

import android.os.Parcelable
import com.pline.R
import kotlinx.android.parcel.Parcelize

@Parcelize
// 글 데이터 객체
data class Post(
    val isBloodReceiver: Boolean = true,
    val profileImgInt: Int = R.drawable.ic_my_page_unselected,
    val nickName: String,
    val upload_date: String,
    val content: String,
    val commentNum: Int
): Parcelable
