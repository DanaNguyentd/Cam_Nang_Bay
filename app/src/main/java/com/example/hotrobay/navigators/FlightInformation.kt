package com.example.hotrobay.navigators

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.hotrobay.R
import com.example.hotrobay.data.Flight
import com.example.hotrobay.data.FlightViewModel
import com.example.hotrobay.data.airportTimezones
import com.example.hotrobay.data.timeBetweenTwoFlights
import java.time.LocalDate

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  FlightInformation(
    mainScreenClicked : () -> Unit,
    atAirPortClicked  : () -> Unit,
    inAirPlaneClicked : () -> Unit,
    viewModel: FlightViewModel = viewModel()
) {
    val flights by viewModel.flights.collectAsState()
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Thông Tin Chuyến Bay",
                            fontSize = 23.sp,
                            color = Color(0xFF448AFF),
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFFF59D) // Yellow background
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .fillMaxWidth(),
                containerColor = Color.Transparent, // ✅ transparent background
                tonalElevation = 0.dp // optional: remove shadow/elevation
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // The blue line at the top
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth(),
                        thickness = 4.dp,
                        color = Color(0xFF4285F4)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier.weight(1f)) {
                            CreateBnt(mainScreenClicked, R.drawable.mainpage)
                        }
                        Box(modifier = Modifier.weight(1f)) {
                            CreateBnt(atAirPortClicked, R.drawable.inairport)
                        }
                        Box(modifier = Modifier.weight(1f)) {
                            CreateBnt(inAirPlaneClicked, R.drawable.inairplane)
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(flights.size) { index ->
                CreateFlightCard(index + 1, flights[index], flights.size)
                if (flights.size > 1 && index < flights.size - 1) {
                    CreateTransit(flights[index].arrivalAirport, timeBetweenTwoFlights(flights[index].arrivalTime, flights[index].arrivaliataCode, flights[index+1].departureTime, flights[index+1].departureiataCode))
                }
            }

        }
    }
}

@Composable
fun CreateTransit(
    airport: String,
    waitTime: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent // Transparent background
        )
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ){
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ){
                Image(
                    painter = painterResource(id = R.drawable.transfericon),
                    contentDescription = "Transfer IMG",
                    modifier = Modifier
                        .fillMaxWidth()    // Optionally fill width, or set width explicitly
                        .clip(RoundedCornerShape(16.dp))  // Adjust the corner radius as you like
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                LongVerticalArrow(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF1565C0) // blue like in your image
                )
            }
            Box(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,   // center vertically
                    horizontalAlignment = Alignment.End         // align to right horizontally
                ){
                    Text(
                        text = buildAnnotatedString {
                            append("Sân bay: ")
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(airport)
                            }
                        },
                        color = Color.Black,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Text(
                        text = buildAnnotatedString {
                            append("• Chờ: ")
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(waitTime)
                            }
                        },
                        color = Color.Black,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
private fun CreateFlightCard(
    index: Int,
    flight: Flight,
    totalFlights: Int
){
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent // Transparent background
        ),
        border = BorderStroke(3.dp, Color.Gray) // 2.dp border
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.Start)
                    .padding(
                        start = 10.dp,
                        top = 10.dp,
                        bottom = 5.dp
                    ), // Position Box to the left,
            ) {
                Text(
                    text = if (totalFlights == 1) "✈\uFE0F Chuyến bay" else "✈\uFE0F Chuyến số $index",
                    color = Color(0xFF6200EA),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.Start)
                    .padding(top = 5.dp, bottom = 5.dp)
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("Số Hiệu: ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(flight.flightNumber)
                        }
                    },
                    color = Color.Red,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.Start)
                    .padding(bottom = 5.dp)
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("Thời gian bay: ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(timeBetweenTwoFlights(flight.arrivalTime, flight.arrivaliataCode, flight.departureTime, flight.departureiataCode))
                        }
                    },
                    color = Color.Black,
                    fontSize = 17.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            /////departure//////
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.Start)
                    .padding(top = 5.dp, start = 10.dp, bottom = 5.dp)
            ) {
                Text(
                    text = "KHỞI HÀNH",
                    color = Color(0xFF6200EA),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            LabelWithBoldValue("• Sân bay", flight.departureAirport)
            LabelWithBoldValue("• Nước", flight.departureCountry)
            LabelWithBoldValue("• Ngày", LocalDate.parse(flight.departureTime.split("T")[0]).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
            LabelWithBoldValue("• Giờ", flight.departureTime.split("T")[1])
            Box(
                modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End)
                .padding(top = 2.dp, end = 10.dp, bottom = 10.dp)
            ){
                FlightTimeVietnam(flight.departureTime, flight.departureiataCode)
            }
            /////arrival//////
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.Start)
                    .padding(top = 5.dp, start = 10.dp, bottom = 5.dp)
            ) {
                Text(
                    text = "HẠ CÁNH",
                    color = Color(0xFF6200EA),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            LabelWithBoldValue("• Sân bay", flight.arrivalAirport)
            LabelWithBoldValue("• Nước", flight.arrivalCountry)
            LabelWithBoldValue("• Ngày",  LocalDate.parse(flight.arrivalTime.split("T")[0]).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
            LabelWithBoldValue("• Giờ", flight.arrivalTime.split("T")[1])
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.End)
                    .padding(top = 2.dp, end = 10.dp, bottom = 10.dp)
            ){
                FlightTimeVietnam(flight.arrivalTime, flight.arrivaliataCode)
            }
        }
    }
}

