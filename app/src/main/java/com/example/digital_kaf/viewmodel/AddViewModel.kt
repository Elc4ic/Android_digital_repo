package com.example.digital_kaf.viewmodel

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digital_kaf.domain.entities.Activity
import com.example.digital_kaf.domain.repository.ActivityRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val repo: ActivityRepository
) : ViewModel() {

    var step = 0

    private var fusedClient: FusedLocationProviderClient? = null

    private val _location = MutableStateFlow<Location?>(null)
    var location: MutableStateFlow<Location?> = _location

    private val _pathPoints = MutableStateFlow<List<LatLng>>(emptyList())
    val pathPoints: StateFlow<List<LatLng>> = _pathPoints

    private val _distance = MutableStateFlow(0f)
    val distance: StateFlow<Float> = _distance

    private val _timeElapsed = MutableStateFlow(0L)
    val timeElapsed: StateFlow<Long> = _timeElapsed

    private var startTime = 0L
    private var sportType = ""

    private var timerJob: Job? = null
    private var locationCallback: LocationCallback? = null


    fun startTracking(context: Context) {
        fusedClient = LocationServices.getFusedLocationProviderClient(context)
        startTime = System.currentTimeMillis()
        startTimer()
        requestLocationUpdates(context)
    }

    fun setType(type: String) {
        sportType = type
        step++
    }

    fun saveActivity() {
        val activity = Activity(
            id = UUID.randomUUID(),
            distance = distance.value,
            activityType = sportType,
            startTime = startTime,
            endTime = startTime + timeElapsed.value,
            userId = UUID.randomUUID(),//??????
            comment = "-"
        )
        repo.add(activity)
        onCleared()
    }

    private fun startTimer() {
        timerJob = viewModelScope.launch {
            while (true) {
                delay(1000)
                _timeElapsed.update { it + 1 }
            }
        }
    }

    private fun requestLocationUpdates(context: Context) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val request = LocationRequest.Builder(1000)
                .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
                .setMinUpdateDistanceMeters(2f)
                .build()

            fusedClient?.requestLocationUpdates(
                request,
                object : LocationCallback() {
                    override fun onLocationResult(result: LocationResult) {
                        result.lastLocation?.let { location.value = it }
                    }
                },
                Looper.getMainLooper()
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        fusedClient?.removeLocationUpdates(locationCallback!!)
        timerJob?.cancel()
    }
}