package be.mickverm.widgets.sample.recyclerview.ui.sectioned

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import be.mickverm.widget.recyclerview.adapter.RxDiffUtilSectionedAdapter
import be.mickverm.widget.recyclerview.adapter.SectionHeader
import be.mickverm.widgets.sample.R
import be.mickverm.widgets.sample.recyclerview.data.models.Item


class SectionedItemsAdapter :
    RxDiffUtilSectionedAdapter<Item, SectionedItemsAdapter.ItemViewHolder, SectionedItemsAdapter.HeaderViewHolder>() {

    override fun onCreateHeaderViewHolder(parent: ViewGroup) =
        HeaderViewHolder(inflateLayout(R.layout.item_header, parent))

    override fun onCreateItemViewHolder(parent: ViewGroup) =
        ItemViewHolder(inflateLayout(R.layout.item_item, parent))

    override fun onBindHeaderViewHolder(
        holder: HeaderViewHolder,
        position: Int,
        header: SectionHeader<Item>
    ) = holder.bind(header)

    override fun onBindItemViewHolder(holder: ItemViewHolder, position: Int, item: Item) =
        holder.bind(item)

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textView: TextView = itemView.findViewById(R.id.text)

        fun bind(item: Item) = with(item) {
            itemView.setBackgroundColor(color)
            textView.text = text
        }
    }

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvHeader: TextView = itemView.findViewById(R.id.tv_header)

        fun bind(sectionHeader: SectionHeader<Item>) = with(sectionHeader.item) {
            tvHeader.text = text[0].toString()
        }
    }
}
