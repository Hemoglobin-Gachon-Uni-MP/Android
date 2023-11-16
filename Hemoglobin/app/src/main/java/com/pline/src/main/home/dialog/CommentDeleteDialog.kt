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
import com.pline.data.home.CommentView
import com.pline.data.home.DeleteCommentService
import com.pline.data.home.model.DeleteCommentResponse
import com.pline.databinding.DialogDeleteCommentBinding

class CommentDeleteDialog(val commentId: Int): DialogFragment(), CommentView {
    private lateinit var binding: DialogDeleteCommentBinding

    interface Listner{
        fun reset()
    }
    private lateinit var mListner: Listner
    fun setmListner(listner: Listner){
        mListner = listner
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        binding = DialogDeleteCommentBinding.inflate(inflater, container, false)
        setDialog()
        return binding.root
    }

    private fun setDialog() = with(binding) {
        // Transparent background for visible corner radius
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // Set click event of delete button
        btnDeleteComment.setOnClickListener {
            DeleteCommentService(this@CommentDeleteDialog).tryDeleteComment(commentId)
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

    override fun onDeleteCommentSuccess(response: DeleteCommentResponse) {
        Log.d("onDeleteComment", "SUCCESS")
        dismiss()
    }

    override fun onDeleteCommentFailure(message: String) {
        Log.d("onDeleteCommentFailure", message)
    }
}