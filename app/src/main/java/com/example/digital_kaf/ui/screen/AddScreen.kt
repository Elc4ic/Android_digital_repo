package com.example.digital_kaf.ui.screen

import android.Manifest
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.digital_kaf.ui.components.TimerBottomSheet
import com.example.digital_kaf.ui.components.TypeBottomSheet
import com.example.digital_kaf.ui.theme.primary
import com.example.digital_kaf.viewmodel.AddViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.delay
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Polyline

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(
    navController: NavController?,
    avm: AddViewModel = viewModel(),
) {
    val context = LocalContext.current
    val locationPermission = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)
    val sheetState = rememberBottomSheetScaffoldState()
    val userLocation by avm.userLocation.collectAsState()
    val pathPoints by avm.pathPoints.collectAsState()
    val centerMapTrigger by avm.centerMapTrigger.collectAsState()
    val step by avm.step.collectAsState()

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val sheetHeight = screenHeight / 3

    LaunchedEffect(Unit) {
        if (!locationPermission.status.isGranted) {
            locationPermission.launchPermissionRequest()
        } else {
            avm.startTracking(context)
        }
    }

    LaunchedEffect(Unit) {
        delay(100)
        sheetState.bottomSheetState.expand()
    }

    val mapView = remember {
        MapView(context).apply {
            setTileSource(TileSourceFactory.MAPNIK)
            setMultiTouchControls(true)
            controller.setZoom(14.0)
            controller.setCenter(userLocation)
            if (pathPoints.size > 2) {
                val polyline = Polyline().apply {
                    outlinePaint.color = primary.toArgb()
                    outlinePaint.strokeWidth = 8f
                    pathPoints.forEach { addPoint(it) }
                }
                overlays.clear()
                overlays.add(polyline)
            }
        }
    }

    LaunchedEffect(userLocation) {
        userLocation?.let { location ->
            val marker = Marker(mapView).apply {
                position = location
                setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                title = "Вы здесь"
            }

            mapView.overlays.clear()
            mapView.overlays.add(marker)
            mapView.invalidate()
        }
    }

    LaunchedEffect(centerMapTrigger) {
        if (centerMapTrigger) {
            userLocation?.let {
                mapView.controller.animateTo(it)
            }
            avm.resetCentering()
        }
    }

    BottomSheetScaffold(
        scaffoldState = sheetState,
        sheetContent = {
            when (step) {
                0 -> TypeBottomSheet(avm)
                else -> TimerBottomSheet(navController, avm)
            }
        },
        sheetPeekHeight = sheetHeight
    ) { paddingV ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingV)) {
            AndroidView(factory = { mapView }, modifier = Modifier.fillMaxSize())
            FloatingActionButton(
                onClick = { avm.requestCentering() },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                containerColor = primary
            ) {
                Icon(Icons.Default.LocationOn, contentDescription = "Центрировать")
            }
        }

    }

    DisposableEffect(Unit) {
        onDispose {
            mapView.onDetach()
        }
    }
}