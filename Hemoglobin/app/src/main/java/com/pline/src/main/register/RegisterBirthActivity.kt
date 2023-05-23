package com.pline.src.main.register

import android.content.Intent
import android.os.Bundle
import com.pline.R
import com.pline.config.ApplicationClass.Companion.sSharedPreferences
import com.pline.config.BaseActivity
import com.pline.databinding.ActivityRegisterBirthBinding

class RegisterBirthActivity : BaseActivity<ActivityRegisterBirthBinding>(ActivityRegisterBirthBinding::inflate), DatePickerDialogFragment.DatePickerListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 현재 날짜 설정
        binding.activityRegisterBirthDatePickerBtn.text = "2000.01.01"

        binding.activityRegisterBirthDatePickerBtn.setOnClickListener {
             val dialog =
                 DatePickerDialogFragment(binding.activityRegisterBirthDatePickerBtn.text as String) { date ->
                     binding.activityRegisterBirthDatePickerBtn.text = date
                     binding.btnNext.background = getDrawable(R.drawable.btn_next_active)
                     binding.btnNext.setTextColor(getColor(R.color.white))
                     binding.btnNext.isClickable = true
                }
            dialog.show(supportFragmentManager, "datePicker")
        }
        binding.btnNext.setOnClickListener {
            sSharedPreferences.edit().putString("registerBirth", binding.activityRegisterBirthDatePickerBtn.text.toString()).apply()
            startActivity(Intent(this, RegisterPhoneActivity::class.java))
        }
        binding.activityRegisterBirthBackBtn.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onDateSelected(date: String) {
        binding.activityRegisterBirthDatePickerBtn.text = date
    }
}