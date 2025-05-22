package com.example.digital_kaf.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digital_kaf.domain.entities.Activity
import com.example.digital_kaf.domain.repository.ActivityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ActivityListViewModel @Inject constructor(
    repository: ActivityRepository,
) : ViewModel() {

    val otherPages: StateFlow<List<Activity>> =
        repository.getAll(false)
            .catch { e ->
                Timber.e(e)
                _errorState.value = e.toString()
            }
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val myPages: StateFlow<List<Activity>> =
        repository.getAll(true)
            .catch { e ->
                Timber.e(e)
                _errorState.value = e.toString()
            }
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private val _errorState = MutableStateFlow<String?>(null)
    val error: StateFlow<String?>
        get() = _errorState

    fun clearError() {
        _errorState.value = null
    }

}