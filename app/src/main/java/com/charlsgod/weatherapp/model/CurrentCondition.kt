package com.charlsgod.weatherapp.model


import com.google.gson.annotations.SerializedName

data class CurrentCondition(
 @SerializedName("FeelsLikeF")
 val feelsLikeF: String,
 @SerializedName("temp_F")
 val tempF: String,
 @SerializedName("weatherDesc")
 val weatherDesc: List<WeatherDesc>,
 @SerializedName("weatherIconUrl")
 val weatherIconUrl: List<WeatherIconUrl>,
 @SerializedName("windspeedKmph")
 val windspeedKmph: String
)