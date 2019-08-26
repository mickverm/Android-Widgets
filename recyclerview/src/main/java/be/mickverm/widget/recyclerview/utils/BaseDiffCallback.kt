package be.mickverm.widget.recyclerview.utils

import androidx.recyclerview.widget.DiffUtil

abstract class BaseDiffCallback<T>(
    private val oldItems: List<T>,
    private val newItems: List<T>
) : DiffUtil.Callback() {

    final override fun getOldListSize() = oldItems.size
    final override fun getNewListSize() = newItems.size

    final override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        areItemItemsTheSame(oldItems[oldItemPosition], newItems[newItemPosition])

    abstract fun areItemItemsTheSame(oldItem: T, newItem: T): Boolean

    final override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        areItemContentsTheSame(oldItems[oldItemPosition], newItems[newItemPosition])

    abstract fun areItemContentsTheSame(oldItem: T, newItem: T): Boolean
}
