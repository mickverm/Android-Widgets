package be.mickverm.widget.recyclerview.adapter

import androidx.paging.PagedList
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.functions.Consumer

abstract class RxSectionedPagedListAdapter<T, VH : RecyclerView.ViewHolder>(
    diffCallback: DiffUtil.ItemCallback<T>
) : InflationPagedListAdapter<T, VH>(diffCallback), Consumer<PagedList<T>> {

    final override fun onBindViewHolder(holder: VH, position: Int) {
        val current = getItem(position) ?: return
        val header = position == 0 || insertSectionBetween(getItem(position - 1), current)
        onBindViewHolder(holder, position, current, header)
    }

    abstract fun onBindViewHolder(holder: VH, position: Int, item: T, showHeader: Boolean)

    final override fun onBindViewHolder(holder: VH, position: Int, payloads: MutableList<Any>) {
        val current = getItem(position) ?: return
        val header = position == 0 || insertSectionBetween(getItem(position - 1), current)
        onBindViewHolder(holder, position, current, payloads, header)
    }

    open fun onBindViewHolder(
        holder: VH,
        position: Int,
        item: T,
        payloads: MutableList<Any>,
        showHeader: Boolean
    ) = onBindViewHolder(holder, position, item, showHeader)

    abstract fun insertSectionBetween(previous: T?, current: T): Boolean

    final override fun accept(pagedList: PagedList<T>) {
        submitList(pagedList) { onItemsChanged() }
    }

    open fun onItemsChanged() {}
}
