package com.pline.src.main.info

import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.ImageView
import com.pline.R
import com.pline.config.BaseFragment
import com.pline.databinding.FragmentInfoBinding


class InfoFragment : BaseFragment<FragmentInfoBinding>(FragmentInfoBinding::bind, R.layout.fragment_info) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            fragmentInfoWvConditionsDesc.settings.javaScriptEnabled = true
            fragmentInfoWvConditionsDesc.loadUrl("file:///android_asset/conditions.html")
            setVisibility(fragmentInfoClConditions,
                fragmentInfoWvConditionsDesc,
                fragmentInfoBtnCondition)
            setVisibility(fragmentInfoClPrecautionBefore,
                fragmentInfoClPrecautionBeforeDesc,
                fragmentInfoBtnPrecautionBefore)
            setVisibility(fragmentInfoClPrecautionAfter,
                fragmentInfoClPrecautionAfterDesc,
                fragmentInfoBtnPrecautionAfter)
        }
    }
    private fun setVisibility(target : View, desc : View, arrow : ImageView) {
        target.setOnClickListener {
            desc.apply {
                visibility = if (visibility == View.VISIBLE) {
                    arrow.setImageResource(R.drawable.ic_arrow_right)
                    View.GONE
                } else {
                    arrow.setImageResource(R.drawable.ic_arrow_down)
                    View.VISIBLE
                }
            }

        }
    }
}