package com.charlsgod.weatherapp.model


import com.google.gson.annotations.SerializedName

data class Request(
 @SerializedName("query")
 val query: String
)