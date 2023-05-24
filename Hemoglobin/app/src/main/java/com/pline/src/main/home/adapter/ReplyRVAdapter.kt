package com.pline.src.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pline.R
import com.pline.config.ApplicationClass
import com.pline.data.home.model.Reply
import com.pline.databinding.ItemReplyBinding

class ReplyRVAdapter(private var replyList: ArrayList<Reply>):
    RecyclerView.Adapter<ReplyRVAdapter.ViewHolder>() {

    val userId = ApplicationClass.sSharedPreferences.getInt("userId", 0)

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
    }
}