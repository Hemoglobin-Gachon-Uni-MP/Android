package com.pline.src.main.home

import android.os.Bundle
import android.view.View
import com.pline.R
import com.pline.config.BaseFragment
import com.pline.databinding.FragmentMyPostRVBinding

// Real My Post List -> Only have recycler view
class MyPostRVFragment : BaseFragment<FragmentMyPostRVBinding>(FragmentMyPostRVBinding::bind, R.layout.fragment_my_post_r_v) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}