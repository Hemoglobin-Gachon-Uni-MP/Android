package com.pline.src.main.myPage

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

// RecyclerView Item Decoration for medal item
class MedalListRVDecoration(
    private val spanCount: Int, // column
    private val space: Int
) : ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount + 1

        if (position < spanCount){
            outRect.top = space
        }
        if (column == spanCount) {
            outRect.left = space / 2
        } else {
            outRect.left = space
        }
        if (column == spanCount){
            outRect.right = space
        }
        outRect.bottom = space
    }
}