package be.mickverm.widget.recyclerview.adapter.internal

import android.view.LayoutInflater
import android.view.ViewGroup

internal typealias ViewHolderBindingInflater<VB> = (
    layoutInflater: LayoutInflater,
    parent: ViewGroup,
    attachToParent: Boolean
) -> VB
