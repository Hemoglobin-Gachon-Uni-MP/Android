package com.pline.src.main.home

import android.os.Bundle
import android.util.Log
import android.view.View
import com.pline.R
import com.pline.config.BaseFragment
import com.pline.data.home.FeedDetailService
import com.pline.data.home.FeedDetailView
import com.pline.data.home.model.FeedInfoResult
import com.pline.databinding.FragmentPostDetailBinding

class FragmentPostDetail(val feedId: Int): BaseFragment<FragmentPostDetailBinding>(FragmentPostDetailBinding::bind, R.layout.fragment_post_detail),
    FeedDetailView {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("FragmentPostDetail", "onCreated")

        FeedDetailService(this).tryGetFeedDetail(feedId)

    }

    override fun onResume() {
        super.onResume()

        // 뒤로가기
        binding.postDetailBackIconIv.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.main_frm, HomeFragment()).commit()
        }

        // 더보기 버튼
        binding.postDetailMoreIconIv.setOnClickListener {

        }
    }

    override fun onGetFeedInfoSuccess(response: FeedInfoResult) {
        TODO("Not yet implemented")
    }

    override fun onGetFeedInfoFailure(message: String) {
        TODO("Not yet implemented")
    }

}