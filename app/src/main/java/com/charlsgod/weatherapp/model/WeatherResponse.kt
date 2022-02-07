package com.charlsgod.weatherapp.model


import com.google.gson.annotations.SerializedName

data class WeatherResponse(
 @SerializedName("data")
 val data: Data
)