package com.charlsgod.weatherapp.data.local

import com.charlsgod.weatherapp.model.Location

interface LocalService {

    suspend fun getLocations(): List<Location>

}