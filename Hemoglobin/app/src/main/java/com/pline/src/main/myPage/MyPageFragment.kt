package com.pline.src.main.myPage

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.pline.R
import com.pline.config.BaseFragment
import com.pline.databinding.FragmentMyPageBinding

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::bind, R.layout.fragment_my_page) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            // Set click event of edit my info button
            imgbtnEditMyInfo.setOnClickListener {
                /// todo - 데이터 전달
                // Show(= Start) EditInfoActivity
                val intent = Intent(activity, EditInfoActivity::class.java)
                startActivity(intent)
            }

            // Set click event of delete account button
            imgbtnDeleteAccount.setOnClickListener {
                // Show dialog for deleting account
                showDeleteDialog()
            }
        }
    }

    private fun showDeleteDialog() {
        activity?.let {
            DeleteDialog()
                .show(it.supportFragmentManager, "DeleteDialog")
        }
    }
}