package com.pline.src.main.register

import android.content.Intent
import android.os.Bundle
import com.pline.config.BaseActivity
import com.pline.databinding.ActivityRegisterNicknameBinding

class RegisterNicknameActivity : BaseActivity<ActivityRegisterNicknameBinding>(ActivityRegisterNicknameBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnNext.setOnClickListener {
            startActivity(Intent(this, RegisterBirthActivity::class.java))
        }
    }
}