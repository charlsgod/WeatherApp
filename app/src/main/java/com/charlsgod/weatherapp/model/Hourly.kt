package com.charlsgod.weatherapp.model


import com.google.gson.annotations.SerializedName

data class Hourly(
 @SerializedName("FeelsLikeF")
 val feelsLikeF: String,
 @SerializedName("tempF")
 val tempF: String,
 @SerializedName("time")
 val time: String,
 @SerializedName("weatherDesc")
 val weatherDesc: List<WeatherDesc>,
 @SerializedName("weatherIconUrl")
 val weatherIconUrl: List<WeatherIconUrl>
)