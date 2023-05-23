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
import com.pline.model.ApiInterface
import com.pline.model.LoginResponse
import com.pline.src.main.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        /*
//        카카오 해시 키 받기
//        */
//
//        var keyHash = Utility.getKeyHash(this)
//        Log.d("Hash", keyHash)

        // 카카오 로그인
        binding.btnLogin.setOnClickListener{
            kakaoLogin()
        }

    }
    private fun kakaoLogin() {
        // 카카오계정으로 로그인 공통 callback 구성
        // 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.e("LOGIN", "카카오계정으로 로그인 실패", error)
            } else if (token != null) {
                Log.i("LOGIN", "카카오계정으로 로그인 성공")
                postLogin(token.accessToken)
            }
        }
        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                if (error != null) {
                    Log.e("LOGIN", "카카오톡으로 로그인 실패", error)

                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }

                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
                } else if (token != null) {
                    Log.i("LOGIN", "카카오톡으로 로그인 성공")
                    postLogin(token.accessToken)
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
        }
    }

    private fun postLogin(kToken : String) {
        val service = sRetrofit.create(ApiInterface::class.java)
        service.postLogIn(kToken)?.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Log.d("kakaoLogin", "postLogin success")
                if (response.isSuccessful){
                    if(response.body()?.isSuccess == true) {
                        sSharedPreferences.edit()
                            .putString("kToken", kToken)
                            .putString("jwt", response.body()!!.result.jwt)
                            .putInt("userId",response.body()!!.result.userId).apply()
                        Log.d("kakaoLogin", "response success")
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                        finish()
                    }
                    else if(response.body()?.code == 2028) {
                        Log.d("kakaoLogin", "response fail")
                        sSharedPreferences.edit().putString("kToken", kToken).apply()
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