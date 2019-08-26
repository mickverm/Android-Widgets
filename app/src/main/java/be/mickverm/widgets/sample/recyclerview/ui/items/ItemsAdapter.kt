package be.mickverm.widgets.sample.recyclerview.ui.items

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import be.mickverm.widget.recyclerview.adapter.RxDiffUtilAdapter
import be.mickverm.widgets.sample.R
import be.mickverm.widgets.sample.recyclerview.data.models.Item

class ItemsAdapter : RxDiffUtilAdapter<Item, ItemsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(inflateLayout(R.layout.item_item, parent))

    override fun onBindViewHolder(holder: ViewHolder, position: Int, item: Item) =
        holder.bind(item)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textView: TextView = itemView.findViewById(R.id.text)

        fun bind(item: Item) = with(item) {
            itemView.setBackgroundColor(color)
            textView.text = text
        }
    }
}
