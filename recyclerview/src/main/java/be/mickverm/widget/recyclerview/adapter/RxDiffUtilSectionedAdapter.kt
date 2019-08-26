package be.mickverm.widget.recyclerview.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.functions.Consumer

abstract class RxDiffUtilSectionedAdapter<T, VH : RecyclerView.ViewHolder, SH : RecyclerView.ViewHolder> :
    BaseSectionedAdapter<T, VH, SH>(), Consumer<Pair<List<Any>, DiffUtil.DiffResult>> {

    override fun accept(pair: Pair<List<Any>, DiffUtil.DiffResult>) {
        updateItemsWithHeaders(pair.first)
        pair.second.dispatchUpdatesTo(this)
    }

    final override fun updateItems(items: List<T>) {
        throw IllegalStateException(
            "Method updateItems() should not be called in RecyclerView.Adapter extending " +
                    "RxDiffUtilSectionedAdapter."
        )
    }

    final override fun updateItemsWithHeaders(items: List<Any>) {
        this.items = items
    }

    final override fun shouldPlaceHeaderBetween(previous: T?, current: T) = false
}
