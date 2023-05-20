package com.pline.src.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pline.R
import com.pline.config.BaseFragment
import com.pline.databinding.FragmentNewPostProviderBinding

class FragmentNewPostProvider: BaseFragment<FragmentNewPostProviderBinding> (FragmentNewPostProviderBinding::bind, R.layout.fragment_new_post_provider) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        binding.newPostProviderCloseIconIv.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.main_frm, HomeFragment()).commit()
        }
    }
}