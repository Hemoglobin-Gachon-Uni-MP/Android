package com.pline.data.home

import com.pline.data.home.model.FeedsResponse
import com.pline.data.home.model.postFeedReqBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface HomeRetrofitInterface {
    // 게시물 생성 API
    @POST("feeds")
    fun createNewPost(
        @Body data: postFeedReqBody
    ): Call<FeedsResponse>

}