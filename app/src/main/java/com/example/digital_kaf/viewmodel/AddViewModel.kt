package com.example.digital_kaf.viewmodel

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.osmdroid.util.GeoPoint
import timber.log.Timber
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val repo: ActivityRepository,
) : ViewModel() {

    var step = MutableStateFlow(0)

    private lateinit var fusedClient: FusedLocationProviderClient

    private val _userLocation = MutableStateFlow<GeoPoint?>(null)
    val userLocation: StateFlow<GeoPoint?> = _userLocation.asStateFlow()

    private val _pathPoints = MutableStateFlow<List<GeoPoint>>(emptyList())
    val pathPoints: StateFlow<List<GeoPoint>> = _pathPoints.asStateFlow()

    private val _centerMapTrigger = MutableStateFlow(false)
    val centerMapTrigger: StateFlow<Boolean> = _centerMapTrigger.asStateFlow()

    private val _distance = MutableStateFlow(0f)
    val distance: StateFlow<Float> = _distance

    private val _timeElapsed = MutableStateFlow(0L)
    val timeElapsed: StateFlow<Long> = _timeElapsed

    private val _sportType = MutableStateFlow("")
    val sportType: StateFlow<String> = _sportType.asStateFlow()

    private var timerJob: Job? = null
    private var startTime = 0L

    fun startTracking(context: Context) {
        fusedClient = LocationServices.getFusedLocationProviderClient(context)
        requestLocationUpdates(context)
    }

    fun setType(type: String) {
        _sportType.value = type
        step.value++
        startTime = System.currentTimeMillis()
        startTimer()
    }

    fun saveActivity() {
        viewModelScope.launch {
            val activity = Activity(
                id = UUID.randomUUID(),
                distance = _distance.value,
                activityType = _sportType.value,
                startTime = startTime,
                endTime = startTime + _timeElapsed.value,
                userId = "admin"  ,//TODO provide real user login
                comment = "-"
            )
            repo.add(activity)
            onCleared()
        }
    }

    private fun startTimer() {
        timerJob = viewModelScope.launch {
            _timeElapsed.value = 0
            while (true) {
                delay(1000)
                _timeElapsed.update { it + 1 }
            }
        }
    }

    fun requestCentering() {
        _centerMapTrigger.value = true
    }

    fun resetCentering() {
        _centerMapTrigger.value = false
    }

    private fun requestLocationUpdates(context: Context) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) return
        val request = LocationRequest.Builder(1000)
            .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
            .setMinUpdateDistanceMeters(2f)
            .build()
        Timber.d("start tracking")
        fusedClient.requestLocationUpdates(
            request,
            object : LocationCallback() {
                override fun onLocationResult(result: LocationResult) {
                    result.lastLocation?.let { loc ->
                        _userLocation.value = GeoPoint(loc.latitude, loc.longitude)
                        Timber.d(loc.toString())
                    }
                    _pathPoints.value =
                        result.locations.map { GeoPoint(it.latitude, it.longitude) }
                }
            },
            Looper.getMainLooper()
        )
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}