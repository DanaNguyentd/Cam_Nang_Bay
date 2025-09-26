package com.example.hotrobay.navigators.userNavigators

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hotrobay.R
import com.example.hotrobay.navigators.CreateBnt
import com.example.hotrobay.userdata.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactInformationScreen(
    mainInformationScreenClicked : () -> Unit,
    generalInformationClicked    : () -> Unit,
    medicineInformationClicked   : () -> Unit,
    viewModel: UserViewModel = viewModel()
) {
    val user by viewModel.user.collectAsState()

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
                                painterResource(id = R.drawable.phone),
                                contentDescription = "Contact",
                                contentScale = ContentScale.Fit, // Ensures image fits nicely
                                modifier = Modifier.size(60.dp) // control size of the image
                            )
                            Spacer(modifier = Modifier.width(20.dp)) // small gap between image and text
                            Text(
                                text = "Thông Tin Liên Hệ\nContact",
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
                            CreateBnt(
                                mainInformationScreenClicked,
                                if (user.gender == "Man") R.drawable.man_icon else R.drawable.female_icon
                            )
                        }

                        Box(modifier = Modifier.weight(1f)) {
                            CreateBnt(
                                generalInformationClicked,
                                R.drawable.generalinfo
                            )
                        }

                        Box(modifier = Modifier.weight(1f)) {
                            CreateBnt(
                                medicineInformationClicked,
                                R.drawable.medicine
                            )
                        }


                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(user.contactNumbersList) { item ->
                    val parts = item.split("--")
                    if (parts.size >= 3) {
                        val (label, number, type) = parts
                        CreateContactColumn(label, number, type)
                    }
                }
            }
        }
    }
}

@Composable
private fun CreateContactColumn(
    label: String,
    nameInfo: String,
    phoneInfo: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium,
            fontSize = 16.sp,
            color = Color(0xFF1565C0)
        )
        Text(
            nameInfo,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 5.dp)
        )
        Text(
            "Phone $phoneInfo",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 5.dp)
        )
    }
}