package ru.surfstudio.weatherapp.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.surfstudio.weatherapp.ui.common.ResourcesProvider

class CitiesListViewModelFactory(
    private val resourceProvider: ResourcesProvider
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CitiesListViewModel(resourceProvider) as T
    }
}