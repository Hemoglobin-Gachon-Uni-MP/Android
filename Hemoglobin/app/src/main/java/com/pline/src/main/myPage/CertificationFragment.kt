package com.pline.src.main.myPage

import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pline.R
import com.pline.config.ApplicationClass.Companion.sSharedPreferences
import com.pline.databinding.FragmentCertificationBinding

class CertificationFragment(): BottomSheetDialogFragment() {
    private lateinit var binding: FragmentCertificationBinding
    val name = sSharedPreferences.getString("name", "홍길동")
    var eName: String = ""
    var eNum: String = ""
    var eDate: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCertificationBinding.inflate(inflater, container, false)

        // bottomsheetdialog 왜 안움직이냐ㅜ
//        val bottomView = layoutInflater.inflate(R.layout.fragment_certification, null)
//        val bottomDialog = BottomSheetDialog(this.requireContext())
//        bottomDialog.behavior.isDraggable = true


        /** 이름 경고 문구 **/
        binding.fragmentCertContentsNameTextfieldEt.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                eName = binding.fragmentCertContentsNameTextfieldEt.text.toString()
                if (isValidName()){  // 같을때
                    binding.warningNameLl.visibility = View.INVISIBLE
                } else{  // 다를 때
                    binding.warningNameLl.visibility = View.VISIBLE
                }
            }
        })

        /** 증서 번호 경고 문구 **/
        binding.fragmentCertContentsNumTextfieldEt.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                /**
                 * 증서 번호 형식 정하기
                 * 입력한 번호랑 형식 다르면 경고문구 표시**/
                eNum = binding.fragmentCertContentsNumTextfieldEt.text.toString()
                if (eNum.length == 2 || eNum.length == 5){
                    eNum += "-"
                    binding.fragmentCertContentsNumTextfieldEt.setText(eNum)

                    var pos: Int = eNum.length
                    binding.fragmentCertContentsNumTextfieldEt.setSelection(pos)
                }
                if (isValidNum()){
                    // 같을때
                    binding.warningNumLl.visibility = View.INVISIBLE
                } else {
                    // 다를 때
                    binding.warningNumLl.visibility = View.VISIBLE
                }
            }
        })

        /** 날짜 경고 문구 **/
        binding.fragmentCertContentsDateTextfieldEt.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                /**
                 * 날짜 형식 정하기
                 * 입력한 날짜랑 형식 다르면 경고문구 표시**/
                eDate = binding.fragmentCertContentsDateTextfieldEt.text.toString()
                if (eDate.length == 4 || eDate.length == 7){
                    eDate += "."
                    binding.fragmentCertContentsDateTextfieldEt.setText(eDate)

                    var pos: Int = eDate.length
                    binding.fragmentCertContentsDateTextfieldEt.setSelection(pos)
                }

                if (isValidDate()) {
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
            if (isValidName()
                && isValidNum()
                && isValidDate()){  // 제대로 입력했을 때

                val dialog = CertificationPhotoFragment(eName, eNum, eDate)
                dialog.show(requireActivity().supportFragmentManager, dialog.tag)
                dismiss()
            } else{
                Toast.makeText(requireActivity(), "입력하신 텍스트의 형식을 확인해주세요", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    private fun isValidName(): Boolean {
        return eName.equals(name)
    }

    private fun isValidNum(): Boolean {
        return eNum.length == 12
                && eNum[2].equals('-')
                && eNum[5].equals('-')
    }

    private fun isValidDate(): Boolean {
        return eDate.length == 10
                && eDate[4].equals('.')
                && eDate[7].equals('.')
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)

    }
}