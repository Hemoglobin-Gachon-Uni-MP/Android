package com.pline.src.main.myPage

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.pline.data.mypage.model.MyPageFeedResult

// ViewPager adapter for my post list
class MyPostListVPAdapter(fragmentActivity: FragmentActivity, var postList: ArrayList<MyPageFeedResult>): FragmentStateAdapter(fragmentActivity) {
    // Set ViewPager total item count
    override fun getItemCount() = 2

    // Set page according to clicked position
    override fun createFragment(position: Int): Fragment {
        val postListFromReceiver = postList.filter { it.isReceiver }
        val postListFromProvider = postList.filter { !it.isReceiver }

        return when(position) {
            0 -> MyPostListRVVFragment(postListFromReceiver as ArrayList<MyPageFeedResult>)
            1 -> MyPostListRVVFragment(postListFromProvider as ArrayList<MyPageFeedResult>)
            else -> MyPostListRVVFragment(postListFromReceiver as ArrayList<MyPageFeedResult>)
        }
    }
}