package com.example.hotrobay.navigators

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hotrobay.R
import com.example.hotrobay.data.Flight
import com.example.hotrobay.data.FlightViewModel
import com.example.hotrobay.data.airportMap
import java.lang.String.format
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen (
    mainScreenClicked: () -> Unit
) {
    val flightViewModel: FlightViewModel = viewModel()
    val context = LocalContext.current

    var itemCount by remember { mutableIntStateOf(1) } // start with 1 item

    var flightNumbers = remember { mutableStateListOf<String>() }
    var departureDate = remember { mutableStateListOf<String>() }
    var departureTime = remember { mutableStateListOf<String>() }
    var arrivalDate = remember { mutableStateListOf<String>() }
    var arrivalTime = remember { mutableStateListOf<String>() }
    var departureAirport = remember { mutableStateListOf<String>() }
    var departureIataCode = remember { mutableStateListOf<String>() }
    var departureCountry = remember { mutableStateListOf<String>() }
    var arrivalAirport = remember { mutableStateListOf<String>() }
    var arrivalIataCode = remember { mutableStateListOf<String>() }
    var arrivalCountry = remember { mutableStateListOf<String>() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp)
                                .height(IntrinsicSize.Min),  // Match height of tallest child
                            verticalAlignment = Alignment.CenterVertically // Center vertically
                        ){
                            Image(
                                painter = painterResource(id = R.drawable.setting),
                                contentDescription = "setting image",
                                contentScale = ContentScale.Fit, // Ensures image fits nicely
                                modifier = Modifier.size(40.dp) // control size of the image
                            )
                            Spacer(modifier = Modifier.width(20.dp)) // small gap between image and text
                            Text(
                                text = "Cài Đặt Thông Tin",
                                color = Color(0xFF6200EA),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold,
                            )
                        }
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
                    }
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(1.dp)
            ) {
            items(itemCount) { index ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Transparent // Transparent background
                    ),
                    border = BorderStroke(2.dp, Color.Gray) // 2.dp border
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(
                            text = "Chuyến số ${index + 1}",
                            color = Color(0xFF6200EA),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                        InputRow(
                            label = "Số Hiệu",
                            "^[A-Z]{1,3}\\d{1,4}[A-Z]?$",
                            onAddClick = { value ->
                                if (index < flightNumbers.size) {
                                    // Update existing
                                    flightNumbers[index] = value
                                } else {
                                    // Append new
                                    flightNumbers += value
                                }
                            }
                        )
                        Text(
                            text = "---KHỞI HÀNH---",
                            color = Color(0xFF6200EA),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.fillMaxWidth()
                        )
                        InputRow(
                            label = "Mã Sân bay",
                            "^[A-Z]{3}\$",
                            onAddClick = { value ->
                                if (index < departureIataCode.size) {
                                    // Update existing
                                    departureIataCode[index] = value
                                    departureAirport[index] =
                                        airportMap[departureIataCode[index]]?.name.toString()
                                    departureCountry[index] =
                                        airportMap[departureIataCode[index]]?.country.toString()
                                } else {
                                    // Append new
                                    departureIataCode += value
                                    departureAirport += airportMap[departureIataCode[index]]?.name.toString()
                                    departureCountry += airportMap[departureIataCode[index]]?.country.toString()
                                }
                            }
                        )
                        // Compute airportText dynamically from state
                        DetailRow(departureIataCode.getOrNull(index)?.let { code ->
                            airportMap[code]?.let { info -> "${info.name} - ${info.country}" }
                        } ?: "")
                        FormattedInputRow(
                            label = "Ngày",
                            format = "yyyy-MM-dd",
                            onAddClick = { value ->
                                if (index < departureDate.size) {
                                    // Update existing
                                    departureDate[index] = value
                                } else {
                                    // Append new
                                    departureDate += value
                                }
                            }
                        )
                        FormattedInputRow(
                            label = "Giờ",
                            format = "HH:mm",
                            onAddClick = { value ->
                                if (index < departureTime.size) {
                                    // Update existing
                                    departureTime[index] = value
                                } else {
                                    // Append new
                                    departureTime += value
                                }
                            }
                        )
                        Text(
                            text = "---HẠ CÁNH---",
                            color = Color(0xFF6200EA),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.fillMaxWidth()
                        )
                        InputRow(
                            label = "Mã Sân bay",
                            "^[A-Z]{3}\$",
                            onAddClick = { value ->
                                if (index < arrivalIataCode.size) {
                                    // Update existing
                                    arrivalIataCode[index] = value
                                    arrivalAirport[index] =
                                        airportMap[arrivalIataCode[index]]?.name.toString()
                                    arrivalCountry[index] =
                                        airportMap[arrivalIataCode[index]]?.country.toString()
                                } else {
                                    // Append new
                                    arrivalIataCode += value
                                    arrivalAirport += airportMap[arrivalIataCode[index]]?.name.toString()
                                    arrivalCountry += airportMap[arrivalIataCode[index]]?.country.toString()
                                }
                            }
                        )
                        // Compute airportText dynamically from state
                        DetailRow(arrivalIataCode.getOrNull(index)?.let { code ->
                            airportMap[code]?.let { info -> "${info.name} - ${info.country}" }
                        } ?: "")
                        FormattedInputRow(
                            label = "Ngày",
                            format = "yyyy-MM-dd",
                            onAddClick = { value ->
                                if (index < arrivalDate.size) {
                                    // Update existing
                                    arrivalDate[index] = value
                                } else {
                                    // Append new
                                    arrivalDate += value
                                }
                            }
                        )
                        FormattedInputRow(
                            label = "Giờ",
                            format = "HH:mm",
                            onAddClick = { value ->
                                if (index < arrivalTime.size) {
                                    // Update existing
                                    arrivalTime[index] = value
                                } else {
                                    // Append new
                                    arrivalTime += value
                                }
                            }
                        )

                    }
                }
            }
            // "Add more" button
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(3.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = { itemCount++ },
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = Color.Transparent, // transparent background
                            contentColor = Color(0xFF6200EA)     // text color
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            "➕ Thêm Nối Chuyến Bay Kế Tiếp",
                            color = Color(0xFF6200EA),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = {
                            // Build a list of new flights from your state lists
                            val newFlights = mutableListOf<Flight>()
                            for (i in 0 until flightNumbers.size) {
                                // Skip if any essential field is missing
                                val fNumber = flightNumbers.getOrNull(i)
                                val depDate = departureDate.getOrNull(i)
                                val depTime = departureTime.getOrNull(i)
                                val arrDate = arrivalDate.getOrNull(i)
                                val arrTime = arrivalTime.getOrNull(i)
                                val depAirport = departureAirport.getOrNull(i)
                                val depCode = departureIataCode.getOrNull(i)
                                val depCountry = departureCountry.getOrNull(i)
                                val arrAirport = arrivalAirport.getOrNull(i)
                                val arrCode = arrivalIataCode.getOrNull(i)
                                val arrCountry = arrivalCountry.getOrNull(i)

                                if (listOf(
                                        fNumber, depDate, depTime, arrDate, arrTime,
                                        depAirport, depCode, depCountry,
                                        arrAirport, arrCode, arrCountry
                                    ).any { it.isNullOrBlank() }
                                ) {
                                    // Skip this flight if any field is null/blank
                                    continue
                                }

                                // Combine date and time for ISO string
                                val depDateTime = "${depDate}T${depTime}"
                                val arrDateTime = "${arrDate}T${arrTime}"

                                newFlights += Flight.newBuilder()
                                    .setFlightNumber(fNumber!!)
                                    .setDepartureTime(depDateTime)
                                    .setArrivalTime(arrDateTime)
                                    .setDepartureAirport(depAirport!!)
                                    .setDepartureiataCode(depCode!!)
                                    .setDepartureCountry(depCountry!!)
                                    .setArrivalAirport(arrAirport!!)
                                    .setArrivaliataCode(arrCode!!)
                                    .setArrivalCountry(arrCountry!!)
                                    .build()
                            }

                            if (newFlights.isNotEmpty()) {
                                // Clear existing flights and save new ones
                                flightViewModel.clearFlights()
                                newFlights.forEach { flightViewModel.addFlights(it) }
                                // Show a Toast popup
                                Toast.makeText(context, "Flights saved successfully!", Toast.LENGTH_SHORT).show()
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF448AFF),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(1.dp, Color.Black),
                        modifier = Modifier.width(140.dp)
                    ) {
                        Text(
                            "Lưu",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun InputRow(
    label: String,
    regexStr: String, // Minimum number of characters required
    onAddClick: (String) -> Unit // Pass the text value when user clicks "Add"
) {
    var text by remember { mutableStateOf("") } // State moved inside InputRow
    var isValid by remember { mutableStateOf(true) } // For outline color
    var isFocused by remember { mutableStateOf(false) }

    val regexRef = regexStr.toRegex()
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 1.dp),
    ) {
        Text("•", fontSize = 20.sp, modifier = Modifier.padding(end = 8.dp))
        Text(label, fontSize = 10.sp, modifier = Modifier.padding(end = 8.dp))

        BasicTextField(
            value = text,
            onValueChange = { text = it.uppercase()}, // convert to uppercase
            textStyle = TextStyle(
                fontSize = 9.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default,
            modifier = Modifier
                .weight(5f)
                .padding(end = 8.dp)
                .widthIn(min = 100.dp)
                .height(48.dp)
                .background(Color.Transparent, shape = RoundedCornerShape(8.dp))
                .border(
                    width = 1.dp,
                    color = when {
                        !isValid -> Color.Red
                        isFocused -> Color.Green
                        else -> Color.Gray
                    },
                    shape = RoundedCornerShape(8.dp)
                )
                .onFocusChanged { focusState -> isFocused = focusState.isFocused },
            decorationBox = { innerTextField ->
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier.padding(start = 3.dp) // placeholder padding
                ) {
                    if (text.isEmpty()) {
                        Text(
                            text = "Enter text",
                            fontSize = 9.sp,
                            color = Color.Gray
                        )
                    }
                    innerTextField()
                }
            }
        )
        Button(
            onClick = {
                if (regexRef.matches(text)) {
                    isValid = true
                    onAddClick(text) // Call the save function
                } else {
                    isValid = false
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF448AFF),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, Color.Black),
            modifier = Modifier
                .width(90.dp)
        ) { Text("Thêm",
            fontSize = 8.sp) }
    }
}

@Composable
private fun FormattedInputRow(
    label: String,
    format: String, // e.g., "yyyy-MM-dd" or "HH:mm"
    onAddClick: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    var isValid by remember { mutableStateOf(false) } // For outline color

    val sdf = SimpleDateFormat(format, Locale.US).apply { isLenient = false }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth() // important to take full width
            .padding(vertical = 4.dp)
    ) {
        Text("•", fontSize = 20.sp, modifier = Modifier.padding(end = 8.dp))
        Text(label, fontSize = 10.sp, modifier = Modifier.padding(end = 8.dp))

        BasicTextField(
            value = text,
            onValueChange = { newText ->
                // Filter input: only digits, colon, and dash
                val filtered = newText.filter { it.isDigit() || it == ':' || it == '-' }
                text = filtered
                isError = try {
                    if (filtered.isNotEmpty()) sdf.parse(filtered)
                    false
                } catch (e: Exception) {
                    true
                }
            },
            textStyle = TextStyle(
                fontSize = 9.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .weight(5f)
                .widthIn(min = if (format.contains("H")) 100.dp else 120.dp)
                .height(48.dp)
                .background(Color.Transparent, shape = RoundedCornerShape(8.dp))
                .border(
                    width = 1.dp,
                    color = when {
                        isError -> Color.Red
                        isValid -> Color.Green
                        else -> Color.Gray
                    },
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 8.dp, vertical = 12.dp),
            decorationBox = { innerTextField ->
                Box(
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (text.isEmpty()) {
                        Text(
                            text = format,
                            fontSize = 7.sp,
                            color = Color.Gray
                        )
                    }
                    innerTextField()
                }
            }
        )

        Spacer(modifier = Modifier.weight(1f)) // takes all remaining space
        Button(
            onClick = {
                if (!isError && text.isNotBlank()) {
                    onAddClick(text)
                    isValid = true
                } else {
                    isValid = false
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF448AFF),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, Color.Black),
            modifier = Modifier.width(90.dp)
        ) {
            Text(
                "Thêm",
                fontSize = 8.sp,
            )
        }
    }
}

@Composable
private fun DetailRow(
    label: String
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 4.dp)) {
        Text(label, fontSize = 18.sp, modifier = Modifier.padding(end = 8.dp))
    }
}

@Composable
private fun CreateBnt(
    clickedFn: () -> Unit,
    imgID: Int
){
    Button(
        onClick = clickedFn,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,  // No background color
            contentColor = Color.Unspecified),   // Let image color be normal
        modifier = Modifier
            .fillMaxWidth() // Or .weight(1f) if inside Row
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                painter = painterResource(id = imgID),
                contentDescription = "Row 1 image",
                modifier = Modifier
                    .fillMaxWidth()    // Optionally fill width, or set width explicitly
                    .clip(RoundedCornerShape(16.dp))  // Adjust the corner radius as you like
            )
        }
    }
}