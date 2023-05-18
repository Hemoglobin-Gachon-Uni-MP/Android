package com.pline.src.main.home

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayoutMediator
import com.pline.R
import com.pline.config.BaseFragment
import com.pline.databinding.FragmentMyPostListBinding
import com.pline.src.main.myPage.MyPostListVPAdapter

// My Post List Tab
class MyPostListFragment : BaseFragment<FragmentMyPostListBinding>(FragmentMyPostListBinding::bind, R.layout.fragment_my_post_list) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set textview of first tab
        var receiverTv = TextView(activity)
        receiverTv.apply {
            text = "수혈자"
            textSize = 16F
            setTextColor(ContextCompat.getColor(context, R.color.black))
            gravity = Gravity.CENTER
        }

        // Set textview of second tab
        var senderTv = TextView(activity)
        senderTv.apply {
            text = "공혈자"
            textSize = 16F
            setTextColor(ContextCompat.getColor(context, R.color.black))
            gravity = Gravity.CENTER
        }

        // Configure textview array
        val tapTextViewArr = arrayOf(
            receiverTv,
            senderTv
        )

        // Set ViewPager adapter
        binding.vpMyPostList.adapter = activity?.let { MyPostListVPAdapter(it) }
        // Set customview of each tab
        TabLayoutMediator(binding.tabMyPostList, binding.vpMyPostList) { tab, pos ->
            tab.customView = tapTextViewArr[pos]
        }.attach()
    }
}