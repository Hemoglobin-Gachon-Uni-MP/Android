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
        sSharedPreferences.edit()
            .putString("kakaoToken", "eyJraWQiOiI5ZjI1MmRhZGQ1ZjIzM2Y5M2QyZmE1MjhkMTJmZWEiLCJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJiYmZmMThiYTRmNmI4YjUwNWQyMGJiY2FhODM1ODRiMiIsInN1YiI6IjI3OTc2MjcyNTgiLCJhdXRoX3RpbWUiOjE3MDAwMzk2NTUsImlzcyI6Imh0dHBzOi8va2F1dGgua2FrYW8uY29tIiwiZXhwIjoxNzAwMDgyODU1LCJpYXQiOjE3MDAwMzk2NTV9.PHngbvtlTbSxGaravEhYnhOh3_OAYwjuYC0wRavx09fy9A5mdiIY9RjcYDV3R_4TkkfS7f08iH693meYClRFx2FU5gwez7c9FnNIPuTyQ1YnFVmmc1mTxt9dY_tnUJGK52y4xqyX58Q7DVZevbyGGrBon94qkeDVwan_I3_26K6UrtK7QEf-ZK3pEEUofJJDDSikWHyNSVRms-QGduqWjszwAh4Wm_JlT1t1C43cCj_rNd36yfX-I2t-Qy8RAFglocH6dAc3PZoDf7PdY594_76Zd6XYGSKE42uKG9ANzxCRkwUKKSPJUUaM0A6zCYL0FGUervnbp2S1Otau8DvyXA")
        val request = RegisterRequest(
            sSharedPreferences.getInt("registerTypeInfoABO", -1),
            sSharedPreferences.getString("registerBirth","")!!,
            sSharedPreferences.getString("registerTypeInfoGender","")!!,
            sSharedPreferences.getString("registerResidence","")!!,
            sSharedPreferences.getString("registerName","")!!,
            sSharedPreferences.getString("registerNickname","")!!,
            sSharedPreferences.getString("registerPhone","")!!,
            (1..2).random(),
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