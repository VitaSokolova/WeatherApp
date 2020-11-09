package ru.surfstudio.weatherapp.ui.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.surfstudio.weatherapp.R
import ru.surfstudio.weatherapp.ui.city.WeatherInCityRoute
import ru.surfstudio.weatherapp.ui.list.recycler.CitiesListAdapter
import ru.surfstudio.weatherapp.ui.list.recycler.SpacesItemDecoration
import java.util.logging.Logger

private const val COLUMNS_NUMBER = 2

class CitiesListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: CitiesListViewModel

    private val adapter = CitiesListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities_list)

        viewModel = ViewModelProvider(this).get(CitiesListViewModel::class.java)
        findViews()
        initViews()
        initListeners()
        observeViewModel()
    }

    private fun findViews() {
        recyclerView = findViewById(R.id.cities_list_rv)
    }

    private fun initViews() {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this, COLUMNS_NUMBER)
        recyclerView.addItemDecoration(
            SpacesItemDecoration(
                resources.getDimensionPixelSize(R.dimen.cities_grid_padding),
                COLUMNS_NUMBER
            )
        )

        supportActionBar?.setTitle(R.string.cities_list_title)
    }

    private fun initListeners() {
        adapter.onItemClickListener = { city ->
            // todo: переход на экран с погодой в городе
            startActivity(WeatherInCityRoute(city).getIntent(this))
        }
    }

    private fun observeViewModel() {
        viewModel.citiesList.observe(this, { list ->
            adapter.setCities(list)
        })
    }
}
