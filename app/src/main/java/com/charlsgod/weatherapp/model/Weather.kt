package com.charlsgod.weatherapp.model


import com.google.gson.annotations.SerializedName

data class Weather(
 @SerializedName("avgtempF")
 val avgtempF: String,
 @SerializedName("date")
 val date: String,
 @SerializedName("hourly")
 val hourly: List<Hourly>,
 @SerializedName("maxtempF")
 val maxtempF: String,
 @SerializedName("mintempF")
 val mintempF: String
)