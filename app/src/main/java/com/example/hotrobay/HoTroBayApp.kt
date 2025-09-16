package com.example.hotrobay

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.hotrobay.navigators.AtAirPort
import com.example.hotrobay.navigators.FlightInformation
import com.example.hotrobay.navigators.InAirPlane
import com.example.hotrobay.navigators.MainScreen
import com.example.hotrobay.navigators.SettingScreen

/**
 * enum values that represent the screens in the app
 */

enum class HoTroBayScreen(@StringRes val title:Int) {
    MainScreen(R.string.main),
    FlightInfo(R.string.FlightInfo),
    AtAirPort(R.string.AtAirPort),
    InAirPlane(R.string.InAirPlane),
    SettingScreen(R.string.setting)
}

@Composable
fun HoTroBayApp() {
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
            startDestination = HoTroBayScreen.MainScreen.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
        ) {
            composable(route = HoTroBayScreen.MainScreen.name) {
                MainScreen(
                    onSettingClicked    = { navController.navigate(HoTroBayScreen.SettingScreen.name) },
                    onFlightInfoClicked = { navController.navigate(HoTroBayScreen.FlightInfo.name)},
                    onAtAirPortClicked  = { navController.navigate(HoTroBayScreen.AtAirPort.name)},
                    inAirPlaneClicked   = { navController.navigate(HoTroBayScreen.InAirPlane.name)}
                )
            }

            composable(route = HoTroBayScreen.FlightInfo.name) {
                FlightInformation(
                    mainScreenClicked = { navController.navigate(HoTroBayScreen.MainScreen.name)},
                    atAirPortClicked  = { navController.navigate(HoTroBayScreen.AtAirPort.name) },
                    inAirPlaneClicked = { navController.navigate(HoTroBayScreen.InAirPlane.name)}
                )
            }

            composable(route = HoTroBayScreen.AtAirPort.name) {
                AtAirPort(
                    mainScreenClicked = { navController.navigate(HoTroBayScreen.MainScreen.name) },
                    flightInfoClicked = { navController.navigate(HoTroBayScreen.FlightInfo.name) },
                    inAirPlaneClicked = { navController.navigate(HoTroBayScreen.InAirPlane.name) }
                )
            }

            composable(route = HoTroBayScreen.InAirPlane.name) {
                InAirPlane(
                    mainScreenClicked = { navController.navigate(HoTroBayScreen.MainScreen.name) },
                    flightInfoClicked = { navController.navigate(HoTroBayScreen.FlightInfo.name) },
                    atAirPortClicked  = { navController.navigate(HoTroBayScreen.AtAirPort.name)}
                )
            }

            composable(route = HoTroBayScreen.SettingScreen.name) {
                SettingScreen(
                    mainScreenClicked = { navController.navigate(HoTroBayScreen.MainScreen.name) }
                )
            }
        }
    }
}
