package com.example.hotrobay.navigators.userNavigators

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hotrobay.R
import com.example.hotrobay.navigators.CreateBnt
import com.example.hotrobay.navigators.MomentaryIconButton
import com.example.hotrobay.userdata.UserViewModel
import com.example.hotrobay.userdata.genders
import com.example.hotrobay.userdata.nationalities
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupInformationScreen(
    mainInformationScreenClicked: () -> Unit,
    viewModel: UserViewModel = viewModel()
){
    val user by viewModel.user.collectAsState()

    val options = TranslatorOptions.Builder()
        .setSourceLanguage(TranslateLanguage.VIETNAMESE) // Vietnamese
        .setTargetLanguage(TranslateLanguage.ENGLISH) // English
        .build()

    val translator = Translation.getClient(options)

    // Download model for offline use
    translator.downloadModelIfNeeded()
        .addOnSuccessListener {
            // Model ready offline
        }
        .addOnFailureListener { e ->
            Log.e("Translator", "Failed to download model: $e")
        }

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
                                painterResource(id = R.drawable.settinginfo),
                                contentDescription = "Setting",
                                contentScale = ContentScale.Fit, // Ensures image fits nicely
                                modifier = Modifier.size(60.dp) // control size of the image
                            )
                            Spacer(modifier = Modifier.width(20.dp)) // small gap between image and text
                            Text(
                                text = "Sửa Thông Tin / Setting",
                                color = Color(0xFF6200EA),
                                fontSize = 23.sp,
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
                Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp)
            ) {
                item {
                    ExpandableHeaderCard("Thông Tin Cá Nhân") {
                        SetUpInformation(viewModel)
                    }
                }

                item {
                    ExpandableHeaderCard("Hồ Sơ Y Tế") {
                        SetUpMedication(viewModel, translator)
                    }
                }

                item {
                    ExpandableHeaderCard("Thông Tin Liên Lạc") {
                        SetUpContact(viewModel, translator)
                    }
                }
            }
        }
    }
}

