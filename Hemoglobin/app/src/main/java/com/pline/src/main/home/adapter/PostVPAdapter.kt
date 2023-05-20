package com.pline.src.main.home.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.pline.src.main.home.FragmentPostDonation
import com.pline.src.main.home.FragmentPostTranfusion

class PostVPAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> FragmentPostTranfusion()
            else -> FragmentPostDonation()
        }
    }
}