package be.mickverm.widget.recyclerview.utils

import androidx.recyclerview.widget.DiffUtil
import io.reactivex.FlowableTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.functions.BiFunction

object RxDiffUtil {

    @JvmStatic
    fun <T> calculateObservableDiff(
        itemDiffer: BiFunction<List<T>, List<T>, DiffUtil.Callback>
    ): ObservableTransformer<List<T>, Pair<List<T>, DiffUtil.DiffResult>> {
        val diffResult = DiffUtil.calculateDiff(itemDiffer.apply(emptyList(), emptyList()))
        val seedPair: Pair<List<T>, DiffUtil.DiffResult> = Pair(emptyList(), diffResult)
        return ObservableTransformer { upstream ->
            upstream
                .scan(seedPair, { oldPair, nextItems ->
                    val callback = itemDiffer.apply(oldPair.first, nextItems)
                    val result = DiffUtil.calculateDiff(callback, true)
                    Pair(nextItems, result)
                })
                .skip(1) // downstream shouldn't receive seedPair.
        }
    }

    @JvmStatic
    fun <T> calculateFlowableDiff(
        itemDiffer: BiFunction<List<T>, List<T>, DiffUtil.Callback>
    ): FlowableTransformer<List<T>, Pair<List<T>, DiffUtil.DiffResult>> {
        val diffResult = DiffUtil.calculateDiff(itemDiffer.apply(emptyList(), emptyList()))
        val seedPair: Pair<List<T>, DiffUtil.DiffResult> = Pair(emptyList(), diffResult)
        return FlowableTransformer { upstream ->
            upstream
                .scan(seedPair, { oldPair, nextItems ->
                    val callback = itemDiffer.apply(oldPair.first, nextItems)
                    val result = DiffUtil.calculateDiff(callback, true)
                    Pair(nextItems, result)
                })
                .skip(1) // downstream shouldn't receive seedPair.
        }
    }
}

inline fun <T> calculateObservableDiff(
    crossinline function: (List<T>, List<T>) -> DiffUtil.Callback
): ObservableTransformer<List<T>, Pair<List<T>, DiffUtil.DiffResult>> =
    RxDiffUtil.calculateObservableDiff(BiFunction { old, new -> function(old, new) })

inline fun <T> calculateFlowableDiff(
    crossinline function: (List<T>, List<T>) -> DiffUtil.Callback
): FlowableTransformer<List<T>, Pair<List<T>, DiffUtil.DiffResult>> =
    RxDiffUtil.calculateFlowableDiff(BiFunction { old, new -> function(old, new) })
