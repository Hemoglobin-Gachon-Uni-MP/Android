package com.pline.src.main.home

import android.os.Bundle
import android.view.View
import com.pline.R
import com.pline.config.BaseFragment
import com.pline.databinding.FragmentHomeBinding


class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}