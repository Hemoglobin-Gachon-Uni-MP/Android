package com.pline.src.main.home.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.pline.data.home.DeleteReplyService
import com.pline.databinding.DialogDeleteCommentBinding

class ReportDialog(val type: Int): DialogFragment() {
    private lateinit var binding: DialogDeleteCommentBinding

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
            0 -> binding.tvDeleteComment.text = "이 글을 신고하시겠습니까?"
            1 -> binding.tvDeleteComment.text = "이 댓글을 신고하시겠습니까?"
            2 -> binding.tvDeleteComment.text = "이 답글을 신고하시겠습니까?"
        }
        setDialog()

        return binding.root
    }

    fun setDialog() = with(binding){
        // Transparent background for visible corner radius
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // Set click event of delete button
        btnDeleteComment.setOnClickListener {
            dismiss()
        }
        // Set click event of cancel button
        btnCancelComment.setOnClickListener {
            dismiss()
        }
    }
}