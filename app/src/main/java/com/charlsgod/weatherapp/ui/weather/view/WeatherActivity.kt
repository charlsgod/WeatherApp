package com.charlsgod.weatherapp.ui.weather.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.charlsgod.weatherapp.databinding.ActivityWeatherBinding
import com.charlsgod.weatherapp.ui.weather.viewmodel.WeatherViewModel
import com.charlsgod.weatherapp.utils.loadUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherActivity : AppCompatActivity() {

    private val forecastDays = "5"
    private lateinit var binding: ActivityWeatherBinding
    private lateinit var adapter: ForecastAdapter
    private val viewModel: WeatherViewModel by viewModels()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ForecastAdapter()
        binding.rvForecasts.adapter = adapter

        binding.ibBack.setOnClickListener {
            onBackPressed()
        }
        viewModel.weatherResponse.observe(this, { weather ->

            val data = weather.data
            binding.apply {
                val city = data.request[0].query
                val temp = "${data.currentCondition[0].tempF}°F"
                val description = data.currentCondition[0].weatherDesc[0].value
                val icon = data.currentCondition[0].weatherIconUrl[0].value
                val wind = "Wind ${data.currentCondition[0].windspeedKmph}Km/h"
                val forecast = data.weather

                adapter.forecasts = forecast
                adapter.notifyDataSetChanged()
                tvCityName.text = city
                tvTemperature.text = temp
                tvDescription.text = description
                ivIcon.loadUrl(icon)
                tvWind.text = wind

                progressBar.visibility = View.GONE
            }
        })

        viewModel.message.observe(this, { message ->
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        })
    }

    override fun onResume() {
        super.onResume()
        binding.progressBar.visibility = View.VISIBLE
        intent.getStringExtra("location")
            ?.let { viewModel.getWeatherByCityAndDaysForecast(forecastDays, it) }
    }
}