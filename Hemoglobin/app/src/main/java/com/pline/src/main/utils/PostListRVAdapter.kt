package com.pline.src.main.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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
        // Set data
        fun bind(post: FeedListResult) {
            postBinding.apply {
                itemPostUserNameTv.text = post.nickname
                itemPostContentsTextTv.text = post.context
                itemPostDateTv.text = post.date

                val defaultImage = R.drawable.ic_profile_ver1
                Glide.with(this.root)
                    .load(post.profileImg) // 불러올 이미지 url
                    .error(defaultImage) // 로딩 에러 발생 시 표시할 이미지
                    .fallback(defaultImage) // 로드할 url 이 비어있을(null 등) 경우 표시할 이미지
                    .into(itemPostProfileImageIv) // 이미지를 넣을 뷰
                itemPostCommentCountTv.text = post.commentCnt.toString()
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