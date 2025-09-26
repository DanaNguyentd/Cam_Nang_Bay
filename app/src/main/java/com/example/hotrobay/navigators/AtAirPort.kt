package com.example.hotrobay.navigators

import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material3.BottomAppBar
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotrobay.R
import java.util.Locale

val VietnameseAirPortList = listOf(
    "Tôi cần giúp đỡ",
    "Tôi cần giúp kết nối wifi",
    "Tôi cần tìm cửa ra máy bay",
    "Tôi cảm thấy mệt",
    "Vui lòng chỉ giúp nhà vệ sinh",
    "Tôi bị thất lạc hành lý",
    "Giúp tôi liên hệ với người nhà",
    "Gọi giúp số: +46 xxx.xxx.xxx",
)

val EnglishAirPortList = listOf(
    "I need help",
    "I need help connecting to Wi-Fi",
    "I need to find the boarding gate",
    "I feel tired",
    "Please show me the restroom",
    "I lost my luggage",
    "Help me contact my family",
    "Call this number for help:\n+46 xxx.xxx.xxx"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AtAirPort(
    mainScreenClicked : () -> Unit,
    flightInfoClicked : () -> Unit,
    inAirPlaneClicked : () -> Unit,
) {
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
                            "Tại Sân Bay-Nói Tiếng Anh",
                            fontSize = 20.sp,
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
                                .padding(horizontal = 10.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(modifier = Modifier.weight(1f)) {
                                CreateBnt(mainScreenClicked, R.drawable.mainpage)
                            }
                            Box(modifier = Modifier.weight(1f)) {
                                CreateBnt(flightInfoClicked, R.drawable.flight_ticket)
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
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(VietnameseAirPortList.size) { index ->
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
                .padding(start = 8.dp, end = 8.dp, top = 5.dp)
        ) {
            Text(
                "🇻🇳 ${VietnameseAirPortList[index]}",
                fontSize = 18.sp,
                color = Color(0xFF6200EA),
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "\uD83C\uDDFA\uD83C\uDDF8 ${EnglishAirPortList[index]}",
                    fontSize = 12.sp,
                    color = Color(0xFF6200EA),
                    fontWeight = FontWeight.Medium
                )
                IconButton(
                    onClick = {
                        tts.speak(
                            EnglishAirPortList[index],
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
