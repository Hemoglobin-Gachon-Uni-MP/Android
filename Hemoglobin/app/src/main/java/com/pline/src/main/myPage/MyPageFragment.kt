package com.pline.src.main.myPage

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.pline.R
import com.pline.config.BaseFragment
import com.pline.databinding.FragmentMyPageBinding

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::bind, R.layout.fragment_my_page) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            imgbtnEditMyInfo.setOnClickListener {
                // fragment -> activity 전환
                val intent = Intent(activity, EditInfoActivity::class.java)
                startActivity(intent)
            }
        }
    }
}