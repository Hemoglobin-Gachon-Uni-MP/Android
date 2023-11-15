package com.pline.src.main.myPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pline.databinding.FragmentCertificationPhotoBinding

class CertificationPhotoFragment: BottomSheetDialogFragment() {
    private lateinit var binding: FragmentCertificationPhotoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCertificationPhotoBinding.inflate(inflater, container, false)


        return binding.root
    }
}