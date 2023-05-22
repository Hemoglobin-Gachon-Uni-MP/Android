package com.pline.data.home

import com.pline.data.home.model.GetFeedListResponse

interface HomeFragmentView {
    // 글 목록 반환
    fun onGetFeedListSuccess(response: GetFeedListResponse)
    fun onGetFeedListFailure(message: String)
}