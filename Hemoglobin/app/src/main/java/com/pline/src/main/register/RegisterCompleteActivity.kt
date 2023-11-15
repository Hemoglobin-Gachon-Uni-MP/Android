package com.pline.src.main.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.pline.config.ApplicationClass
import com.pline.config.ApplicationClass.Companion.sSharedPreferences
import com.pline.config.BaseActivity
import com.pline.databinding.ActivityRegisterCompleteBinding
import com.pline.model.MemberApiInterface
import com.pline.model.RegisterRequest
import com.pline.model.RegisterResponse
import com.pline.src.main.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Call the membership API with the membership register information stored in the sharedPreference
 */
class RegisterCompleteActivity : BaseActivity<ActivityRegisterCompleteBinding>(
    ActivityRegisterCompleteBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val request = RegisterRequest(
            sSharedPreferences.getInt("registerTypeInfoABO", -1),
            sSharedPreferences.getString("registerBirth","")!!,
            sSharedPreferences.getString("registerTypeInfoGender","")!!,
            sSharedPreferences.getString("registerResidence","")!!,
            sSharedPreferences.getString("registerName","")!!,
            sSharedPreferences.getString("registerNickname","")!!,
            sSharedPreferences.getString("registerPhone","")!!,
            sSharedPreferences.getInt("registerTypeInfoRH",-1),
            sSharedPreferences.getString("kakaoToken", "")!!
        )
        Log.d("registerComplete", "$request")
        binding.btnNext.setOnClickListener {
            postRegister(request)
        }
    }
    private fun postRegister(registerRequest : RegisterRequest) {
        val service = ApplicationClass.sRetrofit.create(MemberApiInterface::class.java)
        service.postRegister(registerRequest)
            ?.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                Log.d("registerComplete", "postRegister success")
                if (response.isSuccessful){
                    if(response.body()?.isSuccess == true) {
                        Log.d("registerComplete", "registerComplete response success")
                        sSharedPreferences.edit()
                            .putString("jwt", response.body()!!.result.jwt)
                            .putInt("memberId",response.body()!!.result.userId).apply()
                        startActivity(
                            Intent(this@RegisterCompleteActivity, MainActivity::class.java)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                        finishAffinity()
                    }
                    else {
                        Log.d("registerComplete", "registerComplete response fail")
                        Toast.makeText(this@RegisterCompleteActivity, "${response?.body()?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Log.d("registerComplete", "registerComplete fail")
            }
        })
    }
}