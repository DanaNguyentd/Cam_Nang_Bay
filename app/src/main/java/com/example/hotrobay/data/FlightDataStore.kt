package com.example.hotrobay.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.example.hotrobay.data.FlightList

val Context.flightDataStore: DataStore<FlightList> by dataStore(
    fileName = "flights.pb",
    serializer = FlightListSerializer
)

