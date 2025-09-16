package com.example.hotrobay.data

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object FlightListSerializer : Serializer<FlightList> {
    override val defaultValue: FlightList = FlightList.newBuilder()
        .addFlights(
            Flight.newBuilder()
                .setFlightNumber("TG951")
                .setDepartureTime("2025-09-22T14:25")
                .setArrivalTime("2025-09-23T06:00")
                .setDepartureAirport("Copenhagen")
                .setDepartureiataCode("CPH")
                .setDepartureCountry("Đan Mạch")
                .setArrivalAirport("Suvarnabhumi")
                .setArrivaliataCode("BKK")
                .setArrivalCountry("Thái Lan")
        )
        .addFlights(
            Flight.newBuilder()
                .setFlightNumber("TG560")
                .setDepartureTime("2025-09-23T07:45")
                .setArrivalTime("2025-09-23T09:35")
                .setDepartureAirport("Suvarnabhumi")
                .setDepartureiataCode("BKK")
                .setDepartureCountry("Thái Lan")
                .setArrivalAirport("Hà Nội")
                .setArrivaliataCode("HAN")
                .setArrivalCountry("Việt Nam")
        )
        .build()

    override suspend fun readFrom(input: InputStream): FlightList {
        try {
            return FlightList.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: FlightList, output: OutputStream) {
        t.writeTo(output)
    }
}