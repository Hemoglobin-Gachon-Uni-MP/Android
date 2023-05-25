package com.pline.src.main.register

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.pline.R
import com.pline.config.ApplicationClass
import com.pline.config.ApplicationClass.Companion.sSharedPreferences
import com.pline.config.BaseActivity
import com.pline.databinding.ActivityRegisterNicknameBinding
/**
 * Get nickname information
 */
class RegisterNicknameActivity : BaseActivity<ActivityRegisterNicknameBinding>(ActivityRegisterNicknameBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activityRegisterNicknameEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                if(s.toString() == "") {
                    binding.btnNext.background = getDrawable(R.drawable.btn_next_deactive)
                    binding.btnNext.setTextColor(getColor(R.color.red6))
                    binding.btnNext.isEnabled = false
                } else {
                    binding.btnNext.background = getDrawable(R.drawable.btn_next_active)
                    binding.btnNext.setTextColor(getColor(R.color.white))
                    binding.btnNext.isEnabled = true
                }
            }
        })
        binding.activityRegisterNicknameBackBtn.setOnClickListener {
            onBackPressed()
        }
        binding.btnNext.setOnClickListener {
            sSharedPreferences.edit()
                .putString("registerNickname", binding.activityRegisterNicknameEt.text.toString()).apply()
            startActivity(Intent(this, RegisterBirthActivity::class.java))
        }
    }
}