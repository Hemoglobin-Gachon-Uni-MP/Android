package com.pline.src.main.myPage

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.pline.R
import com.pline.config.BaseFragment
import com.pline.data.home.model.FeedListResult
import com.pline.databinding.FragmentMyPostRVBinding
import com.pline.model.Post
import com.pline.src.main.utils.PostListRVAdapter

// Vertical My Post List -> Only have vertical recycler view
class MyPostListRVVFragment : BaseFragment<FragmentMyPostRVBinding>(FragmentMyPostRVBinding::bind, R.layout.fragment_my_post_r_v) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // My Post List data
        var myPostList = arrayListOf(
//            Post(false, nickName = "서리리", upload_date = "05/18", content = "더미 텍스트입니다.", commentNum = 3),
//            Post(false, nickName = "서리리", upload_date = "05/17", content = "더미 텍스트입니다.", commentNum = 3)
            Post(false, 0, "서리리", "05/18", "dummy", 0, 0, 0, 12, "location", 10)
        )

        binding.rvMyPostList.run {
            // Set Recycler View Adapter
            val postAdapter = PostListRVAdapter(myPostList)
            adapter = postAdapter
            // Set click event of my post element in recycler view
            postAdapter.setOnItemClickListener(object: PostListRVAdapter.OnItemClickListener {
                override fun onPostClick(post: Post, pos: Int) {
                    /// todo - 글 상세 보기 화면 띄우기
                    Log.d("Seori", "Click")
                }
            })
            // Set layout of recycler view
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        }
    }
}