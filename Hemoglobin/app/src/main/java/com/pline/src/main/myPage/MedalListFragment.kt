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

        getMyMedals()
        binding.apply {
            // Set click event of back button
            imgbtnBack.setOnClickListener {
                // Move to previous page
                activity?.let { it.onBackPressed() }
            }
        }
    }

    // Get my medals through api
    private fun getMyMedals() {
        val service = ApplicationClass.sRetrofit.create(MyPageRetrofitInterface::class.java)
        // Get jwt, userId from sp
        val jwt = sSharedPreferences.getString("jwt", "")
        if (jwt != null) {
            Log.d("seori", jwt)
        }

        // Request my page info through API
        jwt?.let {
            // Jwt is in header, userId is in Path Variable
            service.getMyMedalList("Bearer $jwt").enqueue(object : Callback<MyMedalListResponse> {
                override fun onResponse(call: Call<MyMedalListResponse>, response: Response<MyMedalListResponse>) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        when (body?.code) {
                            // If success, fill the data
                            1000 -> {
                                val myMedalList = body.result.rewardList
                                binding.run {
                                    if (myMedalList.isEmpty()) {
                                        rvMedalList.visibility = View.GONE
                                    } else {
                                        setMedalRecyclerView(myMedalList)
                                    }
                                }
                            }
                            // If GET fails, show toast message to user
                            else -> body?.message?.let { it1
                                -> showCustomToast(it1)
                                Log.d("seori", response.message())
                            }
                        }
                    } else {
                        // If fail, show toast message to user
                        showCustomToast("네트워크 연결에 실패했습니다")
                        Log.d("seori1115", response.body().toString())
                    }
                }
                // If fail, show toast message to user
                override fun onFailure(call: Call<MyMedalListResponse>, t: Throwable) {
                    showCustomToast("네트워크 연결에 실패했습니다")
                    Log.d("seori1115", "?")
                }
            })
        }
    }

    // Set RecyclerView of my medals
    private fun setMedalRecyclerView(myMedalList: ArrayList<MedalInfo>) {
        binding.rvMedalList.run {
            // Set Recycler View Adapter
            val medalAdapter = MedalListRVAdapter(myMedalList)
            adapter = medalAdapter

            // Set layout of recycler view
            layoutManager = GridLayoutManager(this.context, 2)
            addItemDecoration(MedalListRVDecoration(spanCount = 2,90))
        }
    }
}