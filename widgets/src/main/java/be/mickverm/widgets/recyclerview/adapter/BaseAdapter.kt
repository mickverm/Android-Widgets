package be.mickverm.widgets.recyclerview.adapter

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    var items: List<T> = listOf()

    final override fun getItemCount() = items.size

    fun getItem(position: Int): T = items[position]

    abstract fun updateItems(items: List<T>)
}
