package com.pline.src.main.register

import android.content.Intent
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.Editable
import android.text.TextWatcher
import com.pline.R
import com.pline.config.ApplicationClass
import com.pline.config.BaseActivity
import com.pline.databinding.ActivityRegisterPhoneBinding

class RegisterPhoneActivity : BaseActivity<ActivityRegisterPhoneBinding>(ActivityRegisterPhoneBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.activityRegisterPhoneEt.addTextChangedListener(object : TextWatcher {
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
        binding.activityRegisterPhoneEt.addTextChangedListener(PhoneNumberFormattingTextWatcher())
        binding.btnNext.setOnClickListener {
            ApplicationClass.sSharedPreferences.edit()
                .putString("registerPhone", binding.activityRegisterPhoneEt.text.toString()).apply()
            startActivity(Intent(this, RegisterTypeInfoActivity::class.java))
        }
    }
}