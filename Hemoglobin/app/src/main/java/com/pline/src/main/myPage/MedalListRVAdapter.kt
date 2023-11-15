package com.pline.src.main.myPage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pline.R
import com.pline.data.mypage.model.MedalInfo
import com.pline.databinding.ItemMedalBinding

// Vertical RecyclerView adapter for Medal List
class MedalListRVAdapter(private val medalList: ArrayList<MedalInfo>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var medalBinding: ItemMedalBinding

    // Bind
    inner class ViewHolder(private val medalBinding: ItemMedalBinding): RecyclerView.ViewHolder(medalBinding.root) {
        // Set data
        fun bind(data: MedalInfo) {
            medalBinding.apply {
                val defaultImage = R.drawable.ic_newbie_medal
                val url = data.medalImg

                Glide.with(this.root)
                    .load(url) // 불러올 이미지 url
                    .error(defaultImage) // 로딩 에러 발생 시 표시할 이미지
                    .fallback(defaultImage) // 로드할 url 이 비어있을(null 등) 경우 표시할 이미지
                    .into(imgMedal) // 이미지를 넣을 뷰

                tvTitle.text = data.name
                tvDate.text = data.date + " 획득"
            }
        }
    }

    // Configure view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        medalBinding = ItemMedalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(medalBinding)
    }

    // Set total item count to show
    override fun getItemCount(): Int = medalList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MedalListRVAdapter.ViewHolder).bind(medalList[holder.adapterPosition])
    }
}