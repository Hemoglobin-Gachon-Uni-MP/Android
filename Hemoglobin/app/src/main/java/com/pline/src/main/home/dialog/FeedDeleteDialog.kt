package com.pline.src.main.home.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.pline.R
import com.pline.data.home.FeedDeleteService
import com.pline.data.home.FeedDeleteView
import com.pline.data.home.model.DeleteFeedResponse
import com.pline.databinding.DialogDeleteFeedBinding
import com.pline.src.main.home.HomeFragment

class FeedDeleteDialog(val feedId: Int) : DialogFragment(), FeedDeleteView {
    private lateinit var binding: DialogDeleteFeedBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        binding = DialogDeleteFeedBinding.inflate(inflater, container, false)
        setDialog()
        return binding.root
    }

    private fun setDialog() = with(binding) {
        // Transparent background for visible corner radius
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // Set click event of delete button
        btnDelete.setOnClickListener {
            FeedDeleteService(this@FeedDeleteDialog).tryDeleteFeed(feedId)
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.main_frm, HomeFragment()).commit()
            dismiss()
        }
        // Set click event of cancel button
        btnCancel.setOnClickListener {
            dismiss()
        }
    }

    override fun onDeleteFeedSuccess(response: DeleteFeedResponse) {
        Log.d("onDeleteFeed", "SUCCESS")
    }

    override fun onDeleteFeedFailure(message: String) {
        Log.d("onDeleteFeedFailed", message)
    }
}