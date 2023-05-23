package com.pline.src.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pline.R
import com.pline.data.home.model.Comment
import com.pline.databinding.ItemCommentBinding
import com.pline.databinding.ItemPostBinding

class CommentRVAdapter(private val commentList: ArrayList<Comment>): RecyclerView.Adapter<CommentRVAdapter.ViewHolder>() {
    private lateinit var commentBinding: ItemCommentBinding

    inner class ViewHolder(val binding: ItemCommentBinding): RecyclerView.ViewHolder(binding.root){
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
        }
    }
}