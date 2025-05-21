package com.example.digital_kaf.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.digital_kaf.ui.components.TimerBottomSheet
import com.example.digital_kaf.ui.components.TypeBottomSheet
import com.example.digital_kaf.viewmodel.AddViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(
    navController: NavController?,
    avm: AddViewModel = viewModel(),
) {
    avm.startTracking(LocalContext.current)

    val locationState by avm.location.collectAsState()
    val pathPoints by avm.pathPoints.collectAsState()

    val mapUiSettings = remember { MapUiSettings(zoomControlsEnabled = false) }
    val mapProperties = remember { MapProperties(isMyLocationEnabled = true) }

//    val screenHeight = Configuration.SCREEN_HEIGHT_DP_UNDEFINED
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val sheetHeight = screenHeight / 3
    val sheetState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        scaffoldState = sheetState,
        sheetContent = {
            when (avm.step) {
                0 -> TypeBottomSheet(avm)
                else -> TimerBottomSheet(navController, avm)
            }
        },
        sheetPeekHeight = sheetHeight
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            properties = mapProperties,
            uiSettings = mapUiSettings,
            cameraPositionState = rememberCameraPositionState {
                locationState?.let {
                    position = CameraPosition.fromLatLngZoom(LatLng(it.latitude, it.longitude), 17f)
                }
            }
        ) {
            if (pathPoints.size >= 2) {
                Polyline(points = pathPoints.toList(), color = Color.Blue, width = 5f)
            }
        }
    }

}