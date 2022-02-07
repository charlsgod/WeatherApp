package com.charlsgod.weatherapp.ui.location.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.charlsgod.weatherapp.model.Location
import com.charlsgod.weatherapp.ui.location.repository.LocationsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel
@Inject
constructor(private val repository: LocationsRepository) : ViewModel() {

    private val _locations = MutableLiveData<List<Location>>()
    val locations: LiveData<List<Location>>
        get() = _locations

    init {
        getLocations()
    }

    private fun getLocations() =
        viewModelScope.launch {
            repository.getLocations().let { locations ->

                if (locations.isNotEmpty()) {
                    _locations.postValue(locations)
                } else {
                    Log.d("tag", "getLocations Error: locations empty")
                }
            }
        }


}













