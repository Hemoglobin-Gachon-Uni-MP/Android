package com.pline.src.main.myPage

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pline.R
import com.pline.config.BaseFragment
import com.pline.databinding.FragmentCertificationListBinding

// Certification List
class CertificationListFragment() : BaseFragment<FragmentCertificationListBinding>(FragmentCertificationListBinding::bind, R.layout.fragment_certification_list) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            // Set click event of back button
            imgbtnBack.setOnClickListener {
                // Move to previous page
                activity?.let { it.onBackPressed() }
            }
        }

        binding.rvCertList.run {
            var certList: ArrayList<String> = arrayListOf()
            certList.add("서서리 / 2023.09.27 / FE234DV9")
            certList.add("서서리 / 2023.09.27 / FE234DV9")
            certList.add("서서리 / 2023.09.27 / FE234DV9")
            certList.add("서서리 / 2023.09.27 / FE234DV9")
            certList.add("서서리 / 2023.09.27 / FE234DV9")
            certList.add("서서리 / 2023.09.27 / FE234DV9")

            // Set Recycler View Adapter
            val certAdapter = CertificationListRVAdapter(certList)
            adapter = certAdapter

            // Set layout of recycler view
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        }

        binding.btnAddCert.setOnClickListener { // 헌혈 인증 추가하기 버튼 클릭
            // certificationFragment open open
            val addCertFrag = CertificationFragment()
            addCertFrag.show(requireActivity().supportFragmentManager, addCertFrag.tag)
        }
    }
}