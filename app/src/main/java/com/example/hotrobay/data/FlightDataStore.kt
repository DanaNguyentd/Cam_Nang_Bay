package com.example.hotrobay.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore

val Context.flightDataStore: DataStore<FlightList> by dataStore(
    fileName = "flights.pb",
    serializer = FlightListSerializer
)

