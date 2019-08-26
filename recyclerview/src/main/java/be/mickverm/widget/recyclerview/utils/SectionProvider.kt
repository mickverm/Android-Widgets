package be.mickverm.widget.recyclerview.utils

import be.mickverm.widget.recyclerview.adapter.SectionHeader
import io.reactivex.FlowableTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.functions.BiFunction

object SectionProvider {

    fun <T> insertSections(
        items: List<T>,
        insert: (previous: T, current: T) -> Boolean
    ): List<Any> {
        val itemsWithSections = mutableListOf<Any>()
        var previous: T? = null
        for (item in items) {
            if (previous == null || insert(previous, item)) {
                itemsWithSections.add(SectionHeader(item))
            }
            itemsWithSections += item as Any
            previous = item
        }
        return itemsWithSections
    }

    @JvmStatic
    fun <T> insertObservableSection(
        insert: BiFunction<T, T, Boolean>
    ): ObservableTransformer<List<T>, List<Any>> = ObservableTransformer { upstream ->
        upstream.map { items ->
            insertSections(items) { previous, current ->
                insert.apply(previous, current)
            }
        }
    }

    @JvmStatic
    fun <T> insertFlowableSection(
        insert: BiFunction<T, T, Boolean>
    ): FlowableTransformer<List<T>, List<Any>> = FlowableTransformer { upstream ->
        upstream.map { items ->
            insertSections(items) { previous, current ->
                insert.apply(previous, current)
            }
        }
    }
}

inline fun <T> insertObservableSections(
    crossinline function: (previous: T, current: T) -> Boolean
): ObservableTransformer<List<T>, List<Any>> = ObservableTransformer { upstream ->
    upstream.map { items ->
        SectionProvider.insertSections(items) { previous, current ->
            function(previous, current)
        }
    }
}

inline fun <T> insertFlowableSections(
    crossinline function: (previous: T, current: T) -> Boolean
): FlowableTransformer<List<T>, List<Any>> = FlowableTransformer { upstream ->
    upstream.map { items ->
        SectionProvider.insertSections(items) { previous, current ->
            function(previous, current)
        }
    }
}
