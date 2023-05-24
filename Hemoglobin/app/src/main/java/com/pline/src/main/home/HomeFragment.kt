package com.pline.src.main.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import com.google.android.material.tabs.TabLayoutMediator
import com.pline.R
import com.pline.config.BaseFragment
import com.pline.databinding.FragmentHomeBinding
import com.pline.src.main.home.adapter.PostVPAdapter


class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {
    var filterVisible: Boolean = false
    var plusVisible: Boolean = false
    val information = arrayListOf("수혈자", "공혈자")
    var rhCnt = 3
    var aboCnt = arrayListOf<Int>(1, 1, 1, 1)
    var location = "전체 지역"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homePositionViewpagerVp.adapter = PostVPAdapter(this, aboCnt, rhCnt, location)

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
                    requireActivity().supportFragmentManager.beginTransaction().replace(R.id.main_frm, FragmentNewPostReceiver(true)).commit()
                }
                // 공혈자 글쓰기
                binding.homeNewPostForProviderTv.setOnClickListener {
                    requireActivity().supportFragmentManager.beginTransaction().replace(R.id.main_frm, FragmentNewPostReceiver(false)).commit()
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
        // rh+
        binding.homeFilterMenuPlusEmptyTv.setOnClickListener {
            rhPlus(true)
        }
        binding.homeFilterMenuPlusSelectedTv.setOnClickListener {
            rhPlus(false)
        }
        // rh-
        binding.homeFilterMenuMinusEmptyTv.setOnClickListener {
            rhMin(true)
        }
        binding.homeFilterMenuMinusSelectedTv.setOnClickListener {
            rhMin(false)
        }
        // a
        binding.homeFilterMenuAEmptyTv.setOnClickListener {
            filterA(true)
        }
        binding.homeFilterMenuASelectedTv.setOnClickListener {
            filterA(false)
        }
        // b
        binding.homeFilterMenuBEmptyTv.setOnClickListener {
            filterB(true)
        }
        binding.homeFilterMenuBSelectedTv.setOnClickListener {
            filterB(false)
        }
        // ab
        binding.homeFilterMenuCEmptyTv.setOnClickListener {
            filterC(true)
        }
        binding.homeFilterMenuCSelectedTv.setOnClickListener {
            filterC(false)
        }
        // o
        binding.homeFilterMenuOEmptyTv.setOnClickListener {
            filterO(true)
        }
        binding.homeFilterMenuOSelectedTv.setOnClickListener {
            filterO(false)
        }


        // 필터 스피너 어댑터
        val spinnerAdapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, resources.getStringArray(R.array.spinner_area_list))
        binding.homeFilterMenuAreaSpinner.adapter = spinnerAdapter
        binding.homeFilterMenuAreaSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                location = binding.homeFilterMenuAreaSpinner.selectedItem.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }


        // 적용하기
        binding.filterMenuApplyTv.setOnClickListener {
            filterBtn(false)
            binding.homePostSelectedAreaTv.text = location
            filterApply(aboCnt, rhCnt)

            binding.homePositionViewpagerVp.adapter = PostVPAdapter(this, aboCnt, rhCnt, location)

            TabLayoutMediator(binding.homePositionTabTl, binding.homePositionViewpagerVp){
                    tab, position->
                tab.text = information[position] // 포지션에 따른 텍스트
            }.attach() // 탭레이아웃과 뷰페이져 붙여주는 기능
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
            rhCnt += 1
            Log.d("home rh count", rhCnt.toString())
//            binding.homePostSelectedRhPlusTv.visibility = View.VISIBLE
        } else{ // false 들어오면 선택된상태 -> 해제된 상태
            binding.homeFilterMenuPlusSelectedTv.visibility = View.GONE
            binding.homeFilterMenuPlusEmptyTv.visibility = View.VISIBLE
            // 필터 밖에도 보여야함
            rhCnt -= 1
            Log.d("home rh count", rhCnt.toString())
//            binding.homePostSelectedRhPlusTv.visibility = View.GONE
        }
    }

    // 필터 Rh-
    private fun rhMin(isSelected: Boolean){
        if (isSelected){ // true 들어오면 해제상태 -> 선택상태
            binding.homeFilterMenuMinusSelectedTv.visibility = View.VISIBLE
            binding.homeFilterMenuMinusEmptyTv.visibility = View.GONE
            // 필터 밖에도 보여야함
            rhCnt += 2
            Log.d("home rh count", rhCnt.toString())
//            binding.homePostSelectedRhMinusTv.visibility = View.VISIBLE
        } else{ // false 들어오면 선택된상태 -> 해제된 상태
            binding.homeFilterMenuMinusSelectedTv.visibility = View.GONE
            binding.homeFilterMenuMinusEmptyTv.visibility = View.VISIBLE
            // 필터 밖에도 보여야함
            rhCnt -= 2
            Log.d("home rh count", rhCnt.toString())
//            binding.homePostSelectedRhMinusTv.visibility = View.GONE
        }
    }

    // 필터 A
    private fun filterA(isSelected: Boolean){
        if (isSelected){ // true 들어오면 해제상태 -> 선택상태
            binding.homeFilterMenuASelectedTv.visibility = View.VISIBLE
            binding.homeFilterMenuAEmptyTv.visibility = View.GONE
            // 필터 밖에도 보여야함
            aboCnt[0] = 1
            Log.d("home abo count", aboCnt.toString())
//            binding.homePostSelectedRhMinusTv.visibility = View.VISIBLE
        } else{ // false 들어오면 선택된상태 -> 해제된 상태
            binding.homeFilterMenuASelectedTv.visibility = View.GONE
            binding.homeFilterMenuAEmptyTv.visibility = View.VISIBLE
            // 필터 밖에도 보여야함
            aboCnt[0] = 0
            Log.d("home abo count", aboCnt.toString())
//            binding.homePostSelectedRhMinusTv.visibility = View.GONE
        }
    }
    private fun filterB(isSelected: Boolean){
        if (isSelected){ // true 들어오면 해제상태 -> 선택상태
            binding.homeFilterMenuBSelectedTv.visibility = View.VISIBLE
            binding.homeFilterMenuBEmptyTv.visibility = View.GONE
            // 필터 밖에도 보여야함
            aboCnt[1] = 1
            Log.d("home abo count", aboCnt.toString())
//            binding.homePostSelectedRhMinusTv.visibility = View.VISIBLE
        } else{ // false 들어오면 선택된상태 -> 해제된 상태
            binding.homeFilterMenuBSelectedTv.visibility = View.GONE
            binding.homeFilterMenuBEmptyTv.visibility = View.VISIBLE
            // 필터 밖에도 보여야함
            aboCnt[1] = 0
            Log.d("home abo count", aboCnt.toString())
//            binding.homePostSelectedRhMinusTv.visibility = View.GONE
        }
    }

    private fun filterC(isSelected: Boolean){
        if (isSelected){ // true 들어오면 해제상태 -> 선택상태
            binding.homeFilterMenuCSelectedTv.visibility = View.VISIBLE
            binding.homeFilterMenuCEmptyTv.visibility = View.GONE
            // 필터 밖에도 보여야함
            aboCnt[3] = 1
            Log.d("home abo count", aboCnt.toString())
//            binding.homePostSelectedRhMinusTv.visibility = View.VISIBLE
        } else{ // false 들어오면 선택된상태 -> 해제된 상태
            binding.homeFilterMenuCSelectedTv.visibility = View.GONE
            binding.homeFilterMenuCEmptyTv.visibility = View.VISIBLE
            // 필터 밖에도 보여야함
            aboCnt[3] = 0
            Log.d("home abo count", aboCnt.toString())
//            binding.homePostSelectedRhMinusTv.visibility = View.GONE
        }
    }

    private fun filterO(isSelected: Boolean){
        if (isSelected){ // true 들어오면 해제상태 -> 선택상태
            binding.homeFilterMenuOSelectedTv.visibility = View.VISIBLE
            binding.homeFilterMenuOEmptyTv.visibility = View.GONE
            // 필터 밖에도 보여야함
            aboCnt[2] = 1
            Log.d("home abo count", aboCnt.toString())
//            binding.homePostSelectedRhMinusTv.visibility = View.VISIBLE
        } else{ // false 들어오면 선택된상태 -> 해제된 상태
            binding.homeFilterMenuOSelectedTv.visibility = View.GONE
            binding.homeFilterMenuOEmptyTv.visibility = View.VISIBLE
            // 필터 밖에도 보여야함
            aboCnt[2] = 0
            Log.d("home abo count", aboCnt.toString())
//            binding.homePostSelectedRhMinusTv.visibility = View.GONE
        }
    }

    private fun filterApply(abo: ArrayList<Int>, rh: Int){
        when(rh){
            1 -> {
                binding.homePostSelectedRhPlusTv.visibility = View.VISIBLE
                binding.homePostSelectedRhMinusTv.visibility = View.GONE
                binding.homePostSelectedRhAllTv.visibility = View.GONE
            }
            2 -> {
                binding.homePostSelectedRhPlusTv.visibility = View.GONE
                binding.homePostSelectedRhMinusTv.visibility = View.VISIBLE
                binding.homePostSelectedRhAllTv.visibility = View.GONE
            }
            3 -> {
                binding.homePostSelectedRhPlusTv.visibility = View.GONE
                binding.homePostSelectedRhMinusTv.visibility = View.GONE
                binding.homePostSelectedRhAllTv.visibility = View.VISIBLE
            }
            else -> {
                binding.homePostSelectedRhPlusTv.visibility = View.GONE
                binding.homePostSelectedRhMinusTv.visibility = View.GONE
                binding.homePostSelectedRhAllTv.visibility = View.VISIBLE
                binding.homeBtnFilterIv.focusable = View.NOT_FOCUSABLE
                showCustomToast("Rh 필터를 한 개 이상 선택해 주세요")
                filterBtn(true)
            }
        }

        binding.homePostSelectedAboTv.visibility = View.GONE
        if (abo[0] == 0){
            binding.homePostSelectedATv.visibility = View.GONE
        } else{
            binding.homePostSelectedATv.visibility = View.VISIBLE
        }
        if (abo[1] == 0){
            binding.homePostSelectedBTv.visibility = View.GONE
        } else{
            binding.homePostSelectedBTv.visibility = View.VISIBLE
        }
        if (abo[2] == 0){
            binding.homePostSelectedOTv.visibility = View.GONE
        } else{
            binding.homePostSelectedOTv.visibility = View.VISIBLE
        }
        if (abo[3] == 0){
            binding.homePostSelectedCTv.visibility = View.GONE
        } else{
            binding.homePostSelectedCTv.visibility = View.VISIBLE
        }

        if (abo[0] == 1&&abo[1] == 1&&abo[2] == 1&&abo[3] == 1){
            binding.homePostSelectedATv.visibility = View.GONE
            binding.homePostSelectedBTv.visibility = View.GONE
            binding.homePostSelectedOTv.visibility = View.GONE
            binding.homePostSelectedCTv.visibility = View.GONE
            binding.homePostSelectedAboTv.visibility = View.VISIBLE
        }

        if (abo[0] == 0&&abo[1] == 0&&abo[2] == 0&&abo[3] == 0){
            binding.homePostSelectedAboTv.visibility = View.VISIBLE
            showCustomToast("ABO 필터를 한 개 이상 선택해 주세요")
            binding.homeBtnFilterIv.focusable = View.NOT_FOCUSABLE
            filterBtn(true)
        }
//
    }
}