package ru.surfstudio.weatherapp.ui.list.recycler

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import ru.surfstudio.weatherapp.R
import ru.surfstudio.weatherapp.domain.City

class CitiesListAdapter : RecyclerView.Adapter<CitiesListAdapter.CityViewHolder>() {

    private var cities: List<City> = emptyList()

    var onItemClickListener: (city: City) -> Unit = {}

    inner class CityViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val cityTv: TextView = view.findViewById(R.id.city_vh_city_name_tv)
        val countryTv: TextView = view.findViewById(R.id.city_vh_country_tv)
        val backgroundView: CardView = view.findViewById(R.id.city_vh_container)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        // create a new view
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.city_view_holder,
            parent,
            false
        )
        return CityViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = cities[position]
        holder.cityTv.text = city.name
        holder.countryTv.text = city.country
        holder.backgroundView.setCardBackgroundColor(Color.parseColor(city.colorHex))
        holder.backgroundView.setOnClickListener {
            onItemClickListener(city)
        }
    }

    override fun getItemCount(): Int {
        return cities.size
    }


    fun setCities(cities: List<City>) {
        this.cities = cities
        notifyDataSetChanged()
    }

    fun clearCities() {
        cities = emptyList()
        notifyDataSetChanged()
    }
}