package com.pline.src.main.info

import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.ImageView
import com.pline.R
import com.pline.config.BaseFragment
import com.pline.databinding.FragmentInfoBinding

/**
 * Information Fragment that is bottom navigation item
 */
class InfoFragment : BaseFragment<FragmentInfoBinding>(FragmentInfoBinding::bind, R.layout.fragment_info) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            // Set up html files within a project in a Web view
            fragmentInfoWvConditionsDesc.settings.javaScriptEnabled = true
            fragmentInfoWvConditionsDesc.loadUrl("file:///android_asset/conditions.html")

            // Determine the visibility of layout with content when click on each tab
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
    // Determine the visibility of layout with content when click on each tab
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