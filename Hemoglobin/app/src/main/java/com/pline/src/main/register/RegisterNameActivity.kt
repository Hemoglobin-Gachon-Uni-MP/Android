package com.pline.src.main.register

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.pline.R
import com.pline.config.ApplicationClass.Companion.sSharedPreferences
import com.pline.config.BaseActivity
import com.pline.databinding.ActivityRegisterNameBinding

class RegisterNameActivity : BaseActivity<ActivityRegisterNameBinding>(ActivityRegisterNameBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.activityRegisterNameEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                if(s.toString() == "") {
                    binding.btnNext.background = getDrawable(R.drawable.btn_next_deactive)
                    binding.btnNext.setTextColor(getColor(R.color.red6))
                    binding.btnNext.isClickable = false
                } else {
                    binding.btnNext.background = getDrawable(R.drawable.btn_next_active)
                    binding.btnNext.setTextColor(getColor(R.color.white))
                    binding.btnNext.isClickable = true
                }
            }

        })
        binding.btnNext.setOnClickListener {
            sSharedPreferences.edit()
                .putString("registerName", binding.activityRegisterNameEt.text.toString())
            startActivity(Intent(this, RegisterNicknameActivity::class.java))
        }
    }
}