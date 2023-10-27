package com.pline.src.main.myPage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pline.R
import com.pline.databinding.ItemMedalBinding

data class MedalDummyData(
    val imgNum: Int,
    val date: String
)

// Vertical RecyclerView adapter for Medal List
class MedalListRVAdapter(private val medalList: ArrayList<MedalDummyData>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var medalBinding: ItemMedalBinding

    // Bind
    inner class ViewHolder(private val medalBinding: ItemMedalBinding): RecyclerView.ViewHolder(medalBinding.root) {
        // Set data
        fun bind(data: MedalDummyData) {
            medalBinding.apply {
                when (data.imgNum) {
                    1 -> {
                        imgMedal.setImageResource(R.drawable.ic_newbie_medal)
                        tvTitle.text = "헌혈 입문자"
                    }
                    2 -> {
                        imgMedal.setImageResource(R.drawable.ic_newbie_medal)
                        tvTitle.text = "생명의 씨앗"
                    }
                    3 -> {
                        imgMedal.setImageResource(R.drawable.ic_newbie_medal)
                        tvTitle.text = "헌혈하는 기쁨"
                    }
                    4 -> {
                        imgMedal.setImageResource(R.drawable.ic_newbie_medal)
                        tvTitle.text = "생명의 빛"
                    }
                    5 -> {
                        imgMedal.setImageResource(R.drawable.ic_newbie_medal)
                        tvTitle.text = "우리 모두의 힘"
                    }
                    6 -> {
                        imgMedal.setImageResource(R.drawable.ic_newbie_medal)
                        tvTitle.text = "헌혈 히어로"
                    }
                    7 -> {
                        imgMedal.setImageResource(R.drawable.ic_newbie_medal)
                        tvTitle.text = "헌혈 마스터"
                    }
                    8 -> {
                        imgMedal.setImageResource(R.drawable.ic_newbie_medal)
                        tvTitle.text = "헌혈의 전설"
                    }
                }
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