package com.example.hotrobay.data

import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

val airportTimezones = mapOf(
    "LHR" to ZoneId.of("Europe/London"),
    "CDG" to ZoneId.of("Europe/Paris"),
    "FRA" to ZoneId.of("Europe/Berlin"),
    "AMS" to ZoneId.of("Europe/Amsterdam"),
    "MAD" to ZoneId.of("Europe/Madrid"),
    "ROM" to ZoneId.of("Europe/Rome"),
    "ZRH" to ZoneId.of("Europe/Zurich"),
    "VIE" to ZoneId.of("Europe/Vienna"),
    "BRU" to ZoneId.of("Europe/Brussels"),
    "OSL" to ZoneId.of("Europe/Oslo"),
    "STO" to ZoneId.of("Europe/Stockholm"),
    "HEL" to ZoneId.of("Europe/Helsinki"),
    "ATH" to ZoneId.of("Europe/Athens"),
    "IST" to ZoneId.of("Europe/Istanbul"),
    "CPH" to ZoneId.of("Europe/Copenhagen"),
    "DXB" to ZoneId.of("Asia/Dubai"),
    "BKK" to ZoneId.of("Asia/Bangkok"),
    "SIN" to ZoneId.of("Asia/Singapore"),
    "HKG" to ZoneId.of("Asia/Hong_Kong"),
    "PEK" to ZoneId.of("Asia/Shanghai"),
    "ICN" to ZoneId.of("Asia/Seoul"),
    "KUL" to ZoneId.of("Asia/Kuala_Lumpur"),
    "DEL" to ZoneId.of("Asia/Kolkata"),
    "TPE" to ZoneId.of("Asia/Taipei"),
    "MNL" to ZoneId.of("Asia/Manila"),
    "JKT" to ZoneId.of("Asia/Jakarta"),
    "BOM" to ZoneId.of("Asia/Kolkata"), // India
    "KTM" to ZoneId.of("Asia/Kathmandu"),
    "DAC" to ZoneId.of("Asia/Dhaka"),
    "CMB" to ZoneId.of("Asia/Colombo"),
    "ISB" to ZoneId.of("Asia/Karachi"),
    "KHI" to ZoneId.of("Asia/Karachi"),
    "LHE" to ZoneId.of("Asia/Karachi"),
    "KHI" to ZoneId.of("Asia/Karachi"),
    "KTM" to ZoneId.of("Asia/Kathmandu"),
    "RGN" to ZoneId.of("Asia/Yangon"),
    "KUL" to ZoneId.of("Asia/Kuala_Lumpur"),
    "SIN" to ZoneId.of("Asia/Singapore"),
    "BKK" to ZoneId.of("Asia/Bangkok"),
    "HKG" to ZoneId.of("Asia/Hong_Kong"),
    "PEK" to ZoneId.of("Asia/Shanghai"),
    "ICN" to ZoneId.of("Asia/Seoul"),
    "KUL" to ZoneId.of("Asia/Kuala_Lumpur"),
    "DEL" to ZoneId.of("Asia/Kolkata"),
    "TPE" to ZoneId.of("Asia/Taipei"),
    "MNL" to ZoneId.of("Asia/Manila"),
    "JKT" to ZoneId.of("Asia/Jakarta"),
    "BOM" to ZoneId.of("Asia/Kolkata"),
    "KTM" to ZoneId.of("Asia/Kathmandu"),
    "DAC" to ZoneId.of("Asia/Dhaka"),
    "CMB" to ZoneId.of("Asia/Colombo"),
    "ISB" to ZoneId.of("Asia/Karachi"),
    "KHI" to ZoneId.of("Asia/Karachi"),
    "LHE" to ZoneId.of("Asia/Karachi"),
    "KHI" to ZoneId.of("Asia/Karachi"),
    "KTM" to ZoneId.of("Asia/Kathmandu"),
    "RGN" to ZoneId.of("Asia/Yangon"),
    "KUL" to ZoneId.of("Asia/Kuala_Lumpur"),
    "SIN" to ZoneId.of("Asia/Singapore"),
    "BKK" to ZoneId.of("Asia/Bangkok"),
    "HKG" to ZoneId.of("Asia/Hong_Kong"),
    "PEK" to ZoneId.of("Asia/Shanghai"),
    "ICN" to ZoneId.of("Asia/Seoul"),
    "KUL" to ZoneId.of("Asia/Kuala_Lumpur"),
    "DEL" to ZoneId.of("Asia/Kolkata"),
    "TPE" to ZoneId.of("Asia/Taipei"),
    "MNL" to ZoneId.of("Asia/Manila"),
    "JKT" to ZoneId.of("Asia/Jakarta"),
    "BOM" to ZoneId.of("Asia/Kolkata"),
    "KTM" to ZoneId.of("Asia/Kathmandu"),
    "DAC" to ZoneId.of("Asia/Dhaka"),
    "CMB" to ZoneId.of("Asia/Colombo"),
    "ISB" to ZoneId.of("Asia/Karachi"),
    "KHI" to ZoneId.of("Asia/Karachi"),
    "LHE" to ZoneId.of("Asia/Karachi"),
    "KHI" to ZoneId.of("Asia/Karachi"),
    "KTM" to ZoneId.of("Asia/Kathmandu"),
    "RGN" to ZoneId.of("Asia/Yangon"),
    "KUL" to ZoneId.of("Asia/Kuala_Lumpur"),
    "SIN" to ZoneId.of("Asia/Singapore"),
    "BKK" to ZoneId.of("Asia/Bangkok"),
    "HKG" to ZoneId.of("Asia/Hong_Kong"),
    "PEK" to ZoneId.of("Asia/Shanghai"),
    "ICN" to ZoneId.of("Asia/Seoul"),
    "KUL" to ZoneId.of("Asia/Kuala_Lumpur"),
    "DEL" to ZoneId.of("Asia/Kolkata"),
    "TPE" to ZoneId.of("Asia/Taipei"),
    "MNL" to ZoneId.of("Asia/Manila"),
    "JKT" to ZoneId.of("Asia/Jakarta"),
    "BOM" to ZoneId.of("Asia/Kolkata"),
    "KTM" to ZoneId.of("Asia/Kathmandu"),
    "DAC" to ZoneId.of("Asia/Dhaka"),
    "CMB" to ZoneId.of("Asia/Colombo"),
    "ISB" to ZoneId.of("Asia/Karachi"),
    "KHI" to ZoneId.of("Asia/Karachi"),
    "LHE" to ZoneId.of("Asia/Karachi"),
    "KHI" to ZoneId.of("Asia/Karachi"),
    "KTM" to ZoneId.of("Asia/Kathmandu"),
    "RGN" to ZoneId.of("Asia/Yangon"),
    "KUL" to ZoneId.of("Asia/Kuala_Lumpur"),
    "SIN" to ZoneId.of("Asia/Singapore"),
    "BKK" to ZoneId.of("Asia/Bangkok"),
    "HKG" to ZoneId.of("Asia/Hong_Kong"),
    "PEK" to ZoneId.of("Asia/Shanghai"),
    "ICN" to ZoneId.of("Asia/Seoul"),
    "KUL" to ZoneId.of("Asia/Kuala_Lumpur"),
    "DEL" to ZoneId.of("Asia/Kolkata"),
    "TPE" to ZoneId.of("Asia/Taipei"),
    "MNL" to ZoneId.of("Asia/Manila"),
    "JKT" to ZoneId.of("Asia/Jakarta"),
    "BOM" to ZoneId.of("Asia/Kolkata"),
    "KTM" to ZoneId.of("Asia/Kathmandu"),
    "DAC" to ZoneId.of("Asia/Dhaka"),
    "CMB" to ZoneId.of("Asia/Colombo"),
    "ISB" to ZoneId.of("Asia/Karachi"),
    "KHI" to ZoneId.of("Asia/Karachi"),
    "LHE" to ZoneId.of("Asia/Karachi"),
    "KHI" to ZoneId.of("Asia/Karachi"),
    "KTM" to ZoneId.of("Asia/Kathmandu"),
    "RGN" to ZoneId.of("Asia/Yangon"),
    "KUL" to ZoneId.of("Asia/Kuala_Lumpur"),
    "SIN" to ZoneId.of("Asia/Singapore"),
    "BKK" to ZoneId.of("Asia/Bangkok"),
    "HKG" to ZoneId.of("Asia/Hong_Kong"),
    "PEK" to ZoneId.of("Asia/Shanghai"),
    "ICN" to ZoneId.of("Asia/Seoul"),
    "KUL" to ZoneId.of("Asia/Kuala_Lumpur"),
    "DEL" to ZoneId.of("Asia/Kolkata"),
    "TPE" to ZoneId.of("Asia/Taipei"),
    "MNL" to ZoneId.of("Asia/Manila"),
    "JKT" to ZoneId.of("Asia/Jakarta"),
    "BOM" to ZoneId.of("Asia/Kolkata"),
    "KTM" to ZoneId.of("Asia/Kathmandu"),
    "DAC" to ZoneId.of("Asia/Dhaka"),
    "CMB" to ZoneId.of("Asia/Colombo"),
    "ISB" to ZoneId.of("Asia/Karachi"),
    "KHI" to ZoneId.of("Asia/Karachi"),
    "LHE" to ZoneId.of("Asia/Karachi"),
    "KHI" to ZoneId.of("Asia/Karachi"),
    "KTM" to ZoneId.of("Asia/Kathmandu"),
    "RGN" to ZoneId.of("Asia/Yangon"),
    "KUL" to ZoneId.of("Asia/Kuala_Lumpur"),
    "SIN" to ZoneId.of("Asia/Singapore"),
    "BKK" to ZoneId.of("Asia/Bangkok"),
    "HKG" to ZoneId.of("Asia/Hong_Kong"),
    "PEK" to ZoneId.of("Asia/Shanghai"),
    "ICN" to ZoneId.of("Asia/Seoul"),
    "KUL" to ZoneId.of("Asia/Kuala_Lumpur"),
    "HAN" to ZoneId.of("Asia/Ho_Chi_Minh"),
    "SGN" to ZoneId.of("Asia/Ho_Chi_Minh"),
    "DAD" to ZoneId.of("Asia/Ho_Chi_Minh")
)

fun getAirportTimezone(iataCode: String): ZoneId {
    return airportTimezones[iataCode] ?: ZoneId.systemDefault() // Default to system timezone if not found
}

fun timeBetweenTwoFlights(
    flightTime1: String,
    flight1iataCode: String,
    flightTime2: String,
    flight2iataCode: String
): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")

    return try {
        // Parse flight times
        val localDateTime1 = LocalDateTime.parse(flightTime1, formatter)
        val localDateTime2 = LocalDateTime.parse(flightTime2, formatter)

        // Get airport timezones dynamically
        val zoned1 = localDateTime1.atZone(getAirportTimezone(flight1iataCode))
        val zoned2 = localDateTime2.atZone(getAirportTimezone(flight2iataCode))

        // Calculate duration
        val duration = Duration.between(zoned1, zoned2).abs()
        val hours = duration.toHours()
        val minutes = duration.toMinutes() % 60

        "${hours}h ${minutes}m"
    } catch (e: Exception) {
        "Invalid time"
    }
}