package com.pline.src.main.home

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.pline.R
import com.pline.config.ApplicationClass
import com.pline.config.BaseFragment
import com.pline.data.home.CreateFeedFragmentView
import com.pline.data.home.NewFeedService
import com.pline.data.home.model.FeedsResponse
import com.pline.data.home.model.postFeedReqBody
import com.pline.databinding.FragmentNewPostReceiverBinding

class FragmentNewPostReceiver(val isReceiver: Boolean): BaseFragment<FragmentNewPostReceiverBinding> (FragmentNewPostReceiverBinding::bind, R.layout.fragment_new_post_receiver), CreateFeedFragmentView {
    data class data(
        var con: String,
        var rh: Int,
        var abo: Int,
        var loc: String
    )
    var postData = data("", -1, -1, "")

    val userId = ApplicationClass.sSharedPreferences.getInt("userId", 0)
    var body = postFeedReqBody(-1, "", "", "", -1, userId)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!isReceiver){ // 공혈자일때
            binding.newPostReceiverToolbarTv.setText("공혈자 새 게시물")
            binding.newPostReceiverBloodTypeTextTv.setText("공혈자의 혈액형을 입력해주세요")
            binding.newPostReceiverTextAreaTv.setText("거주지역")
            body.isReceiver = "F"
        }else body.isReceiver = "T"
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onResume() {
        super.onResume()
        // 필터 스피너 어댑터
        val spinnerAdapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, resources.getStringArray(R.array.spinner_area_list))
        binding.newPostReceiverAreaSpinner.adapter = spinnerAdapter

        // page close
        binding.newPostReceiverCloseIconIv.setOnClickListener {
            toHomeFragment()
        }


        // 본문 입력 감지
        class MyEditWatcher: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                postData.con = binding.newPostReceiverTextEnterfieldEt.text.toString()
                done()
            }
        }
        var watcher = MyEditWatcher()
        binding.newPostReceiverTextEnterfieldEt.addTextChangedListener(watcher)


        // rh 버튼 선택
        binding.newPostReceiverPlusEmptyTv.setOnClickListener {
            rhPlus(true)
        }
        binding.newPostReceiverPlusSelectedTv.setOnClickListener {
            rhPlus(false)
        }
        binding.newPostReceiverMinusEmptyTv.setOnClickListener {
            rhMinus(true)
        }
        binding.newPostReceiverMinusSelectedTv.setOnClickListener {
            rhMinus(false)
        }

        // abo 버튼 선택
        binding.newPostReceiverAEmptyTv.setOnClickListener { aboA(true) }
        binding.newPostReceiverASelectedTv.setOnClickListener { aboA(false) }
        binding.newPostReceiverBEmptyTv.setOnClickListener { aboB(true) }
        binding.newPostReceiverBSelectedTv.setOnClickListener { aboB(false) }
        binding.newPostReceiverAbEmptyTv.setOnClickListener { aboAB(true) }
        binding.newPostReceiverAbSelectedTv.setOnClickListener { aboAB(false) }
        binding.newPostReceiverOEmptyTv.setOnClickListener { aboO(true) }
        binding.newPostReceiverOSelectedTv.setOnClickListener { aboO(false) }

        // 지역 스피너 아이템
        binding.newPostReceiverAreaSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                spinner(binding.newPostReceiverAreaSpinner.selectedItem.toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        // 완료 버튼 클릭
        binding.newPostReceiverDoneBtnActivateTv.setOnClickListener {
            NewFeedService(this).tryPostNewFeed(body)
            toHomeFragment()
        }
    }

    fun rhPlus(isSelected: Boolean){
        if (isSelected){ // 선택하기
            binding.newPostReceiverPlusSelectedTv.visibility = View.VISIBLE
            binding.newPostReceiverPlusEmptyTv.visibility = View.GONE
            // 둘중 하나만 클릭
            rhMinus(false)
            postData.rh = 0
        }else{
            binding.newPostReceiverPlusSelectedTv.visibility = View.GONE
            binding.newPostReceiverPlusEmptyTv.visibility = View.VISIBLE
            postData.rh = -1
        }
        done()
    }

    fun rhMinus(isSelected: Boolean){
        if (isSelected){ // 선택하기
            binding.newPostReceiverMinusSelectedTv.visibility = View.VISIBLE
            binding.newPostReceiverMinusEmptyTv.visibility = View.GONE
            // 하나만 선택
            rhPlus(false)
            postData.rh = 1
        } else{// 선택해제
            binding.newPostReceiverMinusSelectedTv.visibility = View.GONE
            binding.newPostReceiverMinusEmptyTv.visibility = View.VISIBLE
            postData.rh = -1
        }
        done()
    }

    fun aboA(isSelected: Boolean){
        if (isSelected){
            binding.newPostReceiverASelectedTv.visibility = View.VISIBLE
            binding.newPostReceiverAEmptyTv.visibility = View.GONE
            // 하나만 선택
            aboB(false)
            aboO(false)
            aboAB(false)
            postData.abo = 0
        } else{
            binding.newPostReceiverASelectedTv.visibility = View.GONE
            binding.newPostReceiverAEmptyTv.visibility = View.VISIBLE
            postData.abo = -1
        }
        done()
    }

    fun aboB(isSelected: Boolean){
        if (isSelected){
            binding.newPostReceiverBSelectedTv.visibility = View.VISIBLE
            binding.newPostReceiverBEmptyTv.visibility = View.GONE
            // 하나만 선택
            aboA(false)
            aboO(false)
            aboAB(false)
            postData.abo = 1
        } else{
            binding.newPostReceiverBSelectedTv.visibility = View.GONE
            binding.newPostReceiverBEmptyTv.visibility = View.VISIBLE
            postData.abo = -1
        }
        done()
    }

    fun aboO(isSelected: Boolean){
        if (isSelected){
            binding.newPostReceiverOSelectedTv.visibility = View.VISIBLE
            binding.newPostReceiverOEmptyTv.visibility = View.GONE
            // 하나만 선택
            aboA(false)
            aboB(false)
            aboAB(false)
            postData.abo = 2
        } else{
            binding.newPostReceiverOSelectedTv.visibility = View.GONE
            binding.newPostReceiverOEmptyTv.visibility = View.VISIBLE
            postData.abo = -1
        }
        done()
    }

    fun aboAB(isSelected: Boolean){
        if (isSelected){
            binding.newPostReceiverAbSelectedTv.visibility = View.VISIBLE
            binding.newPostReceiverAbEmptyTv.visibility = View.GONE
            // 하나만 선택
            aboA(false)
            aboB(false)
            aboO(false)
            postData.abo = 3
        } else{
            binding.newPostReceiverAbSelectedTv.visibility = View.GONE
            binding.newPostReceiverAbEmptyTv.visibility = View.VISIBLE
            postData.abo = -1
        }
        done()
    }

    fun spinner(item: String){
        postData.loc = item
        done()
    }

    fun done(){
        Log.d("data", postData.toString())
        if (postData.abo == -1 || postData.rh == -1 || postData.loc == "지역 선택" || postData.con == ""){
            binding.newPostReceiverDoneBtnEmptyTv.visibility = View.VISIBLE
            binding.newPostReceiverDoneBtnActivateTv.visibility = View.GONE
        } else{
            binding.newPostReceiverDoneBtnEmptyTv.visibility = View.GONE
            binding.newPostReceiverDoneBtnActivateTv.visibility = View.VISIBLE
            body.abo = postData.abo
            body.rh = postData.rh
            body.context = postData.con
            body.location = postData.loc
            Log.d("New Feed Posting", body.toString())
        }
    }

    fun toHomeFragment(){
        requireActivity().supportFragmentManager.beginTransaction().replace(R.id.main_frm, HomeFragment()).commit()
    }

    override fun onPostNewFeedSuccess(response: FeedsResponse) {
        Log.d("onpostnewfeed", "SUCCESS")
    }

    override fun onPostNewFeedFailure(message: String) {
        Log.d("onPostNewFeedFailure", message)
    }
}