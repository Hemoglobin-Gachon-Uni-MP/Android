package com.pline.data.home

import com.pline.config.ApplicationClass
import com.pline.data.home.model.GetFeedListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class FeedService(val view: HomeFragmentView) {
    val homeRetrofitInterface = ApplicationClass.sRetrofit.create(HomeRetrofitInterface::class.java)

    fun tryGetFeedList() {
        homeRetrofitInterface.getFeedList().enqueue(object: Callback<GetFeedListResponse>{
            override fun onResponse(
                call: Call<GetFeedListResponse>,
                response: Response<GetFeedListResponse>
            ) {
                view.onGetFeedListSuccess(response.body()!!.result)
            }

            override fun onFailure(call: Call<GetFeedListResponse>, t: Throwable) {
                view.onGetFeedListFailure(t.message ?: "통신 오류")
            }

        })
    }
}