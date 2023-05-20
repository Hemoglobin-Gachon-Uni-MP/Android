package com.pline.src.main.home

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.google.android.material.tabs.TabLayoutMediator
import com.pline.R
import com.pline.config.BaseFragment
import com.pline.databinding.FragmentHomeBinding
import com.pline.src.main.home.adapter.PostVPAdapter


class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {
    var filterVisible: Boolean = false
    var plusVisible: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val information = arrayListOf("수혈자", "공혈자")

        binding.homePositionViewpagerVp.adapter = PostVPAdapter(this)

        TabLayoutMediator(binding.homePositionTabTl, binding.homePositionViewpagerVp){
            tab, position->
            tab.text = information[position] // 포지션에 따른 텍스트
        }.attach() // 탭레이아웃과 뷰페이져 붙여주는 기능
    }

    override fun onStart() {
        super.onStart()


        // + 버튼 클릭
        binding.homeBtnNewPostIv.setOnClickListener {
            if (plusVisible){
                plusBtn(false)

            } else { // 게시물 펼치기
                plusBtn(true)
                filterBtn(false) // 필터 열려있으면 닫기

                // 수혈자 글쓰기
                binding.homeNewPostForReceiverTv.setOnClickListener {
                    requireActivity().supportFragmentManager.beginTransaction().replace(R.id.main_frm, FragmentNewPostReceiver()).commit()
                }
                // 공혈자 글쓰기
                binding.homeNewPostForProviderTv.setOnClickListener {
                    requireActivity().supportFragmentManager.beginTransaction().replace(R.id.main_frm, FragmentNewPostProvider()).commit()
                }
            }
        }

        if (binding.homeNewPostMenuLayoutLl.isVisible){

        }


        // 필터 버튼 클릭
        binding.homeBtnFilterIv.setOnClickListener {
            if (filterVisible){ // 필터 보일 때
                filterBtn(false)
            }else { // 필터 접혀있을 때
                filterBtn(true)
                plusBtn(false)
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


        // 필터 스피너 어댑터
        val spinnerAdapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, resources.getStringArray(R.array.spinner_area_list))
        binding.homeFilterMenuAreaSpinner.adapter = spinnerAdapter
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

    // 글 추가 클릭 함수
    private fun plusBtn(isVisible: Boolean){
        if (isVisible){
            // 글쓰기창 보이게
            binding.homeNewPostMenuLayoutLl.visibility = View.VISIBLE
            plusVisible = true
        } else { // 글쓰기창 안보이게
            binding.homeNewPostMenuLayoutLl.visibility = View.GONE
            plusVisible = false

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