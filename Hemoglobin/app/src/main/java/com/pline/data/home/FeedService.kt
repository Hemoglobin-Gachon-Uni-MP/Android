package com.pline.data.home

import com.pline.config.ApplicationClass
import com.pline.data.home.model.DeleteCommentResponse
import com.pline.data.home.model.DeleteFeedResponse
import com.pline.data.home.model.EditPostResponse
import com.pline.data.home.model.FeedInfoResult
import com.pline.data.home.model.FeedsResponse
import com.pline.data.home.model.GetFeedInfoResponse
import com.pline.data.home.model.GetFeedListResponse
import com.pline.data.home.model.PostCommentReqBody
import com.pline.data.home.model.PostNewCommentResponse
import com.pline.data.home.model.baseUserIdReq
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

    val jwt = ApplicationClass.sSharedPreferences.getString("jwt", "")

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


    fun tryPostNewComment(feedId: Int, body: PostCommentReqBody){
        if (jwt != null){
            homeRetrofitInterface.postNewComment(feedId, body, jwt).enqueue(object : Callback<PostNewCommentResponse>{
                override fun onResponse(
                    call: Call<PostNewCommentResponse>,
                    response: Response<PostNewCommentResponse>
                ) {
                    response.body()?.let { view.onPostNewCommentSuccess(it) }
                }

                override fun onFailure(call: Call<PostNewCommentResponse>, t: Throwable) {
                    view.onPostNewCommentFailure(t.message ?: "통신 오류")
                }

            })
        }
    }
}

class FeedDeleteService(val view:FeedDeleteView){
    val homeRetrofitInterface = ApplicationClass.sRetrofit.create(HomeRetrofitInterface::class.java)

    val jwt = ApplicationClass.sSharedPreferences.getString("jwt", "")
    fun tryDeleteFeed(body: baseUserIdReq, feedId: Int){
        if (jwt != null){
            homeRetrofitInterface.deletePost(body, feedId, jwt).enqueue(object : Callback<DeleteFeedResponse>{
                override fun onResponse(
                    call: Call<DeleteFeedResponse>,
                    response: Response<DeleteFeedResponse>
                ) {
                    view.onDeleteFeedSuccess(response.body()!!)
                }

                override fun onFailure(call: Call<DeleteFeedResponse>, t: Throwable) {
                    view.onDeleteFeedFailure(t.message?:"통신 오류")
                }

            })
        }
    }
}

class EditPostService(val view:FeedEditView){
    val homeRetrofitInterface = ApplicationClass.sRetrofit.create(HomeRetrofitInterface::class.java)

    val jwt = ApplicationClass.sSharedPreferences.getString("jwt", "")

    fun tryEditFeed(feedId: Int, body: PostCommentReqBody){
        if (jwt != null){
            homeRetrofitInterface.editPost(feedId, body, jwt).enqueue(object : Callback<EditPostResponse>{
                override fun onResponse(
                    call: Call<EditPostResponse>,
                    response: Response<EditPostResponse>
                ) {
                    view.onEditFeedSuccess(response.body()!!)
                }

                override fun onFailure(call: Call<EditPostResponse>, t: Throwable) {
                    view.onEditFeedFailure(t.message ?: "통신 오류")
                }

            })
        }
    }
}

class DeleteCommentService(val view: CommentView){
    val homeRetrofitInterface = ApplicationClass.sRetrofit.create(HomeRetrofitInterface::class.java)

    val jwt = ApplicationClass.sSharedPreferences.getString("jwt", "")

    fun tryDeleteComment(body: baseUserIdReq, commentId: Int){
        if (jwt != null){
            homeRetrofitInterface.deleteComment(body, commentId, jwt).enqueue(object : Callback<DeleteCommentResponse>{
                override fun onResponse(
                    call: Call<DeleteCommentResponse>,
                    response: Response<DeleteCommentResponse>
                ) {
                    view.onDeleteCommentSuccess(response.body()!!)
                }

                override fun onFailure(call: Call<DeleteCommentResponse>, t: Throwable) {
                    view.onDeleteCommentFailure(t.message ?: "통신 오류")
                }
            })
        }
    }
}