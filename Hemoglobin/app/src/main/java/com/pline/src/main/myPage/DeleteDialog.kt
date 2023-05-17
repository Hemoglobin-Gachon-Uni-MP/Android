package com.pline.src.main.myPage

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.pline.databinding.DialogDeleteAccountBinding

// Dialog for deleting account
class DeleteDialog : DialogFragment() {

    private lateinit var binding : DialogDeleteAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        binding = DialogDeleteAccountBinding.inflate(inflater, container, false)
        setDialog()
        return binding.root
    }

    // Set custom dialog
    private fun setDialog() = with(binding) {
        // Transparent background for visible corner radius
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // Set click event of delete button
        btnDelete.setOnClickListener {
            /// todo - 회원 탈퇴 API 연동
        }
        // Set click event of cancel button
        btnCancel.setOnClickListener {
            dismiss()
        }
    }
}