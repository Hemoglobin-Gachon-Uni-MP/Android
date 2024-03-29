package com.pline.src.main.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.pline.config.ApplicationClass.Companion.sRetrofit
import com.pline.config.ApplicationClass.Companion.sSharedPreferences
import com.pline.config.BaseActivity
import com.pline.databinding.ActivityLoginBinding
import com.pline.model.LoginRequest
import com.pline.model.MemberApiInterface
import com.pline.model.LoginResponse
import com.pline.src.main.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Use kakao login sdk to get kakao access token, forward to login api and get jwt token
 */
class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // kakao login
        binding.btnLogin.setOnClickListener{
            kakaoLogin()
        }

    }
    // Get kakao access token using kakao login sdk and call login api
    private fun kakaoLogin() {
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.e("LOGIN", "Login fail with kakao account", error)
            } else if (token != null) {
                Log.i("LOGIN", "Login success with kakao account : ${token.idToken}")
                postLogin(token.idToken!!)
            }
        }
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                if (error != null) {
                    Log.e("LOGIN", "Login fail with kakaoTalk", error)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }
                    UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
                } else if (token != null) {
                    Log.i("LOGIN", "Login success with kakao account : ${token.idToken}")
                    postLogin(token.idToken!!)
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
        }
    }
    // Call login api and get jwt token
    private fun postLogin(kakaoToken : String) {
        val service = sRetrofit.create(MemberApiInterface::class.java)
        service.postLogin(LoginRequest(kakaoToken))?.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Log.d("kakaoLogin", "postLogin success : ${response}")
                if (response.isSuccessful){
                    if(response.body()?.isSuccess == true) {
                        Log.d("LoginActivity memId check", response.body()!!.result.memberId.toString())
                        sSharedPreferences.edit()
                            .putString("kakaoToken", kakaoToken)
                            .putString("jwt", response.body()!!.result.jwt)
                            .putInt("memberId",response.body()!!.result.memberId).apply()
                        Log.d("kakaoLogin", "response success")
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                        finish()
                    }
                    else if(response.body()?.code == 2028) {
                        Log.d("kakaoLogin", "response fail")
                        sSharedPreferences.edit().putString("kakaoToken", kakaoToken).apply()
                        startActivity(Intent(this@LoginActivity, RegisterNameActivity::class.java)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                        finish()
                    }
                }

            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("kakaoLogin", "postLogin fail")
            }
        })
    }
}