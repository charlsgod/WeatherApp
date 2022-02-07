package com.charlsgod.weatherapp.data.api

import com.charlsgod.weatherapp.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(".")
    suspend fun getWeatherByCityAndDaysForecast(
        @Query("num_of_days") days: String,
        @Query("q") city: String
    ): Response<WeatherResponse>

}