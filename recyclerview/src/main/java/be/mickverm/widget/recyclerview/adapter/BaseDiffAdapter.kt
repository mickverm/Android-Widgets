package be.mickverm.widget.recyclerview.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseDiffAdapter<T, VH : RecyclerView.ViewHolder> : BaseAdapter<T, VH>() {

    final override fun updateItems(items: List<T>) {
        if (this.items.isEmpty()) {
            this.items = items
            notifyItemRangeInserted(0, items.count())
        } else {
            val diffResult = DiffUtil.calculateDiff(getDiffUtilCallBack(this.items, items), true)
            this.items = items
            diffResult.dispatchUpdatesTo(this)
        }
    }

    abstract fun getDiffUtilCallBack(oldItems: List<T>, newItems: List<T>): DiffUtil.Callback
}