@Composable
private fun LabelWithBoldValue(label: String, value: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.Start)
            .padding(top = 2.dp, start = 10.dp, bottom = 2.dp)
    ) {
        Text(
            text = buildAnnotatedString {
                append("$label: ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(value)
                }
            },
            color = Color.Black,
            fontSize = 17.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun FlightTimeVietnam(
    flightTime: String,
    flightIataCode: String,
) {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")
    val outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")

    val vietnamTime = try {
        // 1. Get the timezone for the airport, fallback to UTC if unknown
        val airportZoneId = ZoneId.of((airportTimezones[flightIataCode] ?: "UTC").toString())

        // 2. Parse the flight time as a local time in that zone
        val airportLocalTime = LocalDateTime.parse(flightTime, inputFormatter)
            .atZone(airportZoneId)

        // 3. Convert to Vietnam zone
        val vietnamZoned = airportLocalTime.withZoneSameInstant(ZoneId.of("Asia/Ho_Chi_Minh"))

        // 4. Format the output
        vietnamZoned.format(outputFormatter)
    } catch (e: Exception) {
        "Invalid time"
    }

    Text(
        text = "🕓 (VN): $vietnamTime",
        color = Color.Black,
        fontSize = 12.sp,
        textAlign = TextAlign.End,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun LongVerticalArrow(
    modifier: Modifier = Modifier,
    color: Color = Color.Blue,
    strokeWidth: Float = 8f,
    arrowHeadSize: Float = 30f
) {
    Canvas(modifier = modifier) {
        val startY = 0f
        val endY = size.height

        // Draw the vertical line
        drawLine(
            color = color,
            start = Offset(x = size.width / 2, y = startY),
            end = Offset(x = size.width / 2, y = endY - arrowHeadSize),
            strokeWidth = strokeWidth
        )

        // Draw the arrowhead (triangle)
        val arrowTip = Offset(x = size.width / 2, y = endY)
        val left = Offset(x = (size.width / 2) - arrowHeadSize / 2, y = endY - arrowHeadSize)
        val right = Offset(x = (size.width / 2) + arrowHeadSize / 2, y = endY - arrowHeadSize)

        drawPath(
            path = Path().apply {
                moveTo(arrowTip.x, arrowTip.y)
                lineTo(left.x, left.y)
                lineTo(right.x, right.y)
                close()
            },
            color = color
        )
    }
}
