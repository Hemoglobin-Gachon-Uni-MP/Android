package com.pline.src.main.register

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import com.pline.databinding.FragmentDatePickerDialogBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class DatePickerDialogFragment(private val listener: DatePickerListener) : DialogFragment(), DatePickerDialog.OnDateSetListener {
    lateinit var dialogBinding: FragmentDatePickerDialogBinding

    interface DatePickerListener {
        fun onDateSelected(date: String)
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(requireActivity(), this, year, month, day)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialogBinding = FragmentDatePickerDialogBinding.inflate(inflater, container, false)
        return dialogBinding.root
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        listener.onDateSelected(formatDateString(year, month, day))
    }
    private fun formatDateString(year: Int, month: Int, day: Int): String {
        return "${year}.${month + 1}.${day}"
    }

}