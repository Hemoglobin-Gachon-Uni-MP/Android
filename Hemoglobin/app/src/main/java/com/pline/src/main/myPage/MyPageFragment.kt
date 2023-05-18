package com.pline.src.main.myPage

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.pline.R
import com.pline.config.BaseFragment
import com.pline.databinding.FragmentMyPageBinding
import com.pline.src.main.home.MyPostListFragment

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

            // Set click event of my post list button
            imgbtnMyPostList.setOnClickListener {
                // Show MyPostListFragment
                activity?.let {
                    it.supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, MyPostListFragment())
                        .addToBackStack(null)
                        .commitAllowingStateLoss()
                }
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