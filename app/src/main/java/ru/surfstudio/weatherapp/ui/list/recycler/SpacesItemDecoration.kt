package ru.surfstudio.weatherapp.ui.list.recycler

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

/**
 * Decorator, for adding padding between cells in the grid
 */
class SpacesItemDecoration(
    private val space: Int,
    private val columnsCount: Int
) : ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        val totalCount = parent.adapter?.itemCount ?: 0

        if (itemPosition in 0 until columnsCount) {
            outRect.top = space
        } else {
            outRect.top = space / 2
        }

        if (itemPosition in (totalCount - columnsCount)..totalCount) {
            outRect.bottom = space
        } else {
            outRect.bottom = space / 2
        }

        if (itemPosition % columnsCount == 0) {
            outRect.left = space
        } else {
            outRect.left = space / 2
        }

        if (itemPosition % columnsCount == columnsCount - 1) {
            outRect.right = space
        } else {
            outRect.right = space / 2
        }
    }
}