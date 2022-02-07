package com.charlsgod.weatherapp.ui.weather.repository

import com.charlsgod.weatherapp.data.api.ApiService
import javax.inject.Inject

class WeatherRepository
@Inject
constructor(private val apiService: ApiService) {
    suspend fun getWeatherByCityAndDaysForecast(days: String, city: String) =
        apiService.getWeatherByCityAndDaysForecast(days, city)
}