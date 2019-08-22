package be.mickverm.widgets.recyclerview.decoration

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView

// TODO add RTL/LTR support.
class DividerItemDecoration(
    @ColorInt color: Int,
    thickness: Int,
    private val offsetLeft: Int = 0,
    private val offsetRight: Int = 0
) : RecyclerView.ItemDecoration() {

    private val paint = Paint()

    init {
        paint.color = color
        paint.strokeWidth = thickness.toFloat()
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val params = view.layoutParams as RecyclerView.LayoutParams

        // we want to retrieve the position in the list
        val position = params.viewAdapterPosition

        // and add a separator to any view but the last one
        if (position < state.itemCount) {
            outRect.set(0, 0, 0, paint.strokeWidth.toInt()) // left, top, right, bottom
        } else {
            outRect.setEmpty() // 0, 0, 0, 0
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        // we set the stroke thickness before, so as to correctly draw the line we have to offset by thickness / 2
        val offset = (paint.strokeWidth / 2).toInt()

        // this will iterate over every visible view
        for (i in 0 until parent.childCount - 1) {
            // get the view
            val view = parent.getChildAt(i)
            val params = view.layoutParams as RecyclerView.LayoutParams

            // get the position
            val position = params.viewAdapterPosition

            // and finally draw the separator
            if (position < state.itemCount) {
                c.drawLine(
                    (view.left + offsetLeft).toFloat(), (view.bottom + offset).toFloat(),
                    (view.right - offsetRight).toFloat(), (view.bottom + offset).toFloat(), paint
                )
            }
        }
    }
}
