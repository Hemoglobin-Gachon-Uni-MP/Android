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

class FragmentPostProvider(val abo: ArrayList<Int>, val rh: Int, val location: String): BaseFragment<FragmentPostListProviderBinding> (FragmentPostListProviderBinding::bind, R.layout.fragment_post_list_provider), HomeFragmentView {
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
        var locationList: ArrayList<FeedListResult> = ArrayList()

        for (i in 0.. response.size - 1){
            if (location != "전체 지역"){
                if (response[i].location == location){
                    locationList.add(response[i])
                }
            } else{
                locationList.add(response[i])
            }
        }

        for (i in 0..locationList.size -1){
            if ((rh == 1 || rh == 3) && abo[0] == 1){
                if (locationList[i].isReceiver == false && locationList[i].rh == 0 && locationList[i].abo == 0){
                    receiverList.add(locationList[i])
                }
            }
            if ((rh == 1 || rh == 3) && abo[1] == 1){
                if (locationList[i].isReceiver == false && locationList[i].rh == 0 && locationList[i].abo == 1){
                    receiverList.add(locationList[i])
                }
            }
            if ((rh == 1 || rh == 3) && abo[2] == 1){
                if (locationList[i].isReceiver == false && locationList[i].rh == 0 && locationList[i].abo == 2){
                    receiverList.add(locationList[i])
                }
            }
            if ((rh == 1 || rh == 3) && abo[3] == 1){
                if (locationList[i].isReceiver == false && locationList[i].rh == 0 && locationList[i].abo == 3){
                    receiverList.add(locationList[i])
                }
            }
            if ((rh == 2 || rh == 3) && abo[0] == 1){
                if (locationList[i].isReceiver == false && locationList[i].rh == 1 && locationList[i].abo == 0){
                    receiverList.add(locationList[i])
                }
            }
            if ((rh == 2 || rh == 3) && abo[1] == 1){
                if (locationList[i].isReceiver == false && locationList[i].rh == 1 && locationList[i].abo == 1){
                    receiverList.add(locationList[i])
                }
            }
            if ((rh == 2 || rh == 3) && abo[2] == 1){
                if (locationList[i].isReceiver == false && locationList[i].rh == 1 && locationList[i].abo == 2){
                    receiverList.add(locationList[i])
                }
            }
            if ((rh == 2 || rh == 3) && abo[3] == 1){
                if (locationList[i].isReceiver == false && locationList[i].rh == 1 && locationList[i].abo == 3){
                    receiverList.add(locationList[i])
                }
            }
        }

        Log.d("feedListProvider", receiverList.toString())

        var postAdapter = PostListRVAdapter(receiverList)
        binding.homePostProviderListRv.adapter = postAdapter
        var mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
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