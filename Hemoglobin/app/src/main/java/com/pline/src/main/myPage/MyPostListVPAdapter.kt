package com.pline.src.main.myPage

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.pline.src.main.home.MyPostRVFragment

// ViewPager adapter for my post list
class MyPostListVPAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    // Set ViewPager total item count
    override fun getItemCount() = 2

    // Set page according to clicked position
    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> MyPostRVFragment()
            1 -> MyPostRVFragment()
            else -> MyPostRVFragment()
        }
    }
}