package com.pline.src.main.myPage

import android.os.Bundle
import com.pline.config.ApplicationClass
import com.pline.config.BaseActivity
import com.pline.data.mypage.MyPageRetrofitInterface
import com.pline.data.mypage.model.MyPageEditRequest
import com.pline.data.mypage.model.MyPageEditResponse
import com.pline.databinding.ActivityEditInfoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// Edit my info page
class EditInfoActivity : BaseActivity<ActivityEditInfoBinding>(ActivityEditInfoBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val profileImgId = intent.getIntExtra("profileImgId", 1)
        val myLocation = intent.getStringExtra("myLocation").toString()
        val myNickname = intent.getStringExtra("myNickname").toString()

        binding.apply {
            setDefaultProfile(imgProfile, profileImgId)

            // Set text of edittext from my info
            etHome.setText(myLocation)
            etNickname.setText(myNickname)

            // Set click event to back button
            imgbtnBack.setOnClickListener {
                // End this activity
                finish()
            }
            // Set click event to complete button
            btnEditComplete.setOnClickListener {
                // Request to edit my info
                editMyPageInfo(etHome.text.toString(), etNickname.text.toString())
            }
        }
    }

    // Edit my info through api
    private fun editMyPageInfo(location: String, nickname: String) {
        val service = ApplicationClass.sRetrofit.create(MyPageRetrofitInterface::class.java)
        // Get jwt from sp
        val jwt = ApplicationClass.sSharedPreferences.getString("jwt", "")
        val userId = ApplicationClass.sSharedPreferences.getInt("userId", 0)
        // Configure request body
        val req = MyPageEditRequest(location, nickname)
        // Request my page info through API
        jwt?.let {
            // Jwt is in header, userId is in Path Variable, req is in request body
            service.editMyPageInfo(jwt, userId, req).enqueue(object : Callback<MyPageEditResponse> {
                override fun onResponse(call: Call<MyPageEditResponse>, response: Response<MyPageEditResponse>) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        when (body?.code) {
                            // If success, show success message to user
                            1000 -> {
                                showCustomToast("정보가 수정되었습니다")
                                // End this activity
                                finish()
                            }
                            // Handle error cases through toast message
                            2011 -> {
                                showCustomToast("닉네임을 입력해주세요")
                            }
                            2015 -> {
                                showCustomToast("거주지를 입력해주세요")
                            }
                            2020 -> {
                                showCustomToast("닉네임은 최대 8글자여야 합니다")
                            }
                            // If GET fails, show fail message to user
                            else -> body?.message?.let { it1 -> showCustomToast(it1) }
                        }
                    } else {
                        // If fail, show fail message to user
                        showCustomToast("네트워크 연결에 실패했습니다")
                    }
                }
                // If fail, show fail message to user
                override fun onFailure(call: Call<MyPageEditResponse>, t: Throwable) {
                    showCustomToast("네트워크 연결에 실패했습니다")
                }
            })
        }
    }
}
