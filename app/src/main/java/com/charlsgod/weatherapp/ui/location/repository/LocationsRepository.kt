package com.charlsgod.weatherapp.ui.location.repository

import com.charlsgod.weatherapp.data.local.LocalService
import javax.inject.Inject

class LocationsRepository
@Inject
constructor(private val localService: LocalService) {
    suspend fun getLocations() =
        localService.getLocations()
}