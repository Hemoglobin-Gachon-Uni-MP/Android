package com.pline.src.main.myPage

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.pline.R
import com.pline.config.BaseFragment
import com.pline.databinding.FragmentCertificationListBinding

// Certification List
class CertificationListFragment() : BaseFragment<FragmentCertificationListBinding>(FragmentCertificationListBinding::bind, R.layout.fragment_certification_list) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvCertList.run {
//            var feedList: ArrayList<FeedListResult> = arrayListOf()
//            myPostList.forEach {
//                feedList.add(FeedListResult(null, it.commentCnt, it.context, it.date, it.feedId, it.isReceiver, null, it.nickname, it.profileImg, null, it.userId))
//            }
//
//            // Set Recycler View Adapter
//            val postAdapter = PostListRVAdapter(feedList)
//            adapter = postAdapter
//            // Set click event of my post element in recycler view
//            postAdapter.setOnItemClickListener(object: PostListRVAdapter.OnItemClickListener {
//                override fun onPostClick(feedId:Int) {
//                    // Show clicked post
//                    parentFragmentManager
//                        .beginTransaction()
//                        .replace(R.id.main_frm, FragmentPostDetail(feedId))
//                        .addToBackStack(null)
//                        .commitAllowingStateLoss()
//                }
//            })
//            // Set layout of recycler view
//            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        }
    }
}