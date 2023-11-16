package com.pline.src.main.home.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pline.R
import com.pline.config.ApplicationClass
import com.pline.data.home.model.Comment
import com.pline.data.home.model.Reply
import com.pline.databinding.ItemCommentBinding
import com.pline.databinding.ItemPostBinding

class CommentRVAdapter(private val commentList: ArrayList<Comment>): RecyclerView.Adapter<CommentRVAdapter.ViewHolder>() {
    private lateinit var commentBinding: ItemCommentBinding
    lateinit var replyRVAdapter: ReplyRVAdapter

    interface MyCommentListner{
        fun dialog(commentId: Int)
        fun reply(commentId: Int)
        fun replyDialog(replyId: Int)
        fun reportComment()
        fun reportReply()
    }

    private lateinit var myCommentListner: MyCommentListner
    fun setMyCommentListner(listner: MyCommentListner){
        myCommentListner = listner
    }

    val userId = ApplicationClass.sSharedPreferences.getInt("userId", 0)

    inner class ViewHolder(val binding: ItemCommentBinding, val context: Context): RecyclerView.ViewHolder(binding.root){
        val more = binding.itemCommentMoreIconIv
        val menu = binding.itemCommentMoreMenuReportTv
        val reply = binding.itemCommentReplyIconIv
        fun bind(comment: Comment){
            with(binding){
//                if (comment.profileImg == 1){
//                    itemCommentProfileImageIv.setImageResource(R.drawable.ic_profile_ver1)
//                } else {
//                    itemCommentProfileImageIv.setImageResource(R.drawable.ic_profile_ver2)
//                }

                val defaultImage = R.drawable.ic_profile_ver1
                Glide.with(context)
                    .load(comment.profileImg) // 불러올 이미지 url
                    .error(defaultImage) // 로딩 에러 발생 시 표시할 이미지
                    .fallback(defaultImage) // 로드할 url 이 비어있을(null 등) 경우 표시할 이미지
                    .into(itemCommentProfileImageIv) // 이미지를 넣을 뷰

                itemCommentUserNameTv.text = comment.nickname
                itemCommentReplyCountTv.text = comment.replyList.size.toString()
                itemCommentContentsTextTv.text = comment.context
                itemCommentDateTv.text = comment.date

                val replyList = comment.replyList
                if (comment.replyList.size != 0){
                    itemCommentReplyRv.visibility = View.VISIBLE
                    replyRVAdapter = ReplyRVAdapter(replyList)
                    replyRVAdapter.setMyReplyListner(object : ReplyRVAdapter.ReplyListener{
                        override fun dialog(replyId: Int) {
                            // send replyId to dialog fragment
                            myCommentListner.replyDialog(replyId)
                        }

                        override fun reportReply() {
                            myCommentListner.reportReply()
                        }
                    })
                    itemCommentReplyRv.adapter = replyRVAdapter
                    itemCommentReplyRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                } else{
                    itemCommentReplyRv.visibility = View.GONE
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        commentBinding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(commentBinding, parent.context)
    }

    override fun getItemCount(): Int = commentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(commentList[position])
        holder.more.setOnClickListener {
            if (holder.menu.isVisible){
                holder.menu.visibility = View.GONE
            }else {
                holder.menu.visibility = View.VISIBLE

            }
        }
        if (commentList[position].memberId == userId){
            holder.menu.text = "댓글 삭제하기"
            holder.menu.setOnClickListener {
                // deleting dialog
                myCommentListner.dialog(commentList[position].commentId)
                Log.d("Comments Delete", "Wanna Delete?")
            }
        } else{
            holder.menu.text = "댓글 신고하기"
            holder.menu.setOnClickListener {
                holder.menu.visibility = View.GONE
                myCommentListner.reportComment()
            }
        }

        holder.reply.setOnClickListener {
            myCommentListner.reply(commentList[position].commentId)
        }
    }

//    fun setDefaultProfile(imgView: ImageView, imgUrlString: String) {
//        val defaultImage = R.drawable.ic_profile_ver1
//        Glide.with()
//            .load(imgUrlString) // 불러올 이미지 url
//            .error(defaultImage) // 로딩 에러 발생 시 표시할 이미지
//            .fallback(defaultImage) // 로드할 url 이 비어있을(null 등) 경우 표시할 이미지
//            .into(imgView) // 이미지를 넣을 뷰
//    }
}