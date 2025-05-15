package com.example.digital_kaf.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.digital_kaf.domain.entities.Activity
import com.example.digital_kaf.domain.repository.ActivityRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import timber.log.Timber

class ActivityViewModel
@AssistedInject constructor(
    repo: ActivityRepository,
    @Assisted initialStationId: String
) : ViewModel() {
    val activity: StateFlow<Activity?> =
        repo
            .getOne(initialStationId)
            .catch { e ->
                Timber.e(e)
                _errorState.value = e.toString()
            }
            .stateIn(viewModelScope, SharingStarted.Lazily, null)

    private val _errorState = MutableStateFlow<String?>(null)
    val error: StateFlow<String?>
        get() = _errorState

    fun clearError() {
        _errorState.value = null
    }

    @dagger.assisted.AssistedFactory
    fun interface AssistedFactory {
        fun create(initialStationId: String): ActivityViewModel
    }

    companion object {
        fun AssistedFactory.provideFactory(initialStationId: String): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return this@provideFactory.create(initialStationId) as T
                }
            }
    }
}