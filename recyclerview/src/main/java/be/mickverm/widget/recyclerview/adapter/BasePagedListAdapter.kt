package be.mickverm.widget.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BasePagedListAdapter<T, VH : RecyclerView.ViewHolder>(
    diffCallback: DiffUtil.ItemCallback<T>
) : PagedListAdapter<T, VH>(diffCallback) {

    final override fun onBindViewHolder(holder: VH, position: Int) =
        onBindViewHolder(holder, position, getItem(position))

    abstract fun onBindViewHolder(holder: VH, position: Int, item: T?)

    override fun onBindViewHolder(holder: VH, position: Int, payloads: MutableList<Any>) =
        onBindViewHolder(holder, position, getItem(position), payloads)

    open fun onBindViewHolder(holder: VH, position: Int, item: T?, payloads: MutableList<Any>) =
        onBindViewHolder(holder, position, item)

    fun inflateLayout(@LayoutRes layoutRes: Int, parent: ViewGroup): View =
        LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
}
