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
import androidx.compose.material3.BottomAppBar
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
import com.example.hotrobay.userdata.nationalities

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeneralInformationScreen (
    mainInformationScreenClicked: () -> Unit,
    medicineInformationClicked: () -> Unit,
    contactInformationClicked:  () -> Unit,
    viewModel: UserViewModel = viewModel()
){
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
                                painterResource(id = R.drawable.generalinfo),
                                contentDescription = "General Information",
                                contentScale = ContentScale.Fit, // Ensures image fits nicely
                                modifier = Modifier.size(60.dp) // control size of the image
                            )
                            Spacer(modifier = Modifier.width(20.dp)) // small gap between image and text
                            Text(
                                text = "Thông tin chính\nGeneral Information",
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
                                if (user.gender == "Male") R.drawable.man_icon else R.drawable.female_icon
                            )
                        }

                        Box(modifier = Modifier.weight(1f)) {
                            CreateBnt(
                                medicineInformationClicked,
                                R.drawable.medicine
                            )
                        }

                        Box(modifier = Modifier.weight(1f)) {
                            CreateBnt(
                                contactInformationClicked,
                                R.drawable.phone
                            )
                        }


                    }
                }
            }
        }
    ) {  innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ){
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    CreateInfoRow("Surname\nHọ", user.lastName)
                }
                item {
                    CreateInfoRow("Last name\nTên", user.firstName)
                }
                item {
                    CreateInfoRow("Gender \n Giới tính", user.gender)
                }
                item {
                    CreateInfoRow("Birthday\nNgày sinh", user.birthday)
                }
                item {
                    CreateInfoRow("Nationality\nQuốc tịch", nationalities.firstOrNull { it.vn == user.nationality}?.en
                        ?: "Unknown")
                }
                item {
                    CreateInfoRow("Passport's ID\nSố hộ chiếu", user.passportId)
                }
                item {
                    CreateInfoRow("Expired day\nNgày hết hạn", user.passportExpiryDate)
                }
                item {
                    CreateInfoRow("Address\nĐịa chỉ", user.address)
                }
            }
        }
    }
}

@Composable
private fun CreateInfoRow(
    label: String,
    info: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp, end = 5.dp, top = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Left side of the row
        Text(
            text = label,
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.weight(2f),
            color = Color(0xFF1565C0)
        )
        Spacer(modifier = Modifier.width(5.dp))
        // Right side of the row
        Text(
            text = info,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(3f)
        )
    }
}

