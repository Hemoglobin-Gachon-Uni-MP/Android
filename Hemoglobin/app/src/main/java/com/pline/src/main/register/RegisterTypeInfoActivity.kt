package com.pline.src.main.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.pline.R
import com.pline.config.ApplicationClass.Companion.sSharedPreferences
import com.pline.config.BaseActivity
import com.pline.databinding.ActivityRegisterTypeInfoBinding

/**
 * Get gender, blood type information
 */
class RegisterTypeInfoActivity : BaseActivity<ActivityRegisterTypeInfoBinding>(
    ActivityRegisterTypeInfoBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnNext.isClickable = false
        binding.activityRegisterTypeInfoCbMale.setOnClickListener {
            binding.activityRegisterTypeInfoCbMale.isChecked = true
            binding.activityRegisterTypeInfoCbFemale.isChecked = false
            binding.btnNext.background = getDrawable(R.drawable.btn_next_active)
            binding.btnNext.setTextColor(getColor(R.color.white))
            binding.btnNext.isEnabled = true
        }
        binding.activityRegisterTypeInfoCbFemale.setOnClickListener {
            binding.activityRegisterTypeInfoCbFemale.isChecked = true
            binding.activityRegisterTypeInfoCbMale.isChecked = false
            binding.btnNext.background = getDrawable(R.drawable.btn_next_active)
            binding.btnNext.setTextColor(getColor(R.color.white))
            binding.btnNext.isEnabled = true
        }
        val abo : ArrayList<String> = arrayListOf("A", "B", "O", "AB")
        val rh : ArrayList<String> = arrayListOf("Rh+", "Rh-")
        setSpinner(binding.activityRegisterTypeInfoBloodTypeSpinner, abo)
        setSpinner(binding.activityRegisterTypeInfoBloodTypeRhSpinner, rh)

        binding.btnNext.setOnClickListener {
            Log.d("registerTypeInfo",
                "${binding.activityRegisterTypeInfoCbMale.isChecked}")
            if(binding.activityRegisterTypeInfoCbMale.isChecked) {
                sSharedPreferences.edit().putString("registerTypeInfoGender", "M").apply()
            } else {
                sSharedPreferences.edit().putString("registerTypeInfoGender", "F").apply()
            }
            when (binding.activityRegisterTypeInfoBloodTypeSpinner.selectedItem) {
                "A" -> sSharedPreferences.edit().putInt("registerTypeInfoABO", 0).apply()
                "B" -> sSharedPreferences.edit().putInt("registerTypeInfoABO", 1).apply()
                "O" -> sSharedPreferences.edit().putInt("registerTypeInfoABO", 2).apply()
                "AB" -> sSharedPreferences.edit().putInt("registerTypeInfoABO", 3).apply()
            }
            when (binding.activityRegisterTypeInfoBloodTypeRhSpinner.selectedItem) {
                "Rh+" -> sSharedPreferences.edit().putInt("registerTypeInfoRH", 0).apply()
                "Rh-" -> sSharedPreferences.edit().putInt("registerTypeInfoRH", 1).apply()
            }
            startActivity(Intent(this, RegisterResidenceActivity::class.java))
        }
        binding.activityRegisterTypeInfoBackBtn.setOnClickListener {
            onBackPressed()
        }
    }
    private fun setSpinner(spinner: Spinner, arr : ArrayList<String>) {

        // Creating adapter for spinner
        val dataAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, arr)

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // attaching data adapter to spinner
        spinner.adapter = dataAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
            }

            override fun onNothingSelected(arg0: AdapterView<*>?) {}
        }
    }
}