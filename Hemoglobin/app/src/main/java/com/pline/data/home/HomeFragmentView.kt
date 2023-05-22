package com.pline.data.home

import com.pline.data.home.model.FeedListResult
import com.pline.data.home.model.GetFeedListResponse

interface HomeFragmentView {
    // 글 목록 반환
    fun onGetFeedListSuccess(response: ArrayList<FeedListResult>)
    fun onGetFeedListFailure(message: String)
}