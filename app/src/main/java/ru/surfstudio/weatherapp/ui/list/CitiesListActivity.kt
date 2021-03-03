package ru.surfstudio.weatherapp.ui.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.surfstudio.weatherapp.R
import ru.surfstudio.weatherapp.domain.City
import ru.surfstudio.weatherapp.ui.city.WeatherInCityRoute
import ru.surfstudio.weatherapp.ui.common.ResourcesProviderImpl
import ru.surfstudio.weatherapp.ui.list.recycler.CitiesListAdapter
import ru.surfstudio.weatherapp.ui.list.recycler.SpacesItemDecoration

private const val COLUMNS_NUMBER = 2

class CitiesListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: CitiesListViewModel

    private val adapter = CitiesListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities_list)

        val resourcesProvider = ResourcesProviderImpl(applicationContext)
        viewModel = ViewModelProvider(this, CitiesListViewModelFactory(resourcesProvider)).get(
            CitiesListViewModel::class.java
        )

        findViews()
        initViews()
        initListeners()
        observeViewModel()
    }

    private fun findViews() {
        recyclerView = findViewById(R.id.cities_list_rv)
    }

    private fun initViews() {
        with(recyclerView) {
            adapter = this@CitiesListActivity.adapter
            layoutManager = GridLayoutManager(this@CitiesListActivity, COLUMNS_NUMBER)
            addItemDecoration(
                SpacesItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.cities_grid_padding),
                    COLUMNS_NUMBER
                )
            )
        }
        supportActionBar?.setTitle(R.string.cities_list_title)
    }

    private fun initListeners() {
        adapter.onItemClickListener = { navigateToCityForecastDetails(it) }
    }

    private fun observeViewModel() {
        viewModel.citiesList.observe(
            this,
            Observer<List<City>> { list -> adapter.setCities(list) }
        )
    }

    private fun navigateToCityForecastDetails(city: City) {
        startActivity(WeatherInCityRoute(city).getIntent(this))
    }
}
