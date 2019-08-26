package be.mickverm.widget.recyclerview.adapter

import androidx.recyclerview.widget.RecyclerView
import io.reactivex.functions.Consumer

abstract class RxBaseDiffAdapter<T, VH : RecyclerView.ViewHolder> : BaseDiffAdapter<T, VH>(),
    Consumer<List<T>> {

    final override fun accept(items: List<T>) = updateItems(items)
}
