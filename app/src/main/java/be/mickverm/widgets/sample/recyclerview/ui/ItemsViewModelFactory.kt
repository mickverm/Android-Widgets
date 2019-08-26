package be.mickverm.widgets.sample.recyclerview.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import be.mickverm.widgets.sample.recyclerview.data.repositories.ItemsRepository

class ItemsViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) = ItemsViewModel(
        ItemsRepository.getInstance()
    ) as T
}
