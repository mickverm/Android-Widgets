package be.mickverm.widget.recyclerview.adapter

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, VH : RecyclerView.ViewHolder> : InflationAdapter<VH>() {

    var items: List<T> = listOf()

    final override fun getItemCount() = items.size

    fun getItem(position: Int): T = items[position]

    fun hasItems() = items.isNotEmpty()

    open fun updateItems(items: List<T>) {
        this.items = items
        notifyDataSetChanged()
    }

    final override fun onBindViewHolder(holder: VH, position: Int) =
        onBindViewHolder(holder, position, items[position])

    abstract fun onBindViewHolder(holder: VH, position: Int, item: T)

    final override fun onBindViewHolder(holder: VH, position: Int, payloads: MutableList<Any>) =
        onBindViewHolder(holder, position, items[position], payloads)

    open fun onBindViewHolder(holder: VH, position: Int, item: T, payloads: MutableList<Any>) =
        onBindViewHolder(holder, position, item)
}
