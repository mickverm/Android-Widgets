package be.mickverm.widgets.recyclerview.adapter

import androidx.recyclerview.widget.RecyclerView
import io.reactivex.functions.Consumer

abstract class RxBaseAdapter<T, VH : RecyclerView.ViewHolder> : BaseAdapter<T, VH>(),
    Consumer<List<T>> {

    final override fun accept(items: List<T>) {
        updateItems(items)
    }

    override fun updateItems(items: List<T>) {
        this.items = items
        notifyDataSetChanged()
    }
}
