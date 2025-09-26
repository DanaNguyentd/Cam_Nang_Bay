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
fun MedicineInformationScreen(
    mainInformationScreenClicked : () -> Unit,
    generalInformationClicked    : () -> Unit,
    contactInformationClicked    : () -> Unit,
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
                                painterResource(id = R.drawable.medicine),
                                contentDescription = "Health Record",
                                contentScale = ContentScale.Fit, // Ensures image fits nicely
                                modifier = Modifier.size(60.dp) // control size of the image
                            )
                            Spacer(modifier = Modifier.width(20.dp)) // small gap between image and text
                            Text(
                                text = "Hồ Sơ Y Tế\nHealth Record",
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
                                contactInformationClicked,
                                R.drawable.phone
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Text(
                        text = "Blood Type / Nhóm máu",
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 15.sp,
                        color = Color(0xFF1565C0),
                        modifier = Modifier.weight(2f)
                    )
                    Text(
                        text = user.medicalHistory.bloodType,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                }
                CreateMedicalColumn("Medical Conditions/Bệnh Lý Nền", user.medicalHistory.diseasesList)
                CreateMedicalColumn("Allergies/Dị Ứng", user.medicalHistory.allergiesList)
                CreateMedicalColumn("Medications/Thuốc Đang Dùng", user.medicalHistory.medicationsList)
            }
        }
    }
}

@Composable
private fun CreateMedicalColumn(
    label: String,
    info: List<String>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium,
            fontSize = 15.sp,
            color = Color(0xFF1565C0)
        )

        if (info.isEmpty()) {
            Text(
                "No recorded/Không",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
        } else {
            info.forEach { item ->
                Text(
                    "• $item",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