@Composable
fun SetUpContact(
    viewModel: UserViewModel = viewModel(),
    translator: com.google.mlkit.nl.translate.Translator
) {
    val user by viewModel.user.collectAsState()
    val context = LocalContext.current

    var contacts by remember {mutableStateOf(user.contactNumbersList)}

    Column (
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        contacts.forEachIndexed { index, contact ->
            UpdateContact(
                contact,
                onValueRemove = {
                    val newList = contacts.toMutableList().apply { removeAt(index) }
                    contacts = newList
                                },
                onRemove = {
                    viewModel.removeContactNumberAt(index) // ✅ use index-based function
                    Toast.makeText(context, "Đã xóa liên hệ thành công", Toast.LENGTH_SHORT).show()
                           },
                onValueUpdate = { changedContact ->
                    val newList = contacts.toMutableList()
                    newList[index] = changedContact
                    contacts = newList
                                },
                onUpdate = { newContact ->
                    viewModel.updateContactNumberAt(index, newContact) // ✅ use index-based function
                    Toast.makeText(context, "Đã thay đổi liên hệ thành công", Toast.LENGTH_SHORT).show()
                           },
                translator = translator,
                transform = { it ->
                    it.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
                }
            )
        }
        // Add button
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = {
                    viewModel.addContactNumber(" / -- -- ")
                    val updatedList = contacts.toMutableList()
                    updatedList.add(" / -- -- ")
                    contacts = updatedList
                },
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Color.Transparent, // transparent background
                    contentColor = Color(0xFF6200EA)     // text color
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "➕",
                    color = Color(0xFF6200EA),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun UpdateContact (
    contact: String,
    onValueRemove: () -> Unit,
    onRemove: () -> Unit,
    onValueUpdate: (String) -> Unit,
    onUpdate: (String) -> Unit,
    transform: (String) -> String = { it }, // optional transformation (e.g. uppercase)
    translator: com.google.mlkit.nl.translate.Translator
) {
    Log.e("UpdateContact", "Current Contact: $contact")
    var labelvn by remember { mutableStateOf(contact.split("--")[0].split('/')[0])}
    var labelen by remember { mutableStateOf(contact.split("--")[0].split('/')[1])}
    // When labelvn changes, trigger translation
    LaunchedEffect(labelvn) {
        if (labelvn.isNotBlank()) {
            translator.translate(labelvn)
                .addOnSuccessListener { result ->
                    labelen = result
                }
                .addOnFailureListener {
                    labelen = ""
                }
        }
    }
    var name by remember { mutableStateOf(contact.split("--")[1])}
    var phone by remember { mutableStateOf(contact.split("--")[2])}

    val heightSize = 40.dp
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFFE1F5FE),
                shape = RoundedCornerShape(16.dp)
            )// set your desired background color,
            .padding(horizontal = 8.dp, vertical = 6.dp), // more space inside
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        // Relationship
        LabeledTextField(
            label = "Mối Quan Hệ",
            value = labelvn,
            onValueChange = { labelvn = transform(it) },
            height = heightSize
        )
        // Name
        LabeledTextField(
            label = "Tên",
            value = name,
            onValueChange = { name = transform(it) },
            height = heightSize
        )
        //Phone number
        LabeledTextField(
            label = "Điện Thoại",
            value = phone,
            onValueChange = { phone = transform(it) },
            height = heightSize
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            //Remove Button
            Button(
                onClick = {
                    onRemove()
                    onValueRemove()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF448AFF),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.dp, Color.Black),
                modifier = Modifier
                    .height(40.dp)
            ) {
                Text("Xóa Liên Lạc", fontSize = 15.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.weight(1f))
            //Change Button
            Button(
                onClick = {
                    if (labelvn!="" && name!="" && phone.length >= 10) {
                        val contactString = "$labelvn/$labelen--$name--$phone"
                        onValueUpdate(contactString)
                        onUpdate(contactString)
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF448AFF),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.dp, Color.Black),
                modifier = Modifier
                    .height(40.dp)
            ) {
                Text("Đổi Thông Tin", fontSize = 15.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun LabeledTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    height: Dp,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.weight(3f),
            contentAlignment = Alignment.TopStart
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.titleMedium.copy(color = Color(0xFF4285F4)),
                fontSize = 18.sp
            )
        }

        Box(
            modifier = Modifier
                .height(height)
                .weight(5f)
                .border(2.dp, Color.Gray, shape = MaterialTheme.shapes.small)
                .padding(horizontal = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )

            if (value.isEmpty()) {
                Text("", fontSize = 18.sp, color = Color.Black)
            }
        }
    }
}

