package com.pline.src.main.myPage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pline.databinding.ItemMyPostBinding

// Horizontal RecyclerView adapter for My Post List
class MyPostListRVHAdapter(private val myPostList: ArrayList<MyPost>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var myPostBinding: ItemMyPostBinding
    private lateinit var myPostListener: OnItemClickListener

    // Bind post with data
    inner class PostViewHolder(private val myPostBinding: ItemMyPostBinding): RecyclerView.ViewHolder(myPostBinding.root) {
        init {
            // Set Click Listener
            myPostBinding.root.setOnClickListener {
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    myPostListener.onMyPostClick(myPostList[pos], pos)
                }
            }
        }

        // Set data
        fun bind(post: MyPost) {
            myPostBinding.apply {
                tvDate.text = post.upload_date
                tvContent.text = post.content
                tvCommentsNum.text = "${post.commentNum}"
            }
        }
    }

    // Configure view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        myPostBinding = ItemMyPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(myPostBinding)
    }

    // Bind view holder with data
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PostViewHolder).bind(myPostList[holder.adapterPosition])
    }

    // Set total item count to show
    override fun getItemCount(): Int = myPostList.size

    // Connect listener
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.myPostListener = listener
    }

    // Declare interface to click event
    interface OnItemClickListener {
        fun onMyPostClick(post: MyPost, pos: Int)
    }
}