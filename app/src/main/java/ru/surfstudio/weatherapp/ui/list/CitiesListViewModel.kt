package ru.surfstudio.weatherapp.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.surfstudio.weatherapp.domain.City

class CitiesListViewModel : ViewModel() {

    val citiesList: MutableLiveData<List<City>> by lazy {
        MutableLiveData(
            listOf(
                City(
                    id = "44418",
                    name = "Лондон",
                    country = "Великобритания",
                    colorHex = "#4ACFAC"
                ),
                City(id = "2487956", name = "Сан-Франциско", country = "США", colorHex = "#0F4C75"),
                City(id = "2122265", name = "Москва", country = "Россия", colorHex = "#3282B8"),
                City(
                    id = "455825",
                    name = "Рио-де-Жанейро",
                    country = "Бразилия",
                    colorHex = "#F9AF5F"
                )
            )
        )
    }
}