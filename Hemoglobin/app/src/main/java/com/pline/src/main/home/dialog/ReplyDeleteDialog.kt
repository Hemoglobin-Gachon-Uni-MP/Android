package com.pline.src.main.home.dialog

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.pline.data.home.DeleteReplyService
import com.pline.data.home.DeleteReplyView
import com.pline.data.home.model.DeleteReplyResponse
import com.pline.databinding.DialogDeleteCommentBinding

class ReplyDeleteDialog(val replyId: Int): DialogFragment(),
    DeleteReplyView {
    private lateinit var binding: DialogDeleteCommentBinding

    interface ReplyDeleteListener{
        fun reset()
    }
    private lateinit var mListner: ReplyDeleteListener
    fun setReplyListner(listener: ReplyDeleteListener){
        mListner = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        binding = DialogDeleteCommentBinding.inflate(inflater, container, false)
        binding.tvDeleteCommentGuide.text = "한번 삭제한 답글은 되돌릴 수 없습니다."
        binding.tvDeleteComment.text = "답글을 삭제하시겠습니까?"
        setDialog()
        return binding.root
    }

    private fun setDialog() = with(binding) {
        // Transparent background for visible corner radius
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // Set click event of delete button
        btnDeleteComment.setOnClickListener {
            DeleteReplyService(this@ReplyDeleteDialog).tryDeleteReply(replyId)
        }
        // Set click event of cancel button
        btnCancelComment.setOnClickListener {
            dismiss()
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        Log.d("Ondismiss", "dismiss")
        mListner.reset()
    }

    override fun onDeleteReplySuccess(response: DeleteReplyResponse) {
        dismiss()
    }

    override fun onDeleteReplyFailure(message: String) {
        Log.d("onDeleteReplyFailure", message)
    }
}