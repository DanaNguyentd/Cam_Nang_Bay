package com.example.hotrobay.navigators.userNavigators

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hotrobay.R

/**
 * enum values that represent the screens in the app
 */

enum class UserScreen(@StringRes val title:Int) {
    UserMainScreen(R.string.UserMainScreen),
    GeneralInformation(R.string.GeneralInformation),
    MedicineInformation(R.string.MedicineInformation),
    ContactInformation(R.string.ContactInformation),
    SetupInformation(R.string.SetupInformation)
}

@Composable
fun UserInformation(
    mainScreenClicked : () -> Unit,
) {
    val isInPreview = LocalInspectionMode.current
    val navController = if (isInPreview) {
        // You can provide a dummy object or skip rendering the screen
        null
    } else {
        rememberNavController()
    }
    if (navController != null) {
        NavHost(
            navController = navController,
            startDestination = UserScreen.UserMainScreen.name,
            modifier = Modifier
                .fillMaxSize()
        ) {
            composable(route = UserScreen.UserMainScreen.name) {
                MainInformationScreen(
                    generalInformationClicked  = { navController.navigate(UserScreen.GeneralInformation.name) },
                    medicineInformationClicked = { navController.navigate(UserScreen.MedicineInformation.name)},
                    contactInformationClicked  = { navController.navigate(UserScreen.ContactInformation.name)},
                    setupInformationClicked    = { navController.navigate(UserScreen.SetupInformation.name)},
                    mainScreenClicked
                )
            }

            composable(route = UserScreen.GeneralInformation.name) {
                GeneralInformationScreen(
                    mainInformationScreenClicked = { navController.navigate(UserScreen.UserMainScreen.name)},
                    medicineInformationClicked   = { navController.navigate(UserScreen.MedicineInformation.name)},
                    contactInformationClicked    = { navController.navigate(UserScreen.ContactInformation.name)}
                )
            }

            composable(route = UserScreen.MedicineInformation.name) {
                MedicineInformationScreen(
                    mainInformationScreenClicked = { navController.navigate(UserScreen.UserMainScreen.name)},
                    generalInformationClicked    = { navController.navigate(UserScreen.GeneralInformation.name) },
                    contactInformationClicked    = { navController.navigate(UserScreen.ContactInformation.name)}
                )
            }

            composable(route = UserScreen.ContactInformation.name) {
                ContactInformationScreen(
                    mainInformationScreenClicked = { navController.navigate(UserScreen.UserMainScreen.name)},
                    generalInformationClicked    = { navController.navigate(UserScreen.GeneralInformation.name) },
                    medicineInformationClicked   = { navController.navigate(UserScreen.MedicineInformation.name)},
                )
            }

            composable(route = UserScreen.SetupInformation.name) {
                SetupInformationScreen(
                    mainInformationScreenClicked = { navController.navigate(UserScreen.UserMainScreen.name)}
                )
            }
        }
    }

}