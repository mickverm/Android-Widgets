package be.mickverm.widget.recyclerview.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.mickverm.widget.recyclerview.utils.SectionProvider

private const val VIEW_TYPE_HEADER = 0
private const val VIEW_TYPE_ITEM = 1

abstract class BaseSectionedAdapter<T, VH : RecyclerView.ViewHolder, SH : RecyclerView.ViewHolder> :
    InflationAdapter<RecyclerView.ViewHolder>() {

    var items: List<Any> = listOf()

    final override fun getItemCount() = items.size

    open fun updateItems(items: List<T>) =
        updateItemsWithHeaders(SectionProvider.insertSections(items) { previous, current ->
            shouldPlaceHeaderBetween(previous, current)
        })

    open fun updateItemsWithHeaders(items: List<Any>) {
        this.items = items
        notifyDataSetChanged()
    }

    open fun shouldPlaceHeaderBetween(previous: T?, current: T): Boolean = false

    final override fun getItemViewType(position: Int) = when {
        items[position] is SectionHeader<*> -> VIEW_TYPE_HEADER
        else -> VIEW_TYPE_ITEM
    }

    final override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder = when (viewType) {
        VIEW_TYPE_HEADER -> onCreateHeaderViewHolder(parent)
        VIEW_TYPE_ITEM -> onCreateItemViewHolder(parent)
        else -> throw IllegalArgumentException("Unsupported ViewHolder viewType: $viewType")
    }

    abstract fun onCreateHeaderViewHolder(parent: ViewGroup): SH

    abstract fun onCreateItemViewHolder(parent: ViewGroup): VH

    @Suppress("UNCHECKED_CAST")
    final override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        return when (val viewType = holder.itemViewType) {
            VIEW_TYPE_HEADER -> onBindHeaderViewHolder(
                holder as SH,
                position,
                item as SectionHeader<T>
            )
            VIEW_TYPE_ITEM -> onBindItemViewHolder(holder as VH, position, item as T)
            else -> throw IllegalArgumentException("Unsupported ViewHolder viewType: $viewType")
        }
    }

    abstract fun onBindHeaderViewHolder(holder: SH, position: Int, header: SectionHeader<T>)

    abstract fun onBindItemViewHolder(holder: VH, position: Int, item: T)

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        val item = items[position]
        return when (val viewType = holder.itemViewType) {
            VIEW_TYPE_HEADER -> onBindHeaderViewHolder(
                holder as SH,
                position,
                item as SectionHeader<T>,
                payloads
            )
            VIEW_TYPE_ITEM -> onBindItemViewHolder(holder as VH, position, item as T, payloads)
            else -> throw IllegalArgumentException("Unsupported ViewHolder viewType: $viewType")
        }
    }

    open fun onBindHeaderViewHolder(
        holder: SH,
        position: Int,
        header: SectionHeader<T>,
        payloads: MutableList<Any>
    ) = onBindHeaderViewHolder(holder, position, header)

    open fun onBindItemViewHolder(
        holder: VH,
        position: Int,
        item: T,
        payloads: MutableList<Any>
    ) = onBindItemViewHolder(holder, position, item)

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        val layoutManager = recyclerView.layoutManager
        if (layoutManager is GridLayoutManager) {
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int) =
                    when (val viewType = getItemViewType(position)) {
                        VIEW_TYPE_HEADER -> layoutManager.spanCount
                        VIEW_TYPE_ITEM -> 1
                        else -> throw IllegalArgumentException("Unsupported ViewHolder viewType: $viewType")
                    }
            }
        }
    }
}

data class SectionHeader<T>(
    val item: T
)
