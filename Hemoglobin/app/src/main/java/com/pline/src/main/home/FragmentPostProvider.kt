package com.pline.src.main.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.pline.R
import com.pline.config.BaseFragment
import com.pline.data.home.FeedService
import com.pline.data.home.HomeFragmentView
import com.pline.data.home.model.FeedListResult
import com.pline.databinding.FragmentPostListProviderBinding
import com.pline.src.main.utils.PostListRVAdapter

class FragmentPostProvider: BaseFragment<FragmentPostListProviderBinding> (FragmentPostListProviderBinding::bind, R.layout.fragment_post_list_provider), HomeFragmentView {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FeedService(this).tryGetFeedList()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onGetFeedListSuccess(response: ArrayList<FeedListResult>) {
        var receiverList: ArrayList<FeedListResult> = ArrayList()

        for (i in 0.. response.size - 1){
            if (response[i].isReceiver == "F"){
                var list = response[i]
                var post = FeedListResult(list.abo, list.commentCnt, list.context, list.date, list.feedId, list.isReceiver, list.location, list.nickname, list.profileImg, list.rh, list.userId)
                receiverList.add(post)
            }
        }

        Log.d("feedListProvider", receiverList.toString())

        var postAdapter = PostListRVAdapter(receiverList)
        binding.homePostProviderListRv.adapter = postAdapter
        var mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true)
        mLayoutManager.stackFromEnd = true
        binding.homePostProviderListRv.layoutManager = mLayoutManager

        postAdapter.setOnItemClickListener(object : PostListRVAdapter.OnItemClickListener{
            override fun onPostClick(feedId: Int) {
                requireActivity().supportFragmentManager.beginTransaction().replace(R.id.main_frm, FragmentPostDetail(feedId)).addToBackStack(null).commit()
            }
        })
    }

    override fun onGetFeedListFailure(message: String) {
        Log.d("getFeedFailed", message)
    }
}