package com.example.hotrobay.data

data class AirportInfo(
    val name: String,
    val country: String
)

val airportMap = mapOf(
    // Europe (Major International Airports)
    "CPH" to AirportInfo(name = "Copenhagen Airport", country = "Denmark"),
    "FRA" to AirportInfo(name = "Frankfurt Airport", country = "Germany"),
    "LHR" to AirportInfo(name = "London Heathrow Airport", country = "United Kingdom"),
    "CDG" to AirportInfo(name = "Charles de Gaulle Airport", country = "France"),
    "AMS" to AirportInfo(name = "Amsterdam Airport Schiphol", country = "Netherlands"),
    "ZRH" to AirportInfo(name = "Zurich Airport", country = "Switzerland"),
    "IST" to AirportInfo(name = "Istanbul Airport", country = "Turkey"),
    "MAD" to AirportInfo(name = "Adolfo Suárez Madrid–Barajas Airport", country = "Spain"),
    "MUC" to AirportInfo(name = "Munich Airport", country = "Germany"),
    "FCO" to AirportInfo(name = "Leonardo da Vinci–Fiumicino Airport", country = "Italy"),
    "DUB" to AirportInfo(name = "Dublin Airport", country = "Ireland"),
    "VIE" to AirportInfo(name = "Vienna International Airport", country = "Austria"),
    "LIS" to AirportInfo(name = "Lisbon Airport", country = "Portugal"),
    "OSL" to AirportInfo(name = "Oslo Airport, Gardermoen", country = "Norway"),
    "HEL" to AirportInfo(name = "Helsinki Airport", country = "Finland"),
    "BRU" to AirportInfo(name = "Brussels Airport", country = "Belgium"),
    "WAW" to AirportInfo(name = "Warsaw Chopin Airport", country = "Poland"),
    "ATH" to AirportInfo(name = "Athens International Airport", country = "Greece"),
    "PRG" to AirportInfo(name = "Václav Havel Airport Prague", country = "Czech Republic"),
    "BUD" to AirportInfo(name = "Budapest Ferenc Liszt International Airport", country = "Hungary"),
    "ARN" to AirportInfo(name = "Stockholm Arlanda Airport", country = "Sweden"),
    "GOT" to AirportInfo(name = "Gothenburg Landvetter Airport", country = "Sweden"),
    "MMX" to AirportInfo(name = "Malmö Airport", country = "Sweden"),

    // Asia (Major International Airports)
    "BKK" to AirportInfo(name = "Suvarnabhumi Airport", country = "Thailand"),
    "HKG" to AirportInfo(name = "Hong Kong International Airport", country = "Hong Kong"),
    "SIN" to AirportInfo(name = "Singapore Changi Airport", country = "Singapore"),
    "ICN" to AirportInfo(name = "Incheon International Airport", country = "South Korea"),
    "PEK" to AirportInfo(name = "Beijing Capital International Airport", country = "China"),
    "DEL" to AirportInfo(name = "Indira Gandhi International Airport", country = "India"),
    "KUL" to AirportInfo(name = "Kuala Lumpur International Airport", country = "Malaysia"),
    "DXB" to AirportInfo(name = "Dubai International Airport", country = "United Arab Emirates"),
    "NRT" to AirportInfo(name = "Narita International Airport", country = "Japan"),
    "HND" to AirportInfo(name = "Tokyo Haneda Airport", country = "Japan"),
    "PVG" to AirportInfo(name = "Shanghai Pudong International Airport", country = "China"),
    "JFK" to AirportInfo(name = "Jinnah International Airport", country = "Pakistan"),
    "BOM" to AirportInfo(name = "Chhatrapati Shivaji Maharaj International Airport", country = "India"),
    "CTU" to AirportInfo(name = "Chengdu Shuangliu International Airport", country = "China"),
    "MLE" to AirportInfo(name = "Velana International Airport", country = "Maldives"),
    "HAN" to AirportInfo(name = "Noi Bai International Airport", country = "Vietnam"),
    "SGN" to AirportInfo(name = "Tan Son Nhat International Airport", country = "Vietnam"),
    "MNL" to AirportInfo(name = "Ninoy Aquino International Airport", country = "Philippines")
)
