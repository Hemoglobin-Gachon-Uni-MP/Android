package com.pline.src.main.home

import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.pline.R
import com.pline.config.BaseFragment
import com.pline.databinding.FragmentHomeBinding
import com.pline.src.main.home.adapter.PostVPAdapter


class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {
    var filterVisible: Boolean = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val information = arrayListOf("수혈자", "공혈자")

//        val adapter = AddGoalVPAd(this)
//        binding.addViewpagerVp.adapter = adapter
//

        binding.homePositionViewpagerVp.adapter = PostVPAdapter(this)

        TabLayoutMediator(binding.homePositionTabTl, binding.homePositionViewpagerVp){
            tab, position->
            tab.text = information[position] // 포지션에 따른 텍스트
        }.attach() // 탭레이아웃과 뷰페이져 붙여주는 기능
    }

    override fun onStart() {
        super.onStart()


        binding.homeBtnFilterIv.setOnClickListener {
            if (filterVisible){ // 필터 보일 때
                filterBtn(false)
            }else { // 필터 접혀있을 때
                filterBtn(true)
            }
        }

        // 시작할 때 선택한 필터부분을 본인 기본 혈액형으로 하는 코드 필요
    }

    override fun onResume() {
        super.onResume()
    }

    // 필터 버튼 클릭 함수
    private fun filterBtn(isVisible: Boolean){
        if (isVisible){
            // 필터 선택창 보이게
            binding.homeFilterMenuLayoutCl.visibility = View.VISIBLE
            filterVisible = true
        } else { // 필터 선택창 안보이게
            binding.homeFilterMenuLayoutCl.visibility = View.GONE
            filterVisible = false
        }
    }

    // 필터 Rh+
    private fun rhPlus(){

    }

}