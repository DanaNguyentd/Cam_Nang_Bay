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
import com.example.hotrobay.navigators.CreateRow
import com.example.hotrobay.userdata.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainInformationScreen (
    generalInformationClicked:  () -> Unit,
    medicineInformationClicked: () -> Unit,
    contactInformationClicked:  () -> Unit,
    setupInformationClicked: () -> Unit,
    mainScreenClicked: () -> Unit,
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
                                painter = if (user.gender == "Male") {
                                    painterResource(id = R.drawable.man_icon)
                                } else {
                                    painterResource(id = R.drawable.female_icon)
                                },
                                contentDescription = "User gender",
                                contentScale = ContentScale.Fit, // Ensures image fits nicely
                                modifier = Modifier.size(80.dp) // control size of the image
                            )
                            Spacer(modifier = Modifier.width(20.dp)) // small gap between image and text
                            Text(
                                text = "Thông Tin Cá Nhân",
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally // ← centers children horizontally
            ) {
                CreateRow(generalInformationClicked, "Thông Tin Chính\nInformation", R.drawable.generalinfo)
                Spacer(modifier = Modifier.width(20.dp))

                CreateRow(medicineInformationClicked, "Hồ Sơ Y Tế\nMedicine", R.drawable.medicine)
                Spacer(modifier = Modifier.width(20.dp))

                CreateRow(contactInformationClicked, "Thông tin Liên Hệ\nContact", R.drawable.phone)
                Spacer(modifier = Modifier.width(20.dp))

                CreateRow(setupInformationClicked, "Sửa Thông Tin", R.drawable.settinginfo)
                Spacer(modifier = Modifier.width(20.dp))
            }
        }
    }
}
