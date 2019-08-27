package be.mickverm.widget.recyclerview.decoration

import android.graphics.Rect
import android.view.View
import androidx.annotation.IntRange
import androidx.recyclerview.widget.RecyclerView

class SpacingItemDecoration(
    @IntRange(from = 1) private val spacing: Int,
    private val spanCount: Int = 1
) : RecyclerView.ItemDecoration() {

    init {
        require(spanCount >= 1) { "SpacingItemDecoration must have a span of at least 1!" }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount

        outRect.left = spacing - column * spacing / spanCount
        outRect.right = (column + 1) * spacing / spanCount

        if (position < spanCount)
            outRect.top = spacing

        outRect.bottom = spacing
    }
}
