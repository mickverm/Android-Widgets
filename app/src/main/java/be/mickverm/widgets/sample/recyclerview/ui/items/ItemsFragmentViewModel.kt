package be.mickverm.widgets.sample.recyclerview.ui.items

import androidx.lifecycle.ViewModel
import be.mickverm.widgets.sample.recyclerview.data.repositories.ItemsRepository
import java.util.concurrent.TimeUnit

class ItemsFragmentViewModel(
        itemsRepository: ItemsRepository
) : ViewModel() {

    val randomItems = itemsRepository.observeItems(3, TimeUnit.SECONDS)
}
