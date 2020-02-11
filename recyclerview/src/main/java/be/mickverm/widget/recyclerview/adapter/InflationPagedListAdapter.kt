package be.mickverm.widget.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import be.mickverm.widget.recyclerview.adapter.internal.ViewHolderBindingInflater

abstract class InflationPagedListAdapter<T, VH : RecyclerView.ViewHolder>(
    diffCallback: DiffUtil.ItemCallback<T>
) : PagedListAdapter<T, VH>(diffCallback) {

    @Deprecated(
        "Use ViewGroup.inflateLayout(layoutRes: Int) instead.",
        ReplaceWith("parent.inflateLayout(layoutRes)")
    )
    protected fun inflateLayout(@LayoutRes layoutRes: Int, parent: ViewGroup): View {
        return LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
    }

    fun ViewGroup.inflateLayout(@LayoutRes layoutRes: Int): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, false)
    }

    fun <VB : ViewBinding> ViewGroup.inflateViewBinding(
        bindingInflater: ViewHolderBindingInflater<VB>
    ): VB {
        return bindingInflater.invoke(LayoutInflater.from(context), this, false)
    }
}