@Composable
fun SetUpMedication (
    viewModel: UserViewModel = viewModel(),
    translator: com.google.mlkit.nl.translate.Translator
) {
    val user by viewModel.user.collectAsState()
    val context = LocalContext.current

    var bloodType by remember { mutableStateOf(user.medicalHistory.bloodType) }
    var medications by remember { mutableStateOf(user.medicalHistory.medicationsList) }
    var diseases by remember { mutableStateOf(user.medicalHistory.diseasesList) }
    var allergies by remember { mutableStateOf(user.medicalHistory.allergiesList) }

    Column (
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        // bloodType
        StringFieldRow(
            label = "Nhóm Máu",
            value = bloodType,
            onValueChange = { bloodType = it },
            onSave = {
                viewModel.updateBloodType(bloodType)
                Toast.makeText(context, "Đã thay đổi thành công", Toast.LENGTH_SHORT).show()
            },
            transform = { it ->
                it.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
            }
        )

        //Diseases
        UpdateList(
            label = "Bệnh Lý Nền",
            medications = diseases,
            onValueChange = { diseases = it },
            onRemove = { index ->
                viewModel.removeDiseaseAt(index) // ✅ use index-based function
                Toast.makeText(context, "Đã xóa thành công", Toast.LENGTH_SHORT).show()
            },
            onUpdate = { index, newDisease ->
                viewModel.updateDiseaseAt(index, newDisease) // ✅ use index-based function
                Toast.makeText(context, "Đã thay đổi thành công", Toast.LENGTH_SHORT).show()
            },
            onAdd = {
                viewModel.addDisease("/")
            },
            translator
        )

        //Allergy
        UpdateList(
            label = "Dị Ứng",
            medications = allergies,
            onValueChange = { allergies = it },
            onRemove = { index ->
                viewModel.removeAllergyAt(index) // ✅ use index-based function
                Toast.makeText(context, "Đã thay đổi thành công", Toast.LENGTH_SHORT).show()
            },
            onUpdate = { index, newDisease ->
                viewModel.updateAllergyAt(index, newDisease) // ✅ use index-based function
                Toast.makeText(context, "Đã thay đổi thành công", Toast.LENGTH_SHORT).show()
            },
            onAdd = {
                viewModel.addAllergy("/")
            },
            translator
        )

        // Medication
        UpdateList(
            label = "Thuốc Đang sử Dụng",
            medications = medications,
            onValueChange = { medications = it },
            onRemove = { index ->
                viewModel.removeMedicationAt(index) // ✅ use index-based function
                Toast.makeText(context, "Đã thay đổi thành công", Toast.LENGTH_SHORT).show()
            },
            onUpdate = { index, newMedication ->
                viewModel.updateMedicationAt(index, newMedication) // ✅ use index-based function
                Toast.makeText(context, "Đã thay đổi thành công", Toast.LENGTH_SHORT).show()
            },
            onAdd = {
                viewModel.addMedication("/")
            },
            translator = translator
        )
    }
}

