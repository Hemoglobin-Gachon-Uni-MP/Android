package com.pline.src.main.myPage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pline.data.mypage.model.MyCertListResult
import com.pline.databinding.ItemCertBinding

// Vertical RecyclerView adapter for Certification List
class CertificationListRVAdapter(private val certList: ArrayList<MyCertListResult>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var certListBinding: ItemCertBinding

    // Bind
    inner class ViewHolder(private val certListBinding: ItemCertBinding): RecyclerView.ViewHolder(certListBinding.root) {
        // Set data
        fun bind(cert: MyCertListResult) {
            certListBinding.apply {
                itemTv.text = "${cert.name} / ${cert.date} / ${cert.certificationNum}"
            }
        }
    }

    // Configure view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        certListBinding = ItemCertBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(certListBinding)
    }

    // Set total item count to show
    override fun getItemCount(): Int = certList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CertificationListRVAdapter.ViewHolder).bind(certList[holder.adapterPosition])
    }
}