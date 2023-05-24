package com.pline.src.main.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.pline.R
import com.pline.config.ApplicationClass
import com.pline.data.home.model.Comment
import com.pline.databinding.ItemCommentBinding
import com.pline.databinding.ItemPostBinding

class CommentRVAdapter(private val commentList: ArrayList<Comment>): RecyclerView.Adapter<CommentRVAdapter.ViewHolder>() {
    private lateinit var commentBinding: ItemCommentBinding

    interface MyCommentListner{
        fun dialog(commentId: Int)
    }

    private lateinit var myCommentListner: MyCommentListner
    fun setMyCommentListner(listner: MyCommentListner){
        myCommentListner = listner
    }

    val userId = ApplicationClass.sSharedPreferences.getInt("userId", 0)
    inner class ViewHolder(val binding: ItemCommentBinding): RecyclerView.ViewHolder(binding.root){
        val more = binding.itemCommentMoreIconIv
        val menu = binding.itemCommentMoreMenuReportTv
        fun bind(comment: Comment){
            with(binding){
                itemCommentProfileImageIv.setImageResource(R.drawable.ic_my_page_unselected)
                itemCommentUserNameTv.text = comment.nickname
                itemCommentReplyCountTv.text = comment.replyList.size.toString()
                itemCommentContentsTextTv.text = comment.context
                itemCommentDateTv.text = comment.date
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        commentBinding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(commentBinding)
    }

    override fun getItemCount(): Int = commentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (commentList != null){
            holder.bind(commentList[position])
            holder.more.setOnClickListener {
                if (holder.menu.isVisible){
                    holder.menu.visibility = View.GONE
                }else {
                    holder.menu.visibility = View.VISIBLE

                }
            }
            if (commentList[position].userId == userId){
                holder.menu.text = "댓글 삭제하기"
                holder.menu.setOnClickListener {
                    // 다이얼로그 띄우기
                    myCommentListner.dialog(commentList[position].commentId)
                    Log.d("Comments Delete", "Wanna Delete?")
                }
            } else{
                holder.menu.text = "댓글 신고하기"
            }
        }
    }
}