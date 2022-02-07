package com.charlsgod.weatherapp.ui.location.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.charlsgod.weatherapp.databinding.ActivityLocationsBinding
import com.charlsgod.weatherapp.ui.location.viewmodel.LocationsViewModel
import com.charlsgod.weatherapp.ui.weather.view.WeatherActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LocationsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLocationsBinding
    private lateinit var adapter: LocationsAdapter
    private val viewModel: LocationsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = LocationsAdapter { location ->
            val intent = Intent(this@LocationsActivity, WeatherActivity::class.java)
            intent.putExtra(
                "location",
                location.city + " " + location.state + " " + location.country
            )
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
        binding.rvLocations.adapter = adapter
        viewModel.locations.observe(this, {
            adapter.locations = it
        })
    }

}