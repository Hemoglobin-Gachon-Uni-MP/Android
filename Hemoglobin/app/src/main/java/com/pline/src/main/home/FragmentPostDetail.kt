package com.pline.src.main.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.pline.R
import com.pline.config.ApplicationClass
import com.pline.config.BaseFragment
import com.pline.data.home.FeedDetailService
import com.pline.data.home.FeedDetailView
import com.pline.data.home.model.FeedInfoResult
import com.pline.data.home.model.PostCommentReqBody
import com.pline.data.home.model.PostNewCommentResponse
import com.pline.data.home.model.PostNewReplyResponse
import com.pline.data.home.model.PostReplyReqBody
import com.pline.data.home.model.baseUserIdReq
import com.pline.databinding.FragmentPostDetailBinding
import com.pline.src.main.home.adapter.CommentRVAdapter
import com.pline.src.main.home.dialog.CommentDeleteDialog
import com.pline.src.main.home.dialog.FeedDeleteDialog
import com.pline.src.main.home.dialog.ReplyDeleteDialog
import com.pline.src.main.home.dialog.ReportDialog

class FragmentPostDetail(val feedId: Int): BaseFragment<FragmentPostDetailBinding>(FragmentPostDetailBinding::bind, R.layout.fragment_post_detail),
    FeedDetailView {
    val myId = ApplicationClass.sSharedPreferences.getInt("userId", 0)
    var postUserId = 0
    var moreVisible = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("FragmentPostDetail", "onCreated")
    }

    override fun onResume() {
        super.onResume()

        FeedDetailService(this).tryGetFeedDetail(feedId)

        // back
        binding.postDetailBackIconIv.setOnClickListener {
            // Move to previous page
            activity?.let { it.onBackPressed() }
        }

        // feed more btn
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

        // edit feed
        binding.fragmentDetailMoreMenuEditTv.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.main_frm, FragmentPostEdit(feedId)).addToBackStack(null).commit()
        }

        // delete feed
        binding.fragmentDetailMoreMenuDeleteTv.setOnClickListener {
            deletePostDialog()
        }

        // report feed
        binding.fragmentDetailMoreMenuReportTv.setOnClickListener {
            binding.fragmentDetailMoreMenuNotMineLl.visibility = View.GONE
            reportDialog(0)
        }


        // write comment
        var comment = ""
        class MyEditWatcher: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                comment = binding.commentEnterfieldEt.text.toString()
                Log.d("onPostComment", comment)
            }
        }
        var watcher = MyEditWatcher()
        binding.commentEnterfieldEt.addTextChangedListener(watcher)
        binding.commentSendIconIv.setOnClickListener {
            val body = PostCommentReqBody(comment, myId)
            Log.d("onPostComment", "Send btn selected")
            FeedDetailService(this).tryPostNewComment(feedId, body)
            binding.commentEnterfieldEt.text.clear()
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

    fun deletePostDialog(){
        val body = baseUserIdReq(postUserId)
        val dialog = FeedDeleteDialog(body, feedId)
        dialog.isCancelable = false
        dialog.show(this.requireFragmentManager(), "DeleteFeedDialog")
    }

    fun reportDialog(type: Int){
        val dialog = ReportDialog(type)
        dialog.isCancelable = false

        dialog.setReportListner(object : ReportDialog.ToastListner{
            override fun toast() {
                showCustomToast("Reported Successfully")
            }
        })

        dialog.show(this.requireFragmentManager(), "ReportDialog")
    }

    fun deleteCommentDialog(commentId: Int){
        val body = baseUserIdReq(myId)
        val dialog = CommentDeleteDialog(body, commentId)
        dialog.isCancelable = false

        dialog.setmListner(object : CommentDeleteDialog.Listner{
            override fun reset() {
                Log.d("ondismiss", "postDetail")
                onResume()
            }
        })

        dialog.show(this.requireFragmentManager(), "DeleteCommentDialog")
    }

    fun deleteReplyDialog(replyId: Int){
        val body = baseUserIdReq(myId)
        val dialog = ReplyDeleteDialog(body, replyId)
        dialog.isCancelable = false

        dialog.setReplyListner(object : ReplyDeleteDialog.ReplyDeleteListener{
            override fun reset() {
                onResume()
            }

        })
        dialog.show(this.requireFragmentManager(), "DeleteReplyDialog")
    }

    override fun onGetFeedInfoSuccess(response: FeedInfoResult) {
        postUserId = response.userId
        binding.itemPostUserNameTv.text = response.nickname
        binding.itemPostDateTv.text = response.date
        binding.postDetailContentsTextTv.text = response.context
        binding.postDetailCommentCntTv.text = response.commentCnt.toString()
        if (response.profileImg == 1){
            binding.itemPostProfileImageIv.setImageResource(R.drawable.ic_profile_ver1)
        } else {
            binding.itemPostProfileImageIv.setImageResource(R.drawable.ic_profile_ver2)
        }

        if (response.commentList != null){
            val adapter = CommentRVAdapter(response.commentList!!)
            binding.postDetailCommentsListRv.adapter = adapter
            binding.postDetailCommentsListRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter.setMyCommentListner(object : CommentRVAdapter.MyCommentListner{
                override fun dialog(commentId: Int) {
                    deleteCommentDialog(commentId)
                }

                override fun reply(commentId: Int) {
                    binding.postDetailCommentEnterContainerLl.setBackgroundResource(R.drawable.style_enterfield_reply)
                    binding.commentEnterfieldEt.hint = "답글을 입력하세요"
                    // reply write
                    var reply = ""
                    class MyEditWatcher: TextWatcher {
                        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                        }
                        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        }
                        override fun afterTextChanged(s: Editable?) {
                            reply = binding.commentEnterfieldEt.text.toString()
                            Log.d("onPostComment", reply)
                        }
                    }
                    var watcher = MyEditWatcher()
                    binding.commentEnterfieldEt.addTextChangedListener(watcher)
                    binding.commentSendIconIv.setOnClickListener {
                        binding.postDetailCommentEnterContainerLl.setBackgroundResource(R.drawable.style_filter_unselected)
                        val body = PostReplyReqBody(reply, feedId, myId)
                        FeedDetailService(this@FragmentPostDetail).tryPostReply(commentId, body)
                        binding.commentEnterfieldEt.text.clear()
                    }

                }

                override fun replyDialog(replyId: Int) {
                    deleteReplyDialog(replyId)
                }

                override fun reportComment() {
                    reportDialog(1)
                }

                override fun reportReply() {
                    reportDialog(2)
                }
            })
        }
    }

    override fun onGetFeedInfoFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun onPostNewCommentSuccess(response: PostNewCommentResponse) {
        Log.d("onPostNewComment", "SUCCESS")
        FeedDetailService(this).tryGetFeedDetail(feedId)
    }

    override fun onPostNewCommentFailure(message: String) {
        Log.d("onPostNewCommentFailure", message)
    }

    // New Reply
    override fun onPostReplySuccess(response: PostNewReplyResponse) {
        onResume()
    }

    override fun onPostReplyFailure(message: String) {
        Log.d("onPostREPLY", "FAIL : " + message)
    }

}