package com.pline.src.main.myPage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.pline.R
import com.pline.config.ApplicationClass
import com.pline.config.ApplicationClass.Companion.sSharedPreferences
import com.pline.config.BaseFragment
import com.pline.data.mypage.MyPageRetrofitInterface
import com.pline.data.mypage.model.*
import com.pline.databinding.FragmentMyPageBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageFragment :
    BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::bind, R.layout.fragment_my_page) {
    lateinit var myPostList: ArrayList<MyPageFeedResult>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            // Set click event of edit my info button
            imgbtnEditMyInfo.setOnClickListener {
                // Show(= Start) EditInfoActivity
                val intent = Intent(activity, EditInfoActivity::class.java)
                // Pass data
                intent.putExtra("myLocation", tvLocation.text)
                intent.putExtra("myNickname", tvNickname.text)
                startActivity(intent)
            }

            // Set click event of my post list button
            imgbtnMyPostList.setOnClickListener {
                // Show MyPostListFragment
                activity?.let {
                    it.supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, MyPostListFragment())
                        .addToBackStack(null)
                        .commitAllowingStateLoss()
                }
            }

            // Set click event of delete account button
            imgbtnDeleteAccount.setOnClickListener {
                // Show dialog for deleting account
                showDeleteDialog()
            }
        }
    }

    override fun onStart() {
        super.onStart()

        // Get my page info from server
        getMyPageInfo()
    }

    // Get my page info through api
    private fun getMyPageInfo() {
        val service = ApplicationClass.sRetrofit.create(MyPageRetrofitInterface::class.java)
        // Get jwt from sp
        val jwt = sSharedPreferences.getString("jwt", "")
        val userId = 10 // 더미
        // Request my page info through API
        jwt?.let {
            // Jwt is in header, userId is in Path Variable
            service.getMyPageInfo(jwt, userId).enqueue(object : Callback<MyPageResponse> {
                    override fun onResponse(call: Call<MyPageResponse>, response: Response<MyPageResponse>) {
                        if (response.isSuccessful) {
                            val body = response.body()
                            when (body?.code) {
                                // If success, fill the data
                                1000 -> {
                                    val result = body.result
                                    binding.run {
                                        tvNickname.text = result.nickname
                                        tvName.text = result.name + " (${result.gender})"
                                        tvBirth.text = result.birth
                                        tvLocation.text = result.location
                                        tvBloodType.text = result.blood
                                        myPostList = result.feedList

                                        if (myPostList.isEmpty()) {
                                            rvPostList.visibility = GONE
                                        } else {
                                            setPostRecyclerView()
                                        }
                                    }
                                }
                                // If GET fails, show toast message to user
                                else -> Toast.makeText(activity, body?.message, Toast.LENGTH_SHORT)
                                    .show()
                            }
                        } else {
                            // If fail, show toast message to user
                            Toast.makeText(activity, "네트워크 연결에 실패했습니다", Toast.LENGTH_SHORT).show()
                        }
                    }
                    // If fail, show toast message to user
                    override fun onFailure(call: Call<MyPageResponse>, t: Throwable) {
                        Toast.makeText(activity, "네트워크 연결에 실패했습니다", Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }

    // Set RecyclerView of my post list
    private fun setPostRecyclerView() {
        binding.rvPostList.run {
            // Set Recycler View Adapter
            val myPostAdapter = MyPostListRVHAdapter(myPostList)
            adapter = myPostAdapter
            // Set click event of my post element in recycler view
            myPostAdapter.setOnItemClickListener(object :
                MyPostListRVHAdapter.OnItemClickListener {
                override fun onMyPostClick(
                    post: MyPageFeedResult,
                    pos: Int
                ) {
                    /// todo - 글 상세 보기 화면 띄우기
                    Log.d("Seori", "Click my post")
                }
            })
            // Set layout of recycler view
            layoutManager =
                LinearLayoutManager(
                    this.context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            addItemDecoration(MyPostListRVHDecoration(30))
        }
    }

    // Show delete account dialog
    private fun showDeleteDialog() {
        activity?.let {
            DeleteDialog()
                .show(it.supportFragmentManager, "DeleteDialog")
        }
    }
}