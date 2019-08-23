package be.mickverm.widgets.recyclerview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import be.mickverm.common.extensions.visible

class EmptiableRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    var emptyView: View? = null

    private val emptyObserver = object : AdapterDataObserver() {
        override fun onChanged() = checkIfEmpty()
        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) = checkIfEmpty()
        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) = checkIfEmpty()
    }

    override fun setAdapter(adapter: Adapter<*>?) {
        super.setAdapter(adapter)

        if (adapter != null) {
            adapter.registerAdapterDataObserver(emptyObserver)
            emptyObserver.onChanged()
        }
    }

    private fun checkIfEmpty() {
        val adapter = adapter
        val emptyView = emptyView

        if (adapter != null && emptyView != null) {
            val empty = adapter.itemCount == 0

            emptyView.visible(empty)
            this@EmptiableRecyclerView.visible(!empty, false)
        }
    }
}
