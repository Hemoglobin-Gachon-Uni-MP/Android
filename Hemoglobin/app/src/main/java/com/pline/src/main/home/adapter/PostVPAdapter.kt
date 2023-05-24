package com.pline.src.main.home.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.pline.src.main.home.FragmentPostProvider
import com.pline.src.main.home.FragmentPostReceiver

class PostVPAdapter(fragment: Fragment, val abo: ArrayList<Int>, val rh: Int, val location: String): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> FragmentPostReceiver(abo, rh, location)
            else -> FragmentPostProvider(abo, rh, location)
        }
    }
}