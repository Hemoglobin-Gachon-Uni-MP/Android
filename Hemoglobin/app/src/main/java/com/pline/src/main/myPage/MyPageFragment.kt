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
import com.pline.databinding.FragmentMyPageBinding
import com.pline.src.main.home.FragmentPostDetail
import com.pline.src.main.register.LoginActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageFragment :
    BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::bind, R.layout.fragment_my_page) {
    lateinit var myPostList: ArrayList<MyPageFeedResult>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            // Set click event of my post list button
            myPostListContainerCl.setOnClickListener {
                // Show MyPostListFragment
                activity?.let {
                    it.supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, MyPostListFragment(myPostList))
                        .addToBackStack(null)
                        .commitAllowingStateLoss()
                }
            }

            // Set click event of delete account button
            imgbtnDeleteAccount.setOnClickListener {
                // Show dialog for deleting account
                showDeleteDialog()
            }

            // Set click event of logout button
            imgbtnLogout.setOnClickListener {
                // Clear all shared preferences
                sSharedPreferences.edit().clear().commit()
                // Show(= Start) LoginActivity
                val intent = Intent(activity, LoginActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }

            btnCertList.setOnClickListener {
                // Show CertificationListFragment
                activity?.let {
                    it.supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, CertificationListFragment())
                        .addToBackStack(null)
                        .commitAllowingStateLoss()
                }
            }

            btnMedalList.setOnClickListener {
                // Show MedalListFragment
                activity?.let {
                    it.supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, MedalListFragment())
                        .addToBackStack(null)
                        .commitAllowingStateLoss()
                }
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
        // Get jwt, userId from sp
        val jwt = sSharedPreferences.getString("jwt", "")
        if (jwt != null) {
            Log.d("seori", jwt)
        }

        // Request my page info through API
        jwt?.let {
            // Jwt is in header, userId is in Path Variable
            service.getMyPageInfo("Bearer $jwt").enqueue(object : Callback<MyPageResponse> {
                    override fun onResponse(call: Call<MyPageResponse>, response: Response<MyPageResponse>) {
                        Log.d("seori000", response.toString())
                        if (response.isSuccessful) {
                            val body = response.body()
                            when (body?.code) {
                                // If success, fill the data
                                1000 -> {
                                    val result = body.result
                                    binding.run {
                                        // Set click event of edit my info button
                                        imgbtnEditMyInfo.setOnClickListener {
                                            // Show(= Start) EditInfoActivity
                                            val intent = Intent(activity, EditInfoActivity::class.java)
                                            // Pass data
                                            intent.putExtra("profileImgId", result.profileImg)
                                            intent.putExtra("myLocation", tvLocation.text)
                                            intent.putExtra("myNickname", tvNickname.text)
                                            startActivity(intent)
                                        }

                                        setDefaultProfile(imgProfile, result.profileImg)
                                        saveUserName(result.name)
                                        tvNickname.text = result.nickname
                                        tvName.text = result.name + " (${result.gender})"
                                        tvBirth.text = result.birth
                                        tvLocation.text = result.location
                                        tvBloodType.text = result.blood
                                        myPostList = result.feedList
                                        btnCertList.text = "인증된 헌혈 ${result.certificationCnt}회"
                                        btnMedalList.text = "획득한 메달 ${result.rewardCnt}개"

                                        if (myPostList.isEmpty()) {
                                            rvPostList.visibility = GONE
                                        } else {
                                            setPostRecyclerView()
                                        }
                                    }
                                }
                                // If GET fails, show toast message to user
                                else -> body?.message?.let { it1
                                    -> showCustomToast(it1)
                                    Log.d("seori111", response.message())
                                }
                            }
                        } else {
                            // If fail, show toast message to user
                            showCustomToast("네트워크 연결에 실패했습니다")
                            Log.d("seori222", response.body().toString())
                        }
                    }
                    // If fail, show toast message to user
                    override fun onFailure(call: Call<MyPageResponse>, t: Throwable) {
                        showCustomToast("네트워크 연결에 실패했습니다")
                        Log.d("seori333", "?") // 여기에 걸리다
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
                    // Show clicked post
                    parentFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_frm, FragmentPostDetail(myPostList[pos].feedId))
                        .addToBackStack(null)
                        .commitAllowingStateLoss()
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

    // Save user's name to sp
    private fun saveUserName(name: String) {
        val editor = sSharedPreferences.edit()
        editor.putString("name", name)
        editor.apply()
    }

    // Show delete account dialog
    private fun showDeleteDialog() {
        activity?.let {
            DeleteDialog()
                .show(it.supportFragmentManager, "DeleteDialog")
        }
    }
}