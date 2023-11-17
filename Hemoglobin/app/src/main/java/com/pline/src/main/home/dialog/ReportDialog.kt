package com.pline.src.main.home.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.pline.config.ApplicationClass
import com.pline.data.home.DeleteReplyService
import com.pline.data.home.ReportService
import com.pline.data.home.ReportView
import com.pline.data.home.model.PostReportResponse
import com.pline.data.home.model.PostReportResult
import com.pline.data.home.model.postReportReqBody
import com.pline.databinding.DialogDeleteCommentBinding

class ReportDialog(val type: Int, val thisId: Int, val repMemId: Int): DialogFragment(), ReportView {
    private lateinit var binding: DialogDeleteCommentBinding
    val memId = ApplicationClass.sSharedPreferences.getInt("memberId", 0)
    var body = postReportReqBody("", thisId, memId, "뿡", repMemId)

    interface ToastListner{
        fun toast(msg: String)
    }
    private lateinit var mListner: ToastListner
    fun setReportListner(listner: ToastListner){
        mListner = listner
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        binding = DialogDeleteCommentBinding.inflate(inflater, container, false)
        binding.tvDeleteCommentGuide.text = "신고가 접수될 뿐 바로 삭제되지는 않습니다."
        binding.btnDeleteComment.text = "신고"
        when(type){
            0 -> {
                binding.tvDeleteComment.text = "이 글을 신고하시겠습니까?"
                body.category = "F"
            }
            1 -> {
                binding.tvDeleteComment.text = "이 댓글을 신고하시겠습니까?"
                body.category = "C"
            }
            2 -> {
                binding.tvDeleteComment.text = "이 답글을 신고하시겠습니까?"
                body.category = "R"
            }
        }
        setDialog()

        return binding.root
    }

    fun setDialog() = with(binding){
        // Transparent background for visible corner radius
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // Set click event of delete button
        btnDeleteComment.setOnClickListener {
            Log.d("ReportDialog", "Report Body : $body")
            ReportService(this@ReportDialog).tryPostReport(body)
            // mListner.toast()
            dismiss()
        }
        // Set click event of cancel button
        btnCancelComment.setOnClickListener {
            dismiss()
        }
    }

    override fun onPostReportSuccess(response: PostReportResponse) {

        Log.d("ReportSuccess", "result msg: ${response.result}")
        if (response.result != null){
            mListner.toast("정상적으로 신고되었습니다.")
        }else{
            mListner.toast("이미 접수된 신고입니다.")
        }
    }

    override fun onPostReportFailure(message: String) {
        Log.d("ReportDialog", "Report Fail $message")
    }
}