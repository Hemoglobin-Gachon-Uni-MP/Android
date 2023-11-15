package com.pline.src.main.myPage

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.pline.R
import com.pline.config.BaseFragment
import com.pline.data.home.model.FeedListResult
import com.pline.data.mypage.model.MyPageFeedResult
import com.pline.databinding.FragmentMyPostRVBinding
import com.pline.src.main.home.FragmentPostDetail
import com.pline.src.main.utils.PostListRVAdapter

// Vertical My Post List -> Only have vertical recycler view
class MyPostListRVVFragment(var myPostList: ArrayList<MyPageFeedResult>) : BaseFragment<FragmentMyPostRVBinding>(FragmentMyPostRVBinding::bind, R.layout.fragment_my_post_r_v) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvMyPostList.run {
            var feedList: ArrayList<FeedListResult> = arrayListOf()
            myPostList.forEach {
                feedList.add(FeedListResult(null, it.commentCnt, it.context, it.date, it.feedId, it.isReceiver, null, it.nickname, it.profileImg, null, it.memberId))
            }

            // Set Recycler View Adapter
            val postAdapter = PostListRVAdapter(feedList)
            adapter = postAdapter
            // Set click event of my post element in recycler view
            postAdapter.setOnItemClickListener(object: PostListRVAdapter.OnItemClickListener {
                override fun onPostClick(feedId:Int) {
                    // Show clicked post
                    parentFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_frm, FragmentPostDetail(feedId))
                        .addToBackStack(null)
                        .commitAllowingStateLoss()
                }
            })
            // Set layout of recycler view
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        }
    }
}