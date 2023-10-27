package com.pline.src.main.myPage

import android.os.Bundle
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
        return binding.root
    }
}