package com.pline.src.main.home

import android.os.Bundle
import android.util.Log
import android.view.View
import com.pline.R
import com.pline.config.BaseFragment
import com.pline.databinding.FragmentPostDetailBinding

class FragmentPostDetail(val feedId: Int): BaseFragment<FragmentPostDetailBinding>(FragmentPostDetailBinding::bind, R.layout.fragment_post_detail) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("FragmentPostDetail", "onCreated")
    }

}