@Composable
fun UpdateList(
    label: String,
    medications: List<String>,
    onValueChange: (List<String>) -> Unit,
    onRemove: (Int) -> Unit,
    onUpdate: (Int, String) -> Unit,
    onAdd: () -> Unit,
    translator: com.google.mlkit.nl.translate.Translator
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFFE1F5FE),
                shape = RoundedCornerShape(16.dp)
            )// set your desired background color,
            .padding(horizontal = 8.dp, vertical = 6.dp), // more space inside
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium.copy(color = Color(0xFF4285F4)),
            fontSize = 18.sp
        )

        // List of medications
        medications.forEachIndexed { index, med ->
            var vnText by remember { mutableStateOf(med.split('/')[1])}
            var enText by remember { mutableStateOf(med.split('/')[0])}
            // When vnText changes, trigger translation
            LaunchedEffect(vnText) {
                if (vnText.isNotBlank()) {
                    translator.translate(vnText)
                        .addOnSuccessListener { result ->
                            enText = result
                        }
                        .addOnFailureListener {
                            enText = ""
                        }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                // Red "X" delete button
                TextButton(
                    onClick = {
                        onRemove(index)
                        val newList = medications.toMutableList().apply { removeAt(index) }
                        onValueChange(newList)
                    },
                    modifier = Modifier
                        .size(40.dp)
                        .weight(1f), // optional: make it square
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = Color.Red
                    )
                ) {
                    Text("X", fontSize = 18.sp, fontWeight = FontWeight.ExtraBold)
                }
                //Show info in VN
                BasicTextField(
                    value = vnText,
                    onValueChange = { newText ->
                        vnText = newText
                    },
                    textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
                    modifier = Modifier
                        .border(2.dp, Color.Gray, shape = MaterialTheme.shapes.small)
                        .padding(horizontal = 8.dp, vertical = 4.dp) //inner padding for text
                        .weight(5f)
                )

                //Show info in EN
                Text(
                    text = enText,
                    fontSize = 14.sp,
                    color = Color.Black,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.weight(4f),
                    textAlign = TextAlign.Center, // ✅ ensure text itself is centered
                )

                Spacer(Modifier.weight(1f))

                //Change Button
                Button(
                    onClick = {
                        if (vnText != "") {
                            vnText = vnText.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString()}
                            val updatedList = medications.toMutableList()
                            updatedList[index] = "$enText/$vnText"
                            onValueChange(updatedList)
                            onUpdate(index, "$enText/$vnText")
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF448AFF),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, Color.Black),
                    modifier = Modifier
                        .height(40.dp)
                ) {
                    Text("Đổi", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        // Add button
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = {
                    onAdd()
                    val updatedList = medications.toMutableList()
                    updatedList.add("/")
                    onValueChange(updatedList)
                },
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Color.Transparent, // transparent background
                    contentColor = Color(0xFF6200EA)     // text color
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "➕",
                    color = Color(0xFF6200EA),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun SetUpInformation (
    viewModel: UserViewModel = viewModel()
) {
    val user by viewModel.user.collectAsState()
    val context = LocalContext.current
    var lastName by remember { mutableStateOf(user.lastName) }
    var firstName by remember { mutableStateOf(user.firstName) }
    var gender by remember { mutableStateOf(genders.firstOrNull { it.en == user.gender}?.vn ?: "") }
    var nationality by remember { mutableStateOf(user.nationality) }
    var birthday by remember { mutableStateOf(user.birthday) }
    var passportId by remember { mutableStateOf(user.passportId) }
    var passportExpiryDate by remember { mutableStateOf(user.passportExpiryDate) }
    var address by remember { mutableStateOf(user.address) }

    Column (
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        // Family Name
        StringFieldRow(
            label = "Họ",
            value = lastName,
            onValueChange = { lastName = it },
            onSave = {
                viewModel.updateLastName(lastName)
                Toast.makeText(context, "Đã thay đổi thành công", Toast.LENGTH_SHORT).show()
            },
            transform = { it ->
                it.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
            }
        )

        // Frist Name
        StringFieldRow(
            label = "Tên",
            value = firstName,
            onValueChange = { firstName = it },
            onSave = {
                viewModel.updateFirstName(firstName)
                Toast.makeText(context, "Đã thay đổi thành công", Toast.LENGTH_SHORT).show()
            },
            transform = {
                it.lowercase().split(" ").joinToString(" ") { word ->
                    word.replaceFirstChar { ch ->
                        if (ch.isLowerCase()) ch.titlecase() else ch.toString()
                    }
                }
            }
        )

        // Gender
        GenderRow(
            label = "Giới Tính",
            value = gender,
            onValueChange = { gender = it },
            onSave = {
                viewModel.updateGender(gender)
                Toast.makeText(context, "Đã thay đổi thành công", Toast.LENGTH_SHORT).show()
            }
        )

        //Birthday
        DateRollingRow(
            label = "Sinh Nhật",
            value = birthday,
            onValueChange = { birthday = it },
            onSave = {
                viewModel.updateBirthday(birthday)
                Toast.makeText(context, "Đã thay đổi thành công", Toast.LENGTH_SHORT).show()
            }
        )

        //Nationality
        RollingNationalityRow(
            label = "Quốc Tịch",
            value = nationality,
            onValueChange = { nationality = it },
            onSave = {
                viewModel.updateNationality(nationality)
                Toast.makeText(context, "Đã thay đổi thành công", Toast.LENGTH_SHORT).show()
            }
        )

        // PassportID
        StringFieldRow(
            label = "Số Hộ Chiếu",
            value = passportId,
            onValueChange = { passportId = it },
            onSave = {
                if (passportId.length in 6..9) {
                    viewModel.updatePassportId(passportId)
                    Toast.makeText(context, "Đã thay đổi thành công", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Hộ chiếu không hợp lệ", Toast.LENGTH_SHORT).show()
                    throw IllegalArgumentException("Invalid passport ID")
                }
            },
            transform = { it.uppercase() } // enforce uppercase
        )

        // PassportExpiryDate
        DateRollingRow(
            label = "Ngày Hết Hạn Hộ Chiếu",
            value = passportExpiryDate,
            onValueChange = { passportExpiryDate = it },
            onSave = {
                viewModel.updatePassportExpiryDate(passportExpiryDate)
                Toast.makeText(context, "Đã thay đổi thành công", Toast.LENGTH_SHORT).show()
            }
        )

        // Address
        StringFieldRow(
            label = "Địa Chỉ",
            value = address,
            onValueChange = { address = it },
            onSave = {
                viewModel.updateAddress(address)
                Toast.makeText(context, "Đã thay đổi thành công", Toast.LENGTH_SHORT).show()
            },
            transform = {
                it.lowercase().split(" ").joinToString(" ") { word ->
                    word.replaceFirstChar { ch ->
                        if (ch.isLowerCase()) ch.titlecase() else ch.toString()
                    }
                }
            },
            heightSize = 100.dp
        )
    }
}

@Composable
fun DateRollingRow(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    onSave: () -> Unit
) {
    var day by remember { mutableStateOf(value.split("-")[0]) }
    var month by remember { mutableStateOf(value.split("-")[1]) }
    var year by remember { mutableStateOf(value.split("-")[2]) }

    var borderColor by remember { mutableStateOf(Color.Gray) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(3.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.TopStart
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.titleMedium.copy(color = Color(0xFF4285F4)),
                fontSize = 18.sp
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Ngày",
                style = MaterialTheme.typography.titleMedium.copy(color = Color(0xFF4285F4)),
                fontSize = 16.sp,
                modifier = Modifier
                    .weight(1.7f)
                    .padding(end = 3.dp)
            )
            BasicTextField(
                value = day,
                onValueChange = { newText ->
                    val filtered = newText.filter { it.isDigit() }.take(2) // only digits, max 2
                    day = filtered
                    borderColor = Color.Gray // reset while typing
                },
                textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
                modifier = Modifier
                    .weight(1.5f)
                    .border(2.dp, borderColor, shape = MaterialTheme.shapes.small)
                    .padding(horizontal = 8.dp, vertical = 4.dp) //inner padding for text
            )

            Text(
                text = "Tháng",
                style = MaterialTheme.typography.titleMedium.copy(color = Color(0xFF4285F4)),
                fontSize = 15.sp,
                modifier = Modifier
                    .weight(2f)
                    .padding(start = 3.dp)
            )
            BasicTextField(
                value = month,
                onValueChange = { newText ->
                    val filtered = newText.filter { it.isDigit() }.take(2) // only digits, max 2
                    month = filtered
                    borderColor = Color.Gray // reset while typing
                },
                textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
                modifier = Modifier
                    .weight(1.5f)
                    .border(2.dp, borderColor, shape = MaterialTheme.shapes.small)
                    .padding(horizontal = 8.dp, vertical = 4.dp) //inner padding for text
            )

            Text(
                text = "Năm",
                style = MaterialTheme.typography.titleMedium.copy(color = Color(0xFF4285F4)),
                fontSize = 15.sp,
                modifier = Modifier
                    .weight(1.5f)
                    .padding(start = 3.dp)
            )
            BasicTextField(
                value = year,
                onValueChange = { newText ->
                    val filtered = newText.filter { it.isDigit() }.take(4) // only digits, max 2
                    year = filtered
                    borderColor = Color.Gray // reset while typing
                },
                textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
                modifier = Modifier
                    .weight(2f)
                    .border(2.dp, borderColor, shape = MaterialTheme.shapes.small)
                    .padding(horizontal = 8.dp, vertical = 4.dp) //inner padding for text
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    if (day!="" && month!="" && year.length == 4) {
                        onValueChange("$day-$month-$year")
                        onSave()
                        borderColor = Color(0xFF4CAF50) // green when success
                    } else {
                        borderColor = Color.Red // red when invalid
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF448AFF),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.dp, Color.Black),
                modifier = Modifier
                    .height(40.dp)
            ) {
                Text("Đổi", fontSize = 15.sp, fontWeight = FontWeight.Bold)
            }
        }
    }


}

@Composable
fun GenderRow (
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    onSave: () -> Unit
) {
    var borderColor by remember { mutableStateOf(Color.Gray) }
    var index by remember { mutableIntStateOf(genders.indexOfFirst { it.vn == value }) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.weight(3f),
            contentAlignment = Alignment.TopStart
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.titleMedium.copy(color = Color(0xFF4285F4)),
                fontSize = 18.sp
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(5f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MomentaryIconButton(
                unselectedImage = R.drawable.fast_rewind,
                selectedImage = R.drawable.fast_rewind_filled,
                stepDelay = 100L,
                onClick = {
                    moveIndex(
                        index,
                        genders.size,
                        direction = -1,
                        { index = it }
                    )
                },
                contentDescription = "Previous Gender selected",
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Row(
                modifier = Modifier
                    .weight(4f)
                    .border(2.dp, borderColor, shape = MaterialTheme.shapes.small)
                    .padding(3.dp), // space inside the border,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = genders[index].vn,
                    style = MaterialTheme.typography.bodyLarge.copy( // base style
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color(0xff651fff)
                    )
                )
                Image(
                    painter = painterResource(id = genders[index].image),
                    contentDescription = "${genders[index].en} Flag",
                    modifier = Modifier
                        .size(36.dp)
                )
            }
            Spacer(modifier = Modifier.width(5.dp))
            MomentaryIconButton(
                unselectedImage = R.drawable.fast_forward,
                selectedImage = R.drawable.fast_forward_filled,
                stepDelay = 100L,
                onClick = {
                    moveIndex(
                        index,
                        genders.size,
                        direction = 1,
                        { index = it }
                    )
                },
                contentDescription = "Next Gender selected",
                modifier = Modifier.weight(1f)
            )
        }

        Button(
            onClick = {
                if (value.isNotBlank()) {
                    onValueChange(genders[index].en)
                    onSave()
                    borderColor = Color(0xFF4CAF50) // green when success
                } else {
                    borderColor = Color.Red // red when invalid
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF448AFF),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp, Color.Black),
            modifier = Modifier
                .height(40.dp)
                .weight(2f)
                .padding(start = 3.dp)
        ) {
            Text("Đổi", fontSize = 15.sp, fontWeight = FontWeight.Bold)
        }


    }
}

@Composable
fun RollingNationalityRow (
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    onSave: () -> Unit,
    transform: (String) -> String = { it } // optional transformation (e.g. uppercase)
) {

    var borderColor by remember { mutableStateOf(Color.Gray) }
    var expanded by remember { mutableStateOf(false) }
    var filteredList by remember { mutableStateOf(nationalities)}

    var inputSize by remember { mutableStateOf(Size.Zero) }
    val density = LocalDensity.current

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.weight(3f),
            contentAlignment = Alignment.TopStart
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.titleMedium.copy(color = Color(0xFF4285F4)),
                fontSize = 18.sp
            )
        }

        Box(
            modifier = Modifier
                .height(40.dp)
                .weight(5f)
                .border(2.dp, borderColor, shape = MaterialTheme.shapes.small),
            contentAlignment = Alignment.Center
        ) {
            // Filtered list based on query
            filteredList = if (value.isBlank()) {
                nationalities
            } else {
                nationalities.filter { nationality ->
                    nationality.vn.contains(value, ignoreCase = true)
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        inputSize = coordinates.size.toSize()
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicTextField(
                    value = value,
                    onValueChange = { newText ->
                        onValueChange(transform(newText))
                        borderColor = Color.Gray // reset while typing
                    },
                    textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
                    modifier = Modifier.padding(8.dp)
                )
                Spacer(modifier = Modifier.weight(1f)) //
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = null
                    )
                }
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.width(
                    with(density) { inputSize.width.toDp() }, // convert px → dp
                )
            ) {
                if (filteredList.isEmpty()) {
                    DropdownMenuItem(
                        text = { Text("Không tìm thấy") },
                        onClick = { }
                    )
                } else {
                    filteredList.forEach { option ->
                        DropdownMenuItem(
                            onClick = {
                                onValueChange(option.vn)
                                expanded = false
                            },
                            text = {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image (
                                        painter = painterResource(id = option.image),
                                        contentDescription = option.en,
                                        modifier = Modifier.size(25.dp)
                                    )
                                    Spacer(modifier = Modifier.weight(1f))
                                    Text(
                                        text = option.vn,
                                        style = MaterialTheme.typography.titleMedium,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Color(0xff263238)
                                    )
                                }
                            }
                        )
                    }
                }
            }
        }

        Button(
            onClick = {
                if (value.isNotBlank()) {
                    onSave()
                    borderColor = Color(0xFF4CAF50) // green when success
                } else {
                    borderColor = Color.Red // red when invalid
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF448AFF),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp, Color.Black),
            modifier = Modifier
                .height(40.dp)
                .weight(2f)
                .padding(start = 3.dp)
        ) {
            Text("Đổi", fontSize = 15.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun StringFieldRow(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    onSave: () -> Unit,
    transform: (String) -> String = { it }, // optional transformation (e.g. uppercase)
    heightSize: Dp = 40.dp
) {
    var borderColor by remember { mutableStateOf(Color.Gray) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.weight(3f),
            contentAlignment = Alignment.TopStart
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.titleMedium.copy(color = Color(0xFF4285F4)),
                fontSize = 18.sp
            )
        }

        Box(
            modifier = Modifier
                .height(heightSize)
                .weight(5f)
                .border(2.dp, borderColor, shape = MaterialTheme.shapes.small)
                .padding(horizontal = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            BasicTextField(
                value = value,
                onValueChange = { newText ->
                    onValueChange(transform(newText))
                    borderColor = Color.Gray // reset while typing
                },
                textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )

            if (value.isEmpty()) {
                Text("", fontSize = 18.sp, color = Color.Black)
            }
        }

        Button(
            onClick = {
                if (value.isNotBlank()) {
                    onSave()
                    borderColor = Color(0xFF4CAF50) // green when success
                } else {
                    borderColor = Color.Red // red when invalid
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF448AFF),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp, Color.Black),
            modifier = Modifier
                .height(40.dp)
                .weight(2f)
                .padding(start = 3.dp)
        ) {
            Text("Đổi", fontSize = 15.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ExpandableHeaderCard(
    title: String,
    content: @Composable () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(if (expanded) Color.White else Color(0xFF1976D2)) // switch bg
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { expanded = !expanded }
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = if (expanded) Color(0xFF6200EA) else Color.White
                ),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )

            Icon(
                imageVector = if (expanded) Icons.Default.Remove else Icons.Default.Add,
                contentDescription = if (expanded) "Collapse" else "Expand",
                tint = if (expanded) Color(0xFF6200EA) else Color.White
            )
        }

        // Expandable content (white background)
        AnimatedVisibility(visible = expanded) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                content()
            }
        }
    }
}

private fun moveIndex(
    currentIndex: Int,
    maxIndex: Int,
    direction: Int,
    onMove: (Int) -> Unit
) {
    val newIndex = when (direction) {
        1  -> if (currentIndex + 1 >= maxIndex) 0 else currentIndex + 1
        -1 -> if (currentIndex - 1 < 0) maxIndex - 1 else currentIndex - 1
        else -> currentIndex // No change if direction is 0 or invalid
    }
    onMove(newIndex)
}