package com.pline.src.main.myPage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.pline.R
import com.pline.config.BaseFragment
import com.pline.databinding.FragmentMyPageBinding
import com.pline.src.main.home.MyPostListFragment

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::bind, R.layout.fragment_my_page) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // My Post List data
        var myPostList = arrayListOf(
            MyPost("05/19", "더미 텍스트입니다.", commentNum = 3),
            MyPost("05/18", "더미 텍스트입니다.", commentNum = 3),
            MyPost("05/19", "더미 텍스트입니다.", commentNum = 3),
            MyPost("05/18", "더미 텍스트입니다.", commentNum = 3),
        )

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

            rvPostList.run {
                // Set Recycler View Adapter
                val myPostAdapter = MyPostListRVHAdapter(myPostList)
                adapter = myPostAdapter
                // Set click event of my post element in recycler view
                myPostAdapter.setOnItemClickListener(object :
                    MyPostListRVHAdapter.OnItemClickListener {
                    override fun onMyPostClick(post: MyPost, pos: Int) {
                        /// todo - 글 상세 보기 화면 띄우기
                        Log.d("Seori", "Click my post")
                    }
                })
                // Set layout of recycler view
                layoutManager =
                    LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
                addItemDecoration(MyPostListRVHDecoration(30))
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