package com.pline.src.main.myPage

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
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
            // Set click event of back button
            imgbtnBack.setOnClickListener {
                // Move to previous page
                activity?.let { it.onBackPressed() }
            }

            rvMedalList.run {
                var medalList: ArrayList<MedalDummyData> = arrayListOf()
                medalList.add(MedalDummyData(1,"23.10.27"))
                medalList.add(MedalDummyData(2,"23.10.27"))
                medalList.add(MedalDummyData(3,"23.10.27"))
                medalList.add(MedalDummyData(4,"23.10.27"))
                medalList.add(MedalDummyData(5,"23.10.27"))
                medalList.add(MedalDummyData(6,"23.10.27"))
                medalList.add(MedalDummyData(7,"23.10.27"))
                medalList.add(MedalDummyData(8,"23.10.27"))

                // Set Recycler View Adapter
                val medalAdapter = MedalListRVAdapter(medalList)
                adapter = medalAdapter

                // Set layout of recycler view
                layoutManager = GridLayoutManager(this.context, 2)
                addItemDecoration(MedalListRVDecoration(spanCount = 2,90))
            }
        }
    }
}