package be.mickverm.widget.emptycontentview.extensions

import android.view.View

internal fun View.hide(gone: Boolean = true) {
    visibility = if (gone) View.GONE else View.INVISIBLE
}

internal fun View.show() {
    visibility = View.VISIBLE
}

internal fun View.visible(visible: Boolean = true, gone: Boolean = true) {
    visibility = when {
        visible -> View.VISIBLE
        gone -> View.GONE
        else -> View.INVISIBLE
    }
}