package com.pline.src.main.register

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.pline.databinding.FragmentDatePickerDialogBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class DatePickerDialogFragment(var date:String, val itemClick: (String) -> Unit) : DialogFragment() {
    lateinit var binding: FragmentDatePickerDialogBinding

    interface DatePickerListener {
        fun onDateSelected(date: String)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDatePickerDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vDatePicker.maxDate = System.currentTimeMillis()
        binding.datePickerBtn.setOnClickListener {
            date = formatDateString(binding.vDatePicker.year, binding.vDatePicker.month, binding.vDatePicker.dayOfMonth)
            itemClick(date)
            dialog?.dismiss()
        }
        binding.closeBtn.setOnClickListener {
            dialog?.dismiss()
        }
    }
    private fun formatDateString(year: Int, month: Int, day: Int): String {
        return "${year}.${month + 1}.${day}"
    }

}