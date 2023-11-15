package com.pline.src.main.myPage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pline.R
import com.pline.config.ApplicationClass
import com.pline.config.BaseFragment
import com.pline.data.mypage.MyPageRetrofitInterface
import com.pline.data.mypage.model.MyCertListResponse
import com.pline.data.mypage.model.MyCertListResult
import com.pline.data.mypage.model.MyPageFeedResult
import com.pline.data.mypage.model.MyPageResponse
import com.pline.databinding.FragmentCertificationListBinding
import com.pline.src.main.home.FragmentPostDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// Certification List
class CertificationListFragment() : BaseFragment<FragmentCertificationListBinding>(FragmentCertificationListBinding::bind, R.layout.fragment_certification_list) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getMyCertifications()

        binding.apply {
            // Set click event of back button
            imgbtnBack.setOnClickListener {
                // Move to previous page
                activity?.let { it.onBackPressed() }
            }
        }

        binding.btnAddCert.setOnClickListener { // 헌혈 인증 추가하기 버튼 클릭
            // certificationFragment open open
            val addCertFrag = CertificationFragment()
            addCertFrag.show(requireActivity().supportFragmentManager, addCertFrag.tag)
        }
    }

    // Get my page info through api
    private fun getMyCertifications() {
        val service = ApplicationClass.sRetrofit.create(MyPageRetrofitInterface::class.java)
        // Get jwt, userId from sp
        val jwt = "eyJ0eXBlIjoiand0IiwiYWxnIjoiSFMyNTYifQ.eyJ1c2VySWQiOjEsImlhdCI6MTcwMDA0ODQyMSwiZXhwIjoxNzYzMTIwNDIxfQ.Q86t9vwMhf97A0IKZC_dmUZQTuGKhgSjDW7cZvpkObI"
        if (jwt != null) {
            Log.d("seori", jwt)
        }

        // Request my page info through API
        jwt?.let {
            // Jwt is in header, userId is in Path Variable
            service.getMyCertifications("Bearer $jwt").enqueue(object : Callback<MyCertListResponse> {
                override fun onResponse(call: Call<MyCertListResponse>, response: Response<MyCertListResponse>) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        when (body?.code) {
                            // If success, fill the data
                            1000 -> {
                                val myCertList = body.result
                                binding.run {
                                    if (myCertList.isEmpty()) {
                                        rvCertList.visibility = View.GONE
                                    } else {
                                        setCertRecyclerView(myCertList)
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
                override fun onFailure(call: Call<MyCertListResponse>, t: Throwable) {
                    showCustomToast("네트워크 연결에 실패했습니다")
                    Log.d("seori1115", "?")
                }
            })
        }
    }

    // Set RecyclerView of my post list
    private fun setCertRecyclerView(myCertList: ArrayList<MyCertListResult>) {
        binding.rvCertList.run {
            // Set Recycler View Adapter
            val myCertAdapter = CertificationListRVAdapter(myCertList)
            adapter = myCertAdapter


            // Set layout of recycler view
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        }
    }
}