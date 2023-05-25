package com.pline.src.main.register

import android.content.Intent
import android.os.Bundle
import com.pline.R
import com.pline.config.ApplicationClass.Companion.sSharedPreferences
import com.pline.config.BaseActivity
import com.pline.databinding.ActivityRegisterBirthBinding

/**
 * Get birth information
 */
class RegisterBirthActivity : BaseActivity<ActivityRegisterBirthBinding>(ActivityRegisterBirthBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set default date
        binding.activityRegisterBirthDatePickerBtn.text = "2000.01.01"

        // Call DatePickerDialogFragment and get birth information
        binding.activityRegisterBirthDatePickerBtn.setOnClickListener {
             val dialog =
                 DatePickerDialogFragment(binding.activityRegisterBirthDatePickerBtn.text as String) { date ->
                     binding.activityRegisterBirthDatePickerBtn.text = date
                }
            dialog.show(supportFragmentManager, "datePicker")
        }
        // Go next activity
        binding.btnNext.setOnClickListener {
            sSharedPreferences.edit().putString("registerBirth", binding.activityRegisterBirthDatePickerBtn.text.toString()).apply()
            startActivity(Intent(this, RegisterPhoneActivity::class.java))
        }
        // Go before activity
        binding.activityRegisterBirthBackBtn.setOnClickListener {
            onBackPressed()
        }
    }
}