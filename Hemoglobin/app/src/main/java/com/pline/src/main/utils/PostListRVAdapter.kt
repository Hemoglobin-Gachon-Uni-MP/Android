package com.pline.src.main.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pline.R
import com.pline.data.home.model.FeedListResult
import com.pline.databinding.ItemPostBinding

// Vertical RecyclerView adapter for My Post List
class PostListRVAdapter(private val postList: ArrayList<FeedListResult>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var postBinding: ItemPostBinding

    // Declare interface to click event
    interface OnItemClickListener {
        fun onPostClick(feedId: Int)
    }

    private lateinit var postListener: OnItemClickListener

    // Connect listener
    fun setOnItemClickListener(listener: OnItemClickListener) {
        postListener = listener
    }

    // Bind post with data
    inner class PostViewHolder(private val postBinding: ItemPostBinding): RecyclerView.ViewHolder(postBinding.root) {
        init {
            // Set Click Listener
        }
//        var itemView = postBinding.root

        // Set data
        fun bind(post: FeedListResult) {
            postBinding.apply {
                itemPostUserNameTv.text = post.nickname
                itemPostContentsTextTv.text = post.context
                itemPostDateTv.text = post.date
                itemPostProfileImageIv.setImageResource(R.drawable.ic_my_page_unselected)
                itemPostCommentCountTv.text = "${post.commentCnt}"
            }
        }
    }

    // Configure view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        postBinding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(postBinding)
    }

    // Set total item count to show
    override fun getItemCount(): Int = postList.size


    // Bind view holder with data
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PostViewHolder).bind(postList[holder.adapterPosition])

        holder.itemView.setOnClickListener {
            val pos = position
            if (pos != RecyclerView.NO_POSITION) {
                postListener.onPostClick(postList[pos].feedId)
            }
        }
    }
}