package be.mickverm.widget.recyclerview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import be.mickverm.common.extensions.visible

class EmptiableRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    var emptyView: View? = null

    @IdRes
    private var emptyViewId = NO_EMPTY_VIEW

    companion object {
        const val NO_EMPTY_VIEW = -1
    }

    init {
        if (attrs != null) {
            val a = context.obtainStyledAttributes(
                attrs,
                R.styleable.EmptiableRecyclerView
            )
            emptyViewId = a.getResourceId(
                R.styleable.EmptiableRecyclerView_erv_empty_view,
                NO_EMPTY_VIEW
            )
            a.recycle()
        }
    }

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

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (emptyView == null) setEmptyView(emptyViewId)
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

    fun setEmptyView(@IdRes idRes: Int) {
        emptyView = rootView.findViewById(idRes)
    }
}
