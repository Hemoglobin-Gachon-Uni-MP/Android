package com.pline.src.main.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
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


    }

    override fun onResume() {
        super.onResume()


        // 필터 클릭 모음집...
        binding.homeFilterMenuPlusEmptyTv.setOnClickListener {
            rhPlus(true)
        }
        binding.homeFilterMenuPlusSelectedTv.setOnClickListener {
            rhPlus(false)
        }
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

            // 닫으면서 선택한 필터 적용해서 게시물 불러오기
        }
    }

    // 필터 Rh+
    private fun rhPlus(isSelected: Boolean){
        if (isSelected){ // true 들어오면 해제상태 -> 선택상태
            binding.homeFilterMenuPlusSelectedTv.visibility = View.VISIBLE
            binding.homeFilterMenuPlusEmptyTv.visibility = View.GONE
            // 필터 밖에도 보여야함
            binding.homePostSelectedRhPlusTv.visibility = View.VISIBLE
        } else{ // false 들어오면 선택된상태 -> 해제된 상태
            binding.homeFilterMenuPlusSelectedTv.visibility = View.GONE
            binding.homeFilterMenuPlusEmptyTv.visibility = View.VISIBLE
            // 필터 밖에도 보여야함
            binding.homePostSelectedRhPlusTv.visibility = View.GONE
        }
    }

}