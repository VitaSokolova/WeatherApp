package ru.surfstudio.weatherapp.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.surfstudio.weatherapp.R
import ru.surfstudio.weatherapp.domain.City
import ru.surfstudio.weatherapp.ui.common.ResourcesProvider

class CitiesListViewModel(private val resourcesProvider: ResourcesProvider) : ViewModel() {

    // mocks for cities list, in real app you would load this data from server
    val citiesList: MutableLiveData<List<City>> by lazy {
        MutableLiveData(
            listOf(
                City(
                    id = "44418",
                    name = resourcesProvider.getString(R.string.mock_london),
                    country = resourcesProvider.getString(R.string.mock_great_britain),
                    colorHex = "#4ACFAC"
                ),
                City(
                    id = "2487956",
                    name = resourcesProvider.getString(R.string.mock_san_francisco),
                    country = resourcesProvider.getString(R.string.mock_usa),
                    colorHex = "#0F4C75"
                ),
                City(
                    id = "2122265",
                    name = resourcesProvider.getString(R.string.mock_moscow),
                    country = resourcesProvider.getString(R.string.mock_russia),
                    colorHex = "#3282B8"
                ),
                City(
                    id = "455825",
                    name = resourcesProvider.getString(R.string.mock_rio),
                    country = resourcesProvider.getString(R.string.mock_brazil),
                    colorHex = "#F9AF5F"
                )
            )
        )
    }
}