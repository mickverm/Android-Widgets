package be.mickverm.widget.recyclerview.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.functions.Consumer

abstract class RxDiffUtilBaseAdapter<T, VH : RecyclerView.ViewHolder> : BaseAdapter<T, VH>(),
    Consumer<Pair<List<T>, DiffUtil.DiffResult>> {

    override fun accept(pair: Pair<List<T>, DiffUtil.DiffResult>) {
        updateItems(pair.first)
        pair.second.dispatchUpdatesTo(this)
    }

    final override fun updateItems(items: List<T>) {
        this.items = items
    }
}
