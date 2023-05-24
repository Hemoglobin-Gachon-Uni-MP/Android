package com.pline.src.main.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.pline.R
import com.pline.config.ApplicationClass
import com.pline.data.home.model.Reply
import com.pline.databinding.ItemReplyBinding

class ReplyRVAdapter(private var replyList: ArrayList<Reply>):
    RecyclerView.Adapter<ReplyRVAdapter.ViewHolder>() {

    val userId = ApplicationClass.sSharedPreferences.getInt("userId", 0)

    interface ReplyListener{
        fun dialog(replyId: Int)
    }
    lateinit var myListner: ReplyListener
    fun setMyReplyListner(listener: ReplyListener){
        myListner = listener
    }


    inner class ViewHolder(val binding: ItemReplyBinding): RecyclerView.ViewHolder(binding.root){
        val more = binding.itemReplyMoreIconIv
        val menu = binding.itemReplyMoreMenuReportTv

        fun bind(reply: Reply){
            with(binding){
                itemReplyProfileImageIv.setImageResource(R.drawable.ic_my_page_unselected)
                itemReplyUserNameTv.text = reply.nickname
                itemReplyContentsTextTv.text = reply.context
                itemReplyDateTv.text = reply.date
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val replyBinding= ItemReplyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(replyBinding)
    }

    override fun getItemCount(): Int = replyList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(replyList[position])

        holder.more.setOnClickListener {
            if (holder.menu.isVisible){
                holder.menu.visibility = View.GONE
            }else {
                holder.menu.visibility = View.VISIBLE
            }

            if (replyList[position].userId == userId){
                holder.menu.text = "삭제하기"
                holder.menu.setOnClickListener {
                    Log.d("Reply Delete", "CLICKED")
                    // 다이얼로그 띄우기
                    myListner.dialog(replyList[position].replyId)
                }
            } else{
                holder.menu.text = "댓글 신고하기"
            }

        }
    }
}