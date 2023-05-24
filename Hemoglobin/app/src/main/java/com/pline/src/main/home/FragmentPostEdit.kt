package com.pline.src.main.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.graphics.toColor
import androidx.core.graphics.toColorInt
import com.pline.R
import com.pline.config.ApplicationClass
import com.pline.config.BaseFragment
import com.pline.data.home.EditPostService
import com.pline.data.home.FeedDetailService
import com.pline.data.home.FeedDetailView
import com.pline.data.home.FeedEditView
import com.pline.data.home.model.EditPostResponse
import com.pline.data.home.model.FeedInfoResult
import com.pline.data.home.model.PostCommentReqBody
import com.pline.data.home.model.PostNewCommentResponse
import com.pline.data.home.model.PostNewReplyResponse
import com.pline.databinding.FragmentPostEditBinding

class FragmentPostEdit(val feedId: Int): BaseFragment<FragmentPostEditBinding>(FragmentPostEditBinding::bind, R.layout.fragment_post_edit),
    FeedDetailView, FeedEditView {
    var content: String = ""
    val userId = ApplicationClass.sSharedPreferences.getInt("userId", 0)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FeedDetailService(this).tryGetFeedDetail(feedId)

        // 뒤로가기
        binding.postEditBackIconIv.setOnClickListener {
            activity?.let { it.onBackPressed() }
        }

    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()

        class MyEditWatcher: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                content = binding.postEditReceiverTextEnterfieldEt.text.toString()
            }
        }
        var watcher = MyEditWatcher()
        binding.postEditReceiverTextEnterfieldEt.addTextChangedListener(watcher)

//        if (content != ""){
//            binding.postEditDoneTv.setTextColor("#FFFFFF".toColorInt())
//            // 완료 버튼
//            binding.postEditDoneTv.setOnClickListener {
//                var body = PostCommentReqBody(content, userId)
//                EditPostService(this).tryEditFeed(feedId,body)
//                requireActivity().supportFragmentManager.beginTransaction().replace(R.id.main_frm, FragmentPostDetail(feedId)).commit()
//            }
//        } else{
//            binding.postEditDoneTv.setTextColor("#AEAEAE".toColorInt())
//        }

        binding.postEditDoneTv.setOnClickListener {
            if (content != ""){
                var body = PostCommentReqBody(content, userId)
                EditPostService(this).tryEditFeed(feedId,body)
                requireActivity().supportFragmentManager.beginTransaction().replace(R.id.main_frm, FragmentPostDetail(feedId)).commit()
            } else{
                showCustomToast("내용을 입력해주세요")
            }
        }

    }

    override fun onGetFeedInfoSuccess(response: FeedInfoResult) {
        Log.d("onGetFeedInfo", "SUCCESS")
        binding.itemPostUserNameTv.text = response.nickname
        binding.postEditReceiverTextEnterfieldEt.setText(response.context)
        content = response.context
        if (response.profileImg == 1){
            binding.itemEditProfileImageIv.setImageResource(R.drawable.ic_profile_ver1)
        } else {
            binding.itemEditProfileImageIv.setImageResource(R.drawable.ic_profile_ver2)
        }

        when (response.rh){
            0 -> binding.postEditSelectedRhTypeTv.text = "Rh+"
            else -> binding.postEditSelectedRhTypeTv.text = "Rh-"
        }

        when (response.abo){
            0 -> binding.postEditSelectedAboTypeTv.text = "A"
            1 -> binding.postEditSelectedAboTypeTv.text = "B"
            2 -> binding.postEditSelectedAboTypeTv.text = "O"
            else -> binding.postEditSelectedAboTypeTv.text = "AB"
        }

        binding.postEditSelectedLocationTypeTv.text = response.location

    }

    override fun onGetFeedInfoFailure(message: String) {
        Log.d("onGetFeedInfoFAILURE", message)
    }

    override fun onPostNewCommentSuccess(response: PostNewCommentResponse) {
        Log.d("onPostNewCOMMENT", "SUCCESS")
    }

    override fun onPostNewCommentFailure(message: String) {
        Log.d("onPostCOMMENT", "FAIL : " + message)
    }

    override fun onPostReplySuccess(response: PostNewReplyResponse) {
        Log.d("onPostNewREPLY", "SUCCESS")
    }

    override fun onPostReplyFailure(message: String) {
        Log.d("onPostREPLY", "FAIL : " + message)
    }

    override fun onEditFeedSuccess(response: EditPostResponse) {
        Log.d("EditPost", "SUCCESS")
    }

    override fun onEditFeedFailure(message: String) {
        Log.d("onEditFeedFailure", message)
    }
}