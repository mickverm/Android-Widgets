package be.mickverm.widgets.sample.recyclerview.data.repositories

import be.mickverm.widgets.sample.recyclerview.data.models.Item
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import kotlin.random.Random

private const val COUNT = 100

class ItemsRepository private constructor() {

    private val random = Random.Default

    companion object {
        @Volatile
        private var instance: ItemsRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: ItemsRepository().also {
                instance = it
            }
        }
    }

    fun observeItems(interval: Long, timeUnit: TimeUnit): Observable<List<Item>> {
        return Observable.interval(interval * 2, interval, timeUnit, Schedulers.computation())
            .map {
                randomItems().shuffled(random)
            }
    }

    private fun randomItems(): List<Item> {
        val items = mutableListOf<Item>()
        for (i in 0 until COUNT) {
            items.add(
                Item.random(random, i)
            )
        }
        return items
    }
}
