package com.charlsgod.weatherapp.data.local

import com.charlsgod.weatherapp.model.Location

class LocalData : LocalService {

    override suspend fun getLocations(): List<Location> {
        return generateFixesLocations()
    }

    private fun generateFixesLocations(): List<Location> {
        val locationsList = arrayListOf<Location>()
        val location1 = Location(city = "Los Angeles", state = "California", country = "USA")
        val location2 = Location(city = "London", state = "Ontario", country = "Canada")
        val location3 = Location(city = "Leon", state = "Guanajuato", country = "Mexico")
        val location4 =
            Location(city = "Caracas", state = "Capital District", country = "Venezuela")
        val location5 = Location(city = "New York", state = "New York", country = "USA")
        val location6 = Location(city = "Paris", state = "Île-de-France", country = "France")
        val location7 =
            Location(city = "Santiago", state = "Metropolitan Region", country = "Chile")
        val location8 = Location(city = "Dresden", state = "Saxony", country = "Germany")

        locationsList.add(location1)
        locationsList.add(location2)
        locationsList.add(location3)
        locationsList.add(location4)
        locationsList.add(location5)
        locationsList.add(location6)
        locationsList.add(location7)
        locationsList.add(location8)
        return locationsList
    }
}