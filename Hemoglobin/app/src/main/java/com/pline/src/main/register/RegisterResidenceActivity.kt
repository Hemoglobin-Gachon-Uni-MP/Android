package com.pline.src.main.register

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.pline.config.ApplicationClass
import com.pline.config.BaseActivity
import com.pline.databinding.ActivityRegisterResidenceBinding

class RegisterResidenceActivity :
    BaseActivity<ActivityRegisterResidenceBinding>(ActivityRegisterResidenceBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val list: ArrayList<String> = arrayListOf(
            "강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "구로구",
            "금천구", "노원구", "도봉구", "동대문구", "동작구", "마포구", "서대문구", "서초구", "성동구",
            "성북구", "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구"
        )
        setSpinner(binding.activityRegisterResidenceSpinner, list)

        binding.btnNext.setOnClickListener {
            ApplicationClass.sSharedPreferences.edit()
                .putString(
                    "registerResidence",
                    "서울시 ${binding.activityRegisterResidenceSpinner.selectedItem}"
                ).apply()
            startActivity(Intent(this, RegisterCompleteActivity::class.java))
        }
        binding.activityRegisterResidenceBackBtn.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setSpinner(spinner: Spinner, arr: ArrayList<String>) {

        // Creating adapter for spinner
        val dataAdapter = ArrayAdapter(this, R.layout.simple_spinner_item, arr)

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