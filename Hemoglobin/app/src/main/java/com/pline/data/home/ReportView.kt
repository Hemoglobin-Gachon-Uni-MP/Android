package com.pline.data.home

import com.pline.data.home.model.PostReportResponse
import com.pline.data.home.model.PostReportResult
import retrofit2.Response

interface ReportView{
    fun onPostReportSuccess(response: PostReportResponse)
    fun onPostReportFailure(message: String)
}