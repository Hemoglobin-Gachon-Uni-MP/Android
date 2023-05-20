package com.pline.src.main.register

import android.content.Intent
import android.os.Bundle
import com.pline.config.BaseActivity
import com.pline.databinding.ActivityRegisterPhoneBinding

class RegisterPhoneActivity : BaseActivity<ActivityRegisterPhoneBinding>(ActivityRegisterPhoneBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnNext.setOnClickListener {
            startActivity(Intent(this, RegisterTypeInfoActivity::class.java))
        }
    }
}