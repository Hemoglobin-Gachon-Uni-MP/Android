package com.pline.src.main.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.pline.R
import com.pline.config.ApplicationClass
import com.pline.config.BaseFragment
import com.pline.data.home.FeedDetailService
import com.pline.data.home.FeedDetailView
import com.pline.data.home.model.FeedInfoResult
import com.pline.databinding.FragmentPostDetailBinding
import com.pline.src.main.home.adapter.CommentRVAdapter

class FragmentPostDetail(val feedId: Int): BaseFragment<FragmentPostDetailBinding>(FragmentPostDetailBinding::bind, R.layout.fragment_post_detail),
    FeedDetailView {
    val myId = ApplicationClass.sSharedPreferences.getInt("userId", 0)
    var postUserId = 0
    var moreVisible = false
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
            if (postUserId == myId){
                if (moreVisible){
                    moreBtnMy(false)
                }else {
                    moreBtnMy(true)
                }
            } else{
                if (moreVisible){
                    moreBtn(false)
                } else{
                    moreBtn(true)
                }
            }
        }
    }

    fun moreBtnMy(isVisible: Boolean){
        if (isVisible){
            binding.fragmentDetailMoreMenuMyPostLl.visibility = View.VISIBLE
            moreVisible = true
        }else{
            binding.fragmentDetailMoreMenuMyPostLl.visibility = View.GONE
            moreVisible = false
        }
    }

    fun moreBtn(isVisible: Boolean){
        if (isVisible){
            binding.fragmentDetailMoreMenuNotMineLl.visibility = View.VISIBLE
            moreVisible = true
        }else{
            binding.fragmentDetailMoreMenuNotMineLl.visibility = View.GONE
            moreVisible = false
        }
    }

    override fun onGetFeedInfoSuccess(response: FeedInfoResult) {
        postUserId = response.userId
        binding.itemPostUserNameTv.text = response.nickname
        binding.itemPostDateTv.text = response.date
        binding.postDetailContentsTextTv.text = response.context
        binding.postDetailCommentCntTv.text = response.commentCnt.toString()

        if (response.commentList != null){
            val adapter = CommentRVAdapter(response.commentList!!)
            binding.postDetailCommentsListRv.adapter = adapter
            binding.postDetailCommentsListRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onGetFeedInfoFailure(message: String) {
        TODO("Not yet implemented")
    }

}