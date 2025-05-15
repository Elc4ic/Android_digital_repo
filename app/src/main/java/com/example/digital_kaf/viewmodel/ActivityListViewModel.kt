package com.example.digital_kaf.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.digital_kaf.domain.entities.Activity
import com.example.digital_kaf.domain.repository.ActivityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ActivityListViewModel @Inject constructor(
    private val repository: ActivityRepository,
) : ViewModel() {

    private val _my = MutableStateFlow(false)
    val my: StateFlow<Boolean>
        get() = _my

    fun updateTab(s: Boolean) {
        _my.value = s
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val pages: StateFlow<List<Activity>> =
        my
            .flatMapLatest { repository.getAll(it) }
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