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
            tab.text = information[position] // Text according to position
        }.attach() // connect TabLayout and ViewPager
    }

    override fun onStart() {
        super.onStart()

        // click new feed btn
        binding.homeBtnNewPostIv.setOnClickListener {
            if (plusVisible){
                plusBtn(false)

            } else { // open new feed btn
                plusBtn(true)
                filterBtn(false) // close

                // receiver new feed
                binding.homeNewPostForReceiverTv.setOnClickListener {
                    requireActivity().supportFragmentManager.beginTransaction().replace(R.id.main_frm, FragmentNewPostReceiver(true)).commit()
                }
                // provider new feed
                binding.homeNewPostForProviderTv.setOnClickListener {
                    requireActivity().supportFragmentManager.beginTransaction().replace(R.id.main_frm, FragmentNewPostReceiver(false)).commit()
                }
            }
        }

        if (binding.homeNewPostMenuLayoutLl.isVisible){

        }


        // filter btn click
        binding.homeBtnFilterIv.setOnClickListener {
            if (filterVisible){ //filter layout open
                filterBtn(false)
            }else { // filter layout close
                filterBtn(true)
                plusBtn(false)
            }
        }

    }

    override fun onResume() {
        super.onResume()


        // filter select listners
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


        // filter area spinner adapter
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
                tab.text = information[position] // Text according to position
            }.attach() // connect TabLayout and ViewPager
        }
    }


    // filter button click function
    private fun filterBtn(isVisible: Boolean){
        if (isVisible){
            // show filters
            binding.homeFilterMenuLayoutCl.visibility = View.VISIBLE
            filterVisible = true
        } else { // close filter
            binding.homeFilterMenuLayoutCl.visibility = View.GONE
            filterVisible = false
        }
    }

    // new feed function
    private fun plusBtn(isVisible: Boolean){
        if (isVisible){
            // show new feed
            binding.homeNewPostMenuLayoutLl.visibility = View.VISIBLE
            plusVisible = true
        } else { // close new feed
            binding.homeNewPostMenuLayoutLl.visibility = View.GONE
            plusVisible = false

        }
    }


    //  Rh+
    private fun rhPlus(isSelected: Boolean){
        if (isSelected){ // true deactivate -> activate
            binding.homeFilterMenuPlusSelectedTv.visibility = View.VISIBLE
            binding.homeFilterMenuPlusEmptyTv.visibility = View.GONE
            rhCnt += 1
            Log.d("home rh count", rhCnt.toString())
        } else{ // false activate -> deactivate
            binding.homeFilterMenuPlusSelectedTv.visibility = View.GONE
            binding.homeFilterMenuPlusEmptyTv.visibility = View.VISIBLE
            rhCnt -= 1
            Log.d("home rh count", rhCnt.toString())
        }
    }

    //  Rh-
    private fun rhMin(isSelected: Boolean){
        if (isSelected){ // true deactivate -> activate
            binding.homeFilterMenuMinusSelectedTv.visibility = View.VISIBLE
            binding.homeFilterMenuMinusEmptyTv.visibility = View.GONE
            rhCnt += 2
            Log.d("home rh count", rhCnt.toString())
        } else{ // false activate -> deactivate
            binding.homeFilterMenuMinusSelectedTv.visibility = View.GONE
            binding.homeFilterMenuMinusEmptyTv.visibility = View.VISIBLE
            rhCnt -= 2
            Log.d("home rh count", rhCnt.toString())
        }
    }

    //  A
    private fun filterA(isSelected: Boolean){
        if (isSelected){ // true deactivate -> activate
            binding.homeFilterMenuASelectedTv.visibility = View.VISIBLE
            binding.homeFilterMenuAEmptyTv.visibility = View.GONE
            aboCnt[0] = 1
            Log.d("home abo count", aboCnt.toString())
        } else{ // false activate -> deactivate
            binding.homeFilterMenuASelectedTv.visibility = View.GONE
            binding.homeFilterMenuAEmptyTv.visibility = View.VISIBLE
            aboCnt[0] = 0
            Log.d("home abo count", aboCnt.toString())
        }
    }
    private fun filterB(isSelected: Boolean){ // b
        if (isSelected){ // true deactivate -> activate
            binding.homeFilterMenuBSelectedTv.visibility = View.VISIBLE
            binding.homeFilterMenuBEmptyTv.visibility = View.GONE
            aboCnt[1] = 1
            Log.d("home abo count", aboCnt.toString())
        } else{ // false activate -> deactivate
            binding.homeFilterMenuBSelectedTv.visibility = View.GONE
            binding.homeFilterMenuBEmptyTv.visibility = View.VISIBLE
            aboCnt[1] = 0
            Log.d("home abo count", aboCnt.toString())
        }
    }

    private fun filterC(isSelected: Boolean){ // ab
        if (isSelected){ // true deactivate -> activate
            binding.homeFilterMenuCSelectedTv.visibility = View.VISIBLE
            binding.homeFilterMenuCEmptyTv.visibility = View.GONE
            aboCnt[3] = 1
            Log.d("home abo count", aboCnt.toString())
        } else{ // false activate -> deactivate
            binding.homeFilterMenuCSelectedTv.visibility = View.GONE
            binding.homeFilterMenuCEmptyTv.visibility = View.VISIBLE
            aboCnt[3] = 0
            Log.d("home abo count", aboCnt.toString())
        }
    }

    private fun filterO(isSelected: Boolean){
        if (isSelected){ // true deactivate -> activate
            binding.homeFilterMenuOSelectedTv.visibility = View.VISIBLE
            binding.homeFilterMenuOEmptyTv.visibility = View.GONE
            aboCnt[2] = 1
            Log.d("home abo count", aboCnt.toString())
//            binding.homePostSelectedRhMinusTv.visibility = View.VISIBLE
        } else{ // false activate -> deactivate
            binding.homeFilterMenuOSelectedTv.visibility = View.GONE
            binding.homeFilterMenuOEmptyTv.visibility = View.VISIBLE
            aboCnt[2] = 0
            Log.d("home abo count", aboCnt.toString())
//            binding.homePostSelectedRhMinusTv.visibility = View.GONE
        }
    }

    // apply the filter
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