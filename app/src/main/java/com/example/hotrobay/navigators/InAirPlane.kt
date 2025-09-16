package com.example.hotrobay.navigators

import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotrobay.R
import java.util.Locale

val VietnameseInAirPlaneList = listOf(
    "Bạn có thể lấy giúp tôi nước được không?",
    "Bạn có thể lấy giúp tôi thêm chăn được không?",
    "Bạn có thể lấy giúp tôi trà được không?",
    "Tôi cảm thấy mệt",
    "Vui lòng chỉ giúp nhà vệ sinh",
    "Giúp tôi liên hệ với người nhà",
)

val EnglishInAirPlaneList = listOf(
    "Could you please get me some water?",
    "Could you please bring me an extra blanket?",
    "Could you please get me some tea?",
    "I feel sick.",
    "Please show me where the restroom is.",
    "Please help me contact my family."
)

val proteinMap = mapOf(
    "Beef" to "Bò",
    "Pork" to "Lợn",
    "Chicken" to "Gà",
    "Fisk" to "Cá"
)

val sideMap = mapOf(
    "Rice" to "Cơm",
    "Noodle" to "Mì",
    "Pasta" to "Mỳ Ý",
    "Potato" to "Khoai tây"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InAirPlane(
    mainScreenClicked : () -> Unit,
    flightInfoClicked : () -> Unit,
    atAirPortClicked : () -> Unit,
){
    var selectedProtein1 by remember { mutableStateOf("") }
    var selectedSide1 by remember { mutableStateOf("") }

    var selectedProtein2 by remember { mutableStateOf("") }
    var selectedSide2 by remember { mutableStateOf("") }

    val greenButtonColor = Color(0xFFB9F6CA)
    val blueButtonColor = Color(0xFF448AFF)

    val context = LocalContext.current

    var selectedIndex by remember { mutableStateOf<Int?>(null) }

    val tts = remember {
        var tempTts: TextToSpeech? = null
        tempTts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = tempTts?.setLanguage(Locale.ENGLISH)
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("TTS", "Language not supported or missing data")
                }
            } else {
                Log.e("TTS", "Initialization failed")
            }
        }
        tempTts
    }
    DisposableEffect(tts) {
        onDispose {
            tts.stop()
            tts.shutdown()
        }
    }

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Trên Máy Bay",
                            fontSize = 30.sp,
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
                            CreateBnt(flightInfoClicked,  R.drawable.flight_ticket)
                        }
                        Box(modifier = Modifier.weight(1f)) {
                            CreateBnt(atAirPortClicked, R.drawable.inairport)
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp) // optional spacing between items
        ) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxSize(),
                    colors = CardDefaults.cardColors(
                            containerColor = Color.Transparent // Transparent background
                    )
                ){
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ){
                        Text(
                            text = "Could you please tap the food options on my phone so I can understand what are available?",
                            modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp),
                            color = Color.Black,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Start
                        )
                    }
                }
            }
            item {
                OptionSection(
                    title = "Option 1 - Món 1",
                    proteins = listOf("Beef", "Pork", "Chicken", "Fisk"),
                    sides = listOf("Rice", "Noodle", "Pasta", "Potato"),
                    selectedProtein = selectedProtein1,
                    onProteinSelected = { selectedProtein1 = it },
                    selectedSide = selectedSide1,
                    onSideSelected = { selectedSide1 = it },
                    greenButtonColor = greenButtonColor,
                    blueButtonColor = blueButtonColor
                )
            }
            item {
                OptionSection(
                    title = "Option 2 - Món 2",
                    proteins = listOf("Beef", "Pork", "Chicken", "Fisk"),
                    sides = listOf("Rice", "Noodle", "Pasta", "Potato"),
                    selectedProtein = selectedProtein2,
                    onProteinSelected = { selectedProtein2 = it },
                    selectedSide = selectedSide2,
                    onSideSelected = { selectedSide2 = it },
                    greenButtonColor = greenButtonColor,
                    blueButtonColor = blueButtonColor
                )
            }
            item {
                Column(modifier = Modifier.fillMaxWidth()) {
                    DishRow(
                        title = "Món 1.",
                        proteinKey = selectedProtein1,
                        sideKey = selectedSide1,
                        proteinMap = proteinMap,
                        sideMap = sideMap,
                        tts = tts
                    )
                    DishRow(
                        title = "Món 2.",
                        proteinKey = selectedProtein2,
                        sideKey = selectedSide2,
                        proteinMap = proteinMap,
                        sideMap = sideMap,
                        tts = tts
                    )
                }
            }
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFFFF59D))
                        .padding(vertical = 5.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Trợ giúp tiếng anh",
                        fontSize = 20.sp,
                        color = Color(0xFF448AFF),
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }

            items(VietnameseInAirPlaneList.size) { index ->
                ColumnItem(
                    tts = tts,
                    index = index,
                    isSelected = selectedIndex == index,
                    onClick = { selectedIndex = index }
                )
            }
        }
    }
}

