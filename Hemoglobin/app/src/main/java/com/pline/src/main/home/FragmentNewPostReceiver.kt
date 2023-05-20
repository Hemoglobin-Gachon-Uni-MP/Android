package com.pline.src.main.home

import android.os.Bundle
import android.view.View
import com.pline.R
import com.pline.config.BaseFragment
import com.pline.databinding.FragmentNewPostReceiverBinding

class FragmentNewPostReceiver: BaseFragment<FragmentNewPostReceiverBinding> (FragmentNewPostReceiverBinding::bind, R.layout.fragment_new_post_receiver) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        binding.newPostReceiverCloseIconIv.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.main_frm, HomeFragment()).commit()
        }
    }
}