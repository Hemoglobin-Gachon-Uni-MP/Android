package com.pline.data.home

import com.pline.config.ApplicationClass
import com.pline.data.home.model.PostReportResponse
import com.pline.data.home.model.postReportReqBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReportService(val view: ReportView) {
    val reportRetrofitInterface = ApplicationClass.sRetrofit.create(ReportRetrofitInterface::class.java)
    val jwt = ApplicationClass.sSharedPreferences.getString("jwt", "")

    fun tryPostReport(body: postReportReqBody){
        if (jwt != null){
            reportRetrofitInterface.newPostReport("Bearer $jwt", body).enqueue(object : Callback<PostReportResponse>{
                override fun onResponse(
                    call: Call<PostReportResponse>,
                    response: Response<PostReportResponse>
                ) {
                    view.onPostReportSuccess(response.body()!!)
                }

                override fun onFailure(call: Call<PostReportResponse>, t: Throwable) {
                    view.onPostReportFailure(t.message ?: "통신 오류")
                }

            })
        }
    }

    fun tryPatchReport(){

    }
}
