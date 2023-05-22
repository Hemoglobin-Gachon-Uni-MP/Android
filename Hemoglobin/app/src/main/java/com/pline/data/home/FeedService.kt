package com.pline.data.home

import com.pline.config.ApplicationClass
import com.pline.data.home.model.FeedInfoResult
import com.pline.data.home.model.FeedsResponse
import com.pline.data.home.model.GetFeedInfoResponse
import com.pline.data.home.model.GetFeedListResponse
import com.pline.data.home.model.postFeedReqBody
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

class NewFeedService(val view: CreateFeedFragmentView) {
    val homeRetrofitInterface = ApplicationClass.sRetrofit.create(HomeRetrofitInterface::class.java)

    val jwt = ApplicationClass.sSharedPreferences.getString("jwt", "")

    fun tryPostNewFeed(body: postFeedReqBody){
        if (jwt != null) {
            homeRetrofitInterface.createNewPost(body, jwt).enqueue(object : Callback<FeedsResponse>{
                override fun onResponse(call: Call<FeedsResponse>, response: Response<FeedsResponse>) {
                    view.onPostNewFeedSuccess(response.body()!!)
                }

                override fun onFailure(call: Call<FeedsResponse>, t: Throwable) {
                    view.onPostNewFeedFailure(t.message ?: "통신 오류")
                }

            })
        }
    }
}

class FeedDetailService(val view: FeedDetailView) {
    val homeRetrofitInterface = ApplicationClass.sRetrofit.create(HomeRetrofitInterface::class.java)

    fun tryGetFeedDetail(feedId: Int){
        homeRetrofitInterface.getFeedInfo(feedId).enqueue(object : Callback<GetFeedInfoResponse>{
            override fun onResponse(
                call: Call<GetFeedInfoResponse>,
                response: Response<GetFeedInfoResponse>
            ) {
                view.onGetFeedInfoSuccess(response.body()!!.result)
            }

            override fun onFailure(call: Call<GetFeedInfoResponse>, t: Throwable) {
                view.onGetFeedInfoFailure(t.message ?: "통신 오류")
            }

        })
    }
}