package be.mickverm.widget.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class InflationAdapter<VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    fun inflateLayout(@LayoutRes layoutRes: Int, parent: ViewGroup): View =
        LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
}
