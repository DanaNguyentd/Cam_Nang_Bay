package com.example.hotrobay.navigators

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun CreateRow(
    clickedButton: () -> Unit,
    textInfo: String,
    imgID: Int
) {
    Row( // include 2 boxes those are equal width
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp)
            .height(IntrinsicSize.Min)  // Match height of tallest child
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            contentAlignment = Alignment.Center  // Center content vertically & horizontally inside box
        ) {
            Button(
                onClick = clickedButton,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,  // No background color
                    contentColor = Color.Unspecified)    // Let image color be normal
            ) {
                Image(
                    painter = painterResource(id = imgID),
                    contentDescription = "Row 1 image",
                    modifier = Modifier
                        .height(100.dp)    // Set fixed height
                        .fillMaxWidth()    // Optionally fill width, or set width explicitly
                        .clip(RoundedCornerShape(16.dp))  // Adjust the corner radius as you like
                )
            }
        }
        Spacer(modifier = Modifier.width(10.dp))
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            contentAlignment = Alignment.Center  // Center content vertically & horizontally inside box
        ) {
            Text(
                text = textInfo,
                color = Color(0xFF6200EA),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,  // <-- this centers the text horizontally inside Text box
                lineHeight = 30.sp, // <-- controls vertical spacing between lines
                modifier = Modifier
                    .fillMaxWidth()  // important to let Text fill max width to center text properly
                    .padding(end = 10.dp)
            )
        }
    }
}

@Composable
fun CreateBnt(
    clickedFn: () -> Unit,
    imgID: Int
) {
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

// Create the arrow next or forward
@Composable
fun MomentaryIconButton(
    unselectedImage: Int,
    selectedImage: Int,
    contentDescription: String,
    modifier: Modifier = Modifier,
    stepDelay: Long = 100L, // Minimum value is 1L milliseconds.
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val pressedListener by rememberUpdatedState(onClick)

    LaunchedEffect(isPressed) {
        while (isPressed) {
            delay(stepDelay.coerceIn(1L, Long.MAX_VALUE))
            pressedListener()
        }
    }

    IconButton(
        modifier = modifier,
        onClick = onClick,
        interactionSource = interactionSource
    ) {
        Icon(
            painter = if (isPressed) painterResource(id = selectedImage) else painterResource(id = unselectedImage),
            contentDescription = contentDescription,
        )
    }
}