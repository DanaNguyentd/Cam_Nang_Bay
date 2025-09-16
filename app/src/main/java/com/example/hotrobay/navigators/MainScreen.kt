package com.example.hotrobay.navigators

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.hotrobay.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onSettingClicked: () -> Unit,
    onFlightInfoClicked : () -> Unit,
    onAtAirPortClicked : () -> Unit,
    inAirPlaneClicked : () -> Unit,
) {
    Scaffold (
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
                                painter = painterResource(id = R.drawable.logo),
                                contentDescription = "setting image",
                                contentScale = ContentScale.Fit, // Ensures image fits nicely
                                modifier = Modifier.size(100.dp) // control size of the image
                            )
                            Spacer(modifier = Modifier.width(20.dp)) // small gap between image and text
                            Text(
                                text = "Cẩm Nang Bay",
                                color = Color(0xFF6200EA),
                                fontSize = 28.sp,
                                fontWeight = FontWeight.SemiBold,
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFFF59D) // Yellow background
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally // ← centers children horizontally
            ){
                CreateRow(onFlightInfoClicked, "Thông tin chuyến bay", R.drawable.flight_ticket)
                Spacer(modifier = Modifier.width(20.dp))

                CreateRow(onAtAirPortClicked, "Tại Sân Bay", R.drawable.inairport)
                Spacer(modifier = Modifier.width(20.dp))

                CreateRow(inAirPlaneClicked, "Trong Máy Bay", R.drawable.inairplane)
                Spacer(modifier = Modifier.width(20.dp))

                CreateRow(onSettingClicked, "Cài Đặt Thông Tin", R.drawable.setting)
                Spacer(modifier = Modifier.width(20.dp))
            }
            // Footer
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(start = 8.dp, end = 8.dp, bottom = 30.dp)
            ) {
                androidx.compose.material3.Text(
                    text = "2025",
                    fontSize = 10.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(end = 4.dp)
                )
                androidx.compose.foundation.Image(
                    painter = painterResource(id = R.drawable.personalicon), // replace with your icon
                    contentDescription = "App Icon",
                    modifier = Modifier.size(15.dp)
                )
                androidx.compose.material3.Text(
                    text = "Design by Dana Nguyen",
                    fontSize = 10.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }
    }
}

@Composable
private fun CreateRow(
    clickedButton: () -> Unit,
    textInfo: String,
    imgID: Int
){
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
        ){
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
        ){
            Text(
                text = textInfo,
                color = Color(0xFF6200EA),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,  // <-- this centers the text horizontally inside Text box
                lineHeight = 30.sp, // <-- controls vertical spacing between lines
                modifier = Modifier.fillMaxWidth()  // important to let Text fill max width to center text properly
            )
        }
    }
}

