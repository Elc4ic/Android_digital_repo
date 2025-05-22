package com.example.digital_kaf.ui.screen

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceActivity
import android.preference.PreferenceManager
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.digital_kaf.ui.theme.primary
import com.example.digital_kaf.viewmodel.AddViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import org.osmdroid.config.Configuration
import org.osmdroid.library.BuildConfig
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Polyline

@Composable
fun AddScreen(
    navController: NavController?,
    avm: AddViewModel = viewModel(),
) {
    val context = LocalContext.current

    LaunchedEffect(true) {
        avm.startTracking(context)
    }

    val location by avm.location.collectAsState()
    val pathPoints by avm.pathPoints.collectAsState()

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val sheetHeight = screenHeight / 3

//    val sheetState = rememberBottomSheetScaffoldState()
//
//    LaunchedEffect(Unit) {
//        delay(100)
//        sheetState.bottomSheetState.expand()
//    }

    AndroidView(factory = { ctx ->
        MapView(ctx).apply {
            setTileSource(TileSourceFactory.MAPNIK)
            setMultiTouchControls(true)
            controller.setZoom(18.0)
            val geo = location?.let { GeoPoint(it.latitude, it.longitude) }
            controller.setCenter(geo)
            val polyline = Polyline().apply {
                outlinePaint.color = primary.toArgb()
                outlinePaint.strokeWidth = 8f
                pathPoints.forEach {
                    addPoint(GeoPoint(it.latitude, it.longitude))
                }
            }
            overlays.clear()
            overlays.add(polyline)
        }
    }, modifier = Modifier.fillMaxSize())

//    BottomSheetScaffold(
//        scaffoldState = sheetState,
//        sheetContent = { TypeBottomSheet(avm) },
//        sheetPeekHeight = sheetHeight
//    ) { paddingValues ->
//        GoogleMap(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(paddingValues),
//            properties = mapProperties,
//            uiSettings = mapUiSettings,
//            cameraPositionState = rememberCameraPositionState {
//                locationState?.let {
//                    position = CameraPosition.fromLatLngZoom(LatLng(it.latitude, it.longitude), 17f)
//                }
//            }
//        ) {
//            if (pathPoints.size >= 2) {
//                Polyline(points = pathPoints.toList(), color = Color.Blue, width = 5f)
//            }
//        }
//    }

}