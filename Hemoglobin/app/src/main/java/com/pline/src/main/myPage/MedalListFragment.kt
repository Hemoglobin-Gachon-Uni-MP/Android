package com.pline.src.main.myPage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import androidx.recyclerview.widget.LinearLayoutManager
import com.pline.R
import com.pline.config.ApplicationClass
import com.pline.config.ApplicationClass.Companion.sSharedPreferences
import com.pline.config.BaseFragment
import com.pline.data.mypage.MyPageRetrofitInterface
import com.pline.data.mypage.model.*
import com.pline.databinding.FragmentMedalListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MedalListFragment :
    BaseFragment<FragmentMedalListBinding>(FragmentMedalListBinding::bind, R.layout.fragment_medal_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

        }
    }

    override fun onStart() {
        super.onStart()

        // Get my medal list from server
    }
}