@Composable
fun DishRow(
    title: String,
    proteinKey: String,
    sideKey: String,
    proteinMap: Map<String, String>,
    sideMap: Map<String, String>,
    tts: TextToSpeech
) {
    // Build the display string safely
    val textStrVN = buildString {
        append(title)
        val proteinName = proteinMap[proteinKey] ?: proteinKey
        val sideName = sideMap[sideKey] ?: sideKey

        if (proteinName.isNotEmpty()) {
            append(" $proteinName")
        }

        if (sideName.isNotEmpty()) {
            if (proteinName.isNotEmpty()) append(" và ")
            append(sideName)
        }
    }
    val textStrEN = proteinKey + "and " + sideKey

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = textStrVN,
            color = Color.Black,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )

        IconButton(onClick = {
            tts.speak(
                textStrEN,
                TextToSpeech.QUEUE_FLUSH,
                null,
                "tts_utterance"
            )
        }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                contentDescription = "Speak"
            )
        }
    }
}

@Composable
fun OptionSection(
    title: String,
    proteins: List<String>,
    sides: List<String>,
    selectedProtein: String,
    onProteinSelected: (String) -> Unit,
    selectedSide: String,
    onSideSelected: (String) -> Unit,
    greenButtonColor: Color,
    blueButtonColor: Color
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Text(
            text = title,
            color = Color(0xFF6200EA),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            // Proteins Column
            Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                proteins.forEach { option ->
                    SelectableButton(
                        text = option,
                        isSelected = option == selectedProtein,
                        onClick = { onProteinSelected(option) },
                        greenButtonColor = greenButtonColor,
                        blueButtonColor = blueButtonColor
                    )
                }
            }
            // Sides Column
            Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                sides.forEach { option ->
                    SelectableButton(
                        text = option,
                        isSelected = option == selectedSide,
                        onClick = { onSideSelected(option) },
                        greenButtonColor = greenButtonColor,
                        blueButtonColor = blueButtonColor
                    )
                }
            }
        }
    }
}


@Composable
fun SelectableButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    greenButtonColor: Color,
    blueButtonColor: Color
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) greenButtonColor else blueButtonColor
        ),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier.width(140.dp)
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold
        )
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

@Composable
private fun ColumnItem(
    tts: TextToSpeech,
    index: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                indication = LocalIndication.current, // explicitly pass it
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onClick()
            },
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Color(0xFFFFEB3B) else Color.Transparent
        ),
        border = BorderStroke(2.dp, Color.Gray)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp, end = 10.dp, top = 5.dp)
        ) {
            Text(
                "🇻🇳 ${VietnameseInAirPlaneList[index]}",
                fontSize = 13.sp,
                color = Color(0xFF6200EA),
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "\uD83C\uDDFA\uD83C\uDDF8 ${EnglishInAirPlaneList[index]}",
                    fontSize = 8.sp,
                    color = Color(0xFF6200EA),
                    fontWeight = FontWeight.Medium
                )
                IconButton(
                    onClick = {
                        tts.speak(
                            EnglishInAirPlaneList[index],
                            TextToSpeech.QUEUE_FLUSH,
                            null,
                            "tts_utterance_$index"
                        )
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                        contentDescription = "Speak"
                    )
                }
            }
        }
    }
}