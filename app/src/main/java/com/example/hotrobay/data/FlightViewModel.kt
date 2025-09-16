package com.example.hotrobay.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotrobay.data.Flight
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class FlightViewModel(application: Application) : AndroidViewModel(application) {

    private val dataStore = application.flightDataStore

    val flights = dataStore.data
        .map { it.flightsList }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addFlights(flight: Flight) {
        viewModelScope.launch {
            dataStore.updateData { currentList ->
                currentList.toBuilder()
                    .addFlights(flight)
                    .build()
            }
        }
    }

    fun clearFlights() {
        viewModelScope.launch {
            dataStore.updateData {
                it.toBuilder().clearFlights().build()
            }
        }
    }
}