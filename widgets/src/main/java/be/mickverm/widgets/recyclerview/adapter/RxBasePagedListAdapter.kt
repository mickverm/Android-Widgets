package be.mickverm.widgets.recyclerview.adapter

import androidx.paging.PagedList
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.functions.Consumer

abstract class RxBasePagedListAdapter<T, VH : RecyclerView.ViewHolder>(
    diffCallback: DiffUtil.ItemCallback<T>
) : BasePagedListAdapter<T, VH>(diffCallback), Consumer<PagedList<T>> {

    override fun accept(pagedList: PagedList<T>) = submitList(pagedList)
}
