package be.mickverm.widgets.sample.recyclerview.ui

import androidx.lifecycle.ViewModel
import be.mickverm.widgets.sample.recyclerview.data.models.Item
import be.mickverm.widgets.sample.recyclerview.data.repositories.ItemsRepository
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class ItemsViewModel(
    itemsRepository: ItemsRepository
) : ViewModel() {

    val randomItems: Observable<List<Item>> = itemsRepository.observeItems(3, TimeUnit.SECONDS)

    val randomItemsSorted: Observable<List<Item>> =
        itemsRepository.observeItems(3, TimeUnit.SECONDS)
            .map { items ->
                items.sortedBy { item -> item.text[0] }
            }
}
