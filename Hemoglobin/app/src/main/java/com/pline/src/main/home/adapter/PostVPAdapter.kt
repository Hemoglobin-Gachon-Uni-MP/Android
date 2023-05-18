package com.pline.src.main.home.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.pline.src.main.home.FragmentPostProvider
import com.pline.src.main.home.FragmentPostUser

class PostVPAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> FragmentPostUser()
            else -> FragmentPostProvider()
        }
    }
}