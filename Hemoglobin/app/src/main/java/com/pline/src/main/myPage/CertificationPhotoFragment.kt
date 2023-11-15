package com.pline.src.main.myPage

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pline.databinding.FragmentCertificationPhotoBinding

class CertificationPhotoFragment(val name: String): BottomSheetDialogFragment() {
    private lateinit var binding: FragmentCertificationPhotoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCertificationPhotoBinding.inflate(inflater, container, false)

        /** 이전 버튼 클릭 **/
        binding.fragmentCertificationPhotoPreviousBtnTv.setOnClickListener {
            dismiss()
        }

        return binding.root
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)

        val dialog = CertificationFragment(name)
        dialog.show(requireActivity().supportFragmentManager, dialog.tag)
    }
}