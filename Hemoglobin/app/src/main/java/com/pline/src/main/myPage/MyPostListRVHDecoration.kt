package com.pline.src.main.myPage

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

// Horizontal RecyclerView Item Decoration for my post list in my page
class MyPostListRVHDecoration(private val divWidth: Int) : ItemDecoration() {
    // Set right spacing between my posts
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.right = divWidth
    }
}