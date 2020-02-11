package be.mickverm.widgets.sample.recyclerview.ui.sectioned

import android.view.ViewGroup
import be.mickverm.widget.recyclerview.adapter.RxDiffUtilSectionedAdapter
import be.mickverm.widget.recyclerview.adapter.SectionHeader
import be.mickverm.widget.recyclerview.adapter.viewholder.BaseViewBindingViewHolder
import be.mickverm.widgets.sample.databinding.ItemHeaderBinding
import be.mickverm.widgets.sample.databinding.ItemItemBinding
import be.mickverm.widgets.sample.recyclerview.data.models.Item

class SectionedItemsAdapter :
    RxDiffUtilSectionedAdapter<Item, SectionedItemsAdapter.ItemViewHolder, SectionedItemsAdapter.HeaderViewHolder>() {

    override fun onCreateHeaderViewHolder(parent: ViewGroup): HeaderViewHolder {
        return HeaderViewHolder(parent.inflateBinding(ItemHeaderBinding::inflate))
    }

    override fun onCreateItemViewHolder(parent: ViewGroup): ItemViewHolder {
        return ItemViewHolder(parent.inflateBinding(ItemItemBinding::inflate))
    }

    override fun onBindHeaderViewHolder(
        holder: HeaderViewHolder,
        position: Int,
        header: SectionHeader<Item>
    ) {
        holder.bind(header)
    }

    override fun onBindItemViewHolder(holder: ItemViewHolder, position: Int, item: Item) {
        holder.bind(item)
    }

    class HeaderViewHolder(private val binding: ItemHeaderBinding) :
        BaseViewBindingViewHolder(binding) {

        fun bind(sectionHeader: SectionHeader<Item>) = with(sectionHeader.item) {
            binding.tvHeader.text = text[0].toString()
        }
    }

    class ItemViewHolder(private val binding: ItemItemBinding) : BaseViewBindingViewHolder(binding) {

        fun bind(item: Item) = with(item) {
            itemView.setBackgroundColor(color)
            binding.tvText.text = text
        }
    }
}
