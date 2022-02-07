package com.charlsgod.weatherapp.ui.location.view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.charlsgod.weatherapp.R
import com.charlsgod.weatherapp.databinding.ItemLocationBinding
import com.charlsgod.weatherapp.model.Location
import com.charlsgod.weatherapp.utils.inflate

class LocationsAdapter(private val listener: (Location) -> Unit) :
    RecyclerView.Adapter<LocationsAdapter.ViewHolder>() {

    var locations: List<Location> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_location, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = locations.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val location = locations[position]
        holder.bind(location)
        holder.itemView.setOnClickListener { listener(location) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemLocationBinding.bind(view)
        fun bind(location: Location) = with(binding) {
            tvCity.text = location.city
            val stateCountry = location.state + ", " + location.country
            tvStateCountry.text = stateCountry
        }
    }
}