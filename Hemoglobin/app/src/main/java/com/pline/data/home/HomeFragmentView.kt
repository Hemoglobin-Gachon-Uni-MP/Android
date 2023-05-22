package com.pline.data.home

import com.pline.data.home.model.FeedInfoResult
import com.pline.data.home.model.FeedListResult
import com.pline.data.home.model.FeedsResponse
import com.pline.data.home.model.GetFeedInfoResponse
import com.pline.data.home.model.GetFeedListResponse

interface HomeFragmentView {
    // 글 목록 반환
    fun onGetFeedListSuccess(response: ArrayList<FeedListResult>)
    fun onGetFeedListFailure(message: String)
}

interface CreateFeedFragmentView {
    fun onPostNewFeedSuccess(response: FeedsResponse)
    fun onPostNewFeedFailure(message: String)
}

interface FeedDetailView {
    fun onGetFeedInfoSuccess(response: FeedInfoResult)
    fun onGetFeedInfoFailure(message: String)
}