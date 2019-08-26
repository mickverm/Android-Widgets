package be.mickverm.widget.recyclerview.utils

import androidx.recyclerview.widget.DiffUtil
import be.mickverm.widget.recyclerview.adapter.SectionHeader

abstract class HeaderDiffCallback<T>(
    private val oldItems: List<Any>,
    private val newItems: List<Any>
) : DiffUtil.Callback() {

    final override fun getOldListSize() = oldItems.size
    final override fun getNewListSize() = newItems.size

    @Suppress("UNCHECKED_CAST")
    final override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]

        return if (oldItem is SectionHeader<*> || newItem is SectionHeader<*>) false
        else areItemItemsTheSame(oldItem as T, newItem as T)
    }

    abstract fun areItemItemsTheSame(oldItem: T, newItem: T): Boolean

    @Suppress("UNCHECKED_CAST")
    final override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        areItemContentsTheSame(oldItems[oldItemPosition] as T, newItems[newItemPosition] as T)

    abstract fun areItemContentsTheSame(oldItem: T, newItem: T): Boolean
}
