package be.mickverm.widgets.sample.recyclerview.ui.sectioned

import be.mickverm.widget.recyclerview.utils.HeaderDiffCallback
import be.mickverm.widgets.sample.recyclerview.data.models.Item

class SectionedItemDiffCallback(
    oldItems: List<Any>,
    newItems: List<Any>
) : HeaderDiffCallback<Item>(oldItems, newItems) {

    override fun areItemItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areItemContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }
}
