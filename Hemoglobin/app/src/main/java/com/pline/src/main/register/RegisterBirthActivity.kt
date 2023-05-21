package com.pline.src.main.register

import android.content.Intent
import android.os.Bundle
import com.pline.config.BaseActivity
import com.pline.databinding.ActivityRegisterBirthBinding

class RegisterBirthActivity : BaseActivity<ActivityRegisterBirthBinding>(ActivityRegisterBirthBinding::inflate), DatePickerDialogFragment.DatePickerListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 현재 날짜 설정
        binding.activityRegisterBirthDatePickerBtn.text = "0000.00.00"

        binding.activityRegisterBirthDatePickerBtn.setOnClickListener {
            DatePickerDialogFragment(this).show(supportFragmentManager, "datePicker")
        }
        binding.btnNext.setOnClickListener {
            startActivity(Intent(this, RegisterPhoneActivity::class.java))
        }
    }

    override fun onDateSelected(date: String) {
        binding.activityRegisterBirthDatePickerBtn.text = date
    }
}