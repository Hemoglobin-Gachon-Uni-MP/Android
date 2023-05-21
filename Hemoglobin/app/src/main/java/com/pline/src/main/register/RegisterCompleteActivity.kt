package com.pline.src.main.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.pline.config.ApplicationClass
import com.pline.config.ApplicationClass.Companion.sSharedPreferences
import com.pline.config.BaseActivity
import com.pline.databinding.ActivityRegisterCompleteBinding
import com.pline.model.ApiInterface
import com.pline.model.LoginResponse
import com.pline.model.RegisterRequest
import com.pline.src.main.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterCompleteActivity : BaseActivity<ActivityRegisterCompleteBinding>(
    ActivityRegisterCompleteBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    /*private fun postRegister(registerRequest : RegisterRequest) {
        val service = ApplicationClass.sRetrofit.create(ApiInterface::class.java)
        service.postRegister(kToken, registerRequest)?.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Log.d("kakaoLogin", "postLogin success")
                if (response.isSuccessful){
                    if(response.body()?.isSuccess == true) {
                        Log.d("kakaoLogin", "response success")
                        startActivity(
                            Intent(this@LoginActivity, MainActivity::class.java)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                        finish()
                    }
                    else if(response.body()?.code == 2028) {
                        Log.d("kakaoLogin", "response fail")
                        startActivity(
                            Intent(this@LoginActivity, RegisterNameActivity::class.java)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                        finish()
                    }
                }
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("kakaoLogin", "postLogin fail")
            }
        })
    }*/
}