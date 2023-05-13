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

    override fun onStart() {
        super.onStart()

        // 시작할 때 선택한 필터부분을 본인 기본 혈액형으로 하는 코드 필요
    }

    override fun onResume() {
        super.onResume()
    }
}