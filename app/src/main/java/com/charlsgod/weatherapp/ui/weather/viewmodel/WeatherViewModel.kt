package com.charlsgod.weatherapp.ui.weather.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.charlsgod.weatherapp.model.WeatherResponse
import com.charlsgod.weatherapp.ui.weather.repository.WeatherRepository
import com.charlsgod.weatherapp.utils.InternetHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel
@Inject
constructor(
    private val repository: WeatherRepository,
    private val internetHelper: InternetHelper
) : ViewModel() {

    private val _response = MutableLiveData<WeatherResponse>()
    val weatherResponse: LiveData<WeatherResponse>
        get() = _response

    private val _message = MutableLiveData<String>()
    val message: LiveData<String>
        get() = _message

    fun getWeatherByCityAndDaysForecast(days: String, city: String) =

        viewModelScope.launch {
            if (internetHelper.isInternetAvailable()) {
                repository.getWeatherByCityAndDaysForecast(days, city).let { response ->

                    if (response.isSuccessful) {
                        _response.postValue(response.body())
                    } else {
                        Log.d("tag", "getWeather Error: ${response.code()}")
                    }
                }
            } else {
                _message.postValue("Ups! No internet connection")
                Log.e("tag", "No internet")
            }
        }


}













