package com.pline.src.main.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pline.R
import com.pline.databinding.ItemPostBinding
import com.pline.model.Post

// Vertical RecyclerView adapter for My Post List
class PostListRVAdapter(private val postList: ArrayList<Post>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var postBinding: ItemPostBinding
    private lateinit var postListener: OnItemClickListener

    // Connect listener
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.postListener = listener
    }

    // Declare interface to click event
    interface OnItemClickListener {
        fun onPostClick(post: Post, pos: Int)
    }

    // Bind post with data
    inner class PostViewHolder(private val postBinding: ItemPostBinding): RecyclerView.ViewHolder(postBinding.root) {
        init {
            // Set Click Listener
            postBinding.root.setOnClickListener {
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    postListener.onPostClick(postList[pos], pos)
                }
            }
        }

        // Set data
        fun bind(post: Post) {
            postBinding.apply {
                itemPostUserNameTv.text = post.nickName
                itemPostContentsTextTv.text = post.content
                itemPostDateTv.text = post.upload_date
                itemPostProfileImageIv.setImageResource(R.drawable.ic_my_page_unselected)
                itemPostCommentCountTv.text = "${post.commentNum}"
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
    }
}