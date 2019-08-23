package be.mickverm.widgets.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    var items: List<T> = listOf()

    final override fun getItemCount() = items.size

    fun getItem(position: Int): T = items[position]

    fun hasItems() = items.isNotEmpty()

    abstract fun updateItems(items: List<T>)

    final override fun onBindViewHolder(holder: VH, position: Int) =
        onBindViewHolder(holder, position, items[position])

    abstract fun onBindViewHolder(holder: VH, position: Int, item: T)

    final override fun onBindViewHolder(holder: VH, position: Int, payloads: MutableList<Any>) =
        onBindViewHolder(holder, position, items[position], payloads)

    open fun onBindViewHolder(holder: VH, position: Int, item: T, payloads: MutableList<Any>) =
        onBindViewHolder(holder, position, item)

    fun inflateLayout(@LayoutRes layoutRes: Int, parent: ViewGroup): View =
        LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
}
