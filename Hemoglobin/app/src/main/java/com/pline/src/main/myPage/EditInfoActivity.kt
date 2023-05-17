package com.pline.src.main.myPage

import android.os.Bundle
import com.pline.R
import com.pline.config.BaseActivity
import com.pline.databinding.ActivityEditInfoBinding

// Edit my info page
class EditInfoActivity : BaseActivity<ActivityEditInfoBinding>(ActivityEditInfoBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            // Set click event to back button
            imgbtnBack.setOnClickListener {
                // End this activity
                finish()
            }
            // Set click event to complete button
            btnEditComplete.setOnClickListener {
                // todo - 수정 완료 API 연동

                // End this activity
                finish()
            }
        }
    }
}