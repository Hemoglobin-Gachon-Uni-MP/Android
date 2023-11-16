package com.pline.src.main.myPage

import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pline.R
import com.pline.databinding.FragmentCertificationBinding

class CertificationFragment(val name: String): BottomSheetDialogFragment() {
    private lateinit var binding: FragmentCertificationBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCertificationBinding.inflate(inflater, container, false)

        val bottomView = layoutInflater.inflate(R.layout.fragment_certification, null)
        val bottomDialog = BottomSheetDialog(this.requireContext())
        bottomDialog.behavior.isDraggable = true

        /** 이름 경고 문구 **/
        binding.fragmentCertContentsNameTextfieldEt.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().equals(name)){  // 같을때
                    binding.warningNameLl.visibility = View.INVISIBLE
                } else{  // 다를 때
                    binding.warningNameLl.visibility = View.VISIBLE
                }

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
                if (s.toString().length == 12
                    && s.toString()[2].equals('-')
                    && s.toString()[5].equals('-')){
                    // 같을때
                    binding.warningNumLl.visibility = View.INVISIBLE
                } else {
                    // 다를 때
                    binding.warningNumLl.visibility = View.VISIBLE
                }
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
                if (s.toString().length == 10
                    && s.toString()[4].equals('.')
                    && s.toString()[7].equals('.')) {
                    // 같을때
                    binding.warningDateLl.visibility = View.INVISIBLE
                } else {
                    // 다를 때
                    binding.warningDateLl.visibility = View.VISIBLE
                }
            }

        })

        /**  다음 페이지로 넘어가기 **/
        binding.fragmentCertificationNextBtnTv.setOnClickListener {
            val dialog = CertificationPhotoFragment(name)
            dialog.show(requireActivity().supportFragmentManager, dialog.tag)
            dismiss()
        }

        return binding.root
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)

    }
}