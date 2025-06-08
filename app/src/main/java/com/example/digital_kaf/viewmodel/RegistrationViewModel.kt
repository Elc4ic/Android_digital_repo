package com.example.digital_kaf.viewmodel

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.digital_kaf.data.AuthValidator
import com.example.digital_kaf.domain.entities.Gender
import com.example.digital_kaf.domain.entities.User
import com.example.digital_kaf.domain.repository.RegistrationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject
constructor(
    private val repo: RegistrationRepository,
) : ViewModel() {

    private val _ui = MutableStateFlow(AuthValidator())
    val ui = _ui.asStateFlow()

    val regUser = mutableStateOf<User?>(null)

    fun setGender(value: Gender) {
        _ui.value = _ui.value.copy(gender = value)
    }

    fun setLogin(value: String) {
        _ui.value = _ui.value.copy(login = value)
    }

    fun setPassword(value: String) {
        _ui.value = _ui.value.copy(password = value)
    }

    fun setPasswordRepeat(value: String) {
        _ui.value = _ui.value.copy(passwordRepeat = value)
    }

    fun setNickname(value: String) {
        _ui.value = _ui.value.copy(nickname = value)
    }

    fun validateRegister() {
        viewModelScope.launch {
            _ui.value.isRegistrationValid(repo)
        }
    }

    fun validateLogin() {
        viewModelScope.launch {
            _ui.value.isLoginValid(repo)
        }
    }

    fun register() {
        viewModelScope.launch {
            val user = _ui.value.createUser()
            repo.register(user)
            regUser.value = user
        }
    }

    fun login() =
        viewModelScope.launch {
            val user = repo.login(_ui.value.login!!, _ui.value.password!!)
            regUser.value = user
        }
}