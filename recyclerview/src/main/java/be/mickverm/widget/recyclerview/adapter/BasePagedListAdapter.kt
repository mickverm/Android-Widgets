package be.mickverm.widget.recyclerview.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BasePagedListAdapter<T, VH : RecyclerView.ViewHolder>(
    diffCallback: DiffUtil.ItemCallback<T>
) : InflationPagedListAdapter<T, VH>(diffCallback) {

    final override fun onBindViewHolder(holder: VH, position: Int) =
        onBindViewHolder(holder, position, getItem(position))

    abstract fun onBindViewHolder(holder: VH, position: Int, item: T?)

    final override fun onBindViewHolder(holder: VH, position: Int, payloads: MutableList<Any>) =
        onBindViewHolder(holder, position, getItem(position), payloads)

    open fun onBindViewHolder(holder: VH, position: Int, item: T?, payloads: MutableList<Any>) =
        onBindViewHolder(holder, position, item)
}
