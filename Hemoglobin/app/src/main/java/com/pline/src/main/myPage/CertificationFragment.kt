package com.pline.src.main.myPage

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pline.databinding.FragmentCertificationBinding

class CertificationFragment(): BottomSheetDialogFragment() {
    private lateinit var binding: FragmentCertificationBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCertificationBinding.inflate(inflater, container, false)

        /** 이름 경고 문구 **/
        binding.fragmentCertContentsNameTextfieldEt.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                /**
                 * 서버에서 이름 받아오기
                 * 받아온 이름이랑 입력한 이름이랑 다르면 경고문구 표시**/
                // 같을때
                binding.warningNameLl.visibility = View.INVISIBLE
                // 다를 때
                binding.warningNameLl.visibility = View.VISIBLE
            }

        })

        /** 증서 번호 경고 문구 **/
        binding.fragmentCertContentsNumTextfieldEt.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                /**
                 * 증서 번호 형식 정하기
                 * 입력한 번호랑 형식 다르면 경고문구 표시**/
                // 같을때
                binding.warningNumLl.visibility = View.INVISIBLE
                // 다를 때
                binding.warningNumLl.visibility = View.VISIBLE
            }

        })

        /** 날짜 경고 문구 **/
        binding.fragmentCertContentsDateTextfieldEt.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                /**
                 * 날짜 형식 정하기
                 * 입력한 날짜랑 형식 다르면 경고문구 표시**/
                // 같을때
                binding.warningDateLl.visibility = View.INVISIBLE
                // 다를 때
                binding.warningDateLl.visibility = View.VISIBLE
            }

        })

        binding.fragmentCertificationNextBtnTv.setOnClickListener {

        }

        return binding.root
    }
}