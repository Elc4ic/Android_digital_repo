package com.example.digital_kaf.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.digital_kaf.domain.entities.Gender
import com.example.digital_kaf.domain.entities.User
import com.example.digital_kaf.domain.repository.RegistrationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject
constructor(
    private val repo: RegistrationRepository,
) : ViewModel() {

    val login = mutableStateOf("")
    val loginErr = mutableStateOf("")

    val password = mutableStateOf("")
    val passwordErr = mutableStateOf("")

    val repeatPassword = mutableStateOf("")
    val repeatPasswordErr = mutableStateOf("")

    val nickname = mutableStateOf("")

    val gender = mutableStateOf<Gender?>(null)

    var isEnabledRegisterButton = mutableStateOf(false)
    var isEnabledLoginButton = mutableStateOf(false)


    fun validateLogin() {
        if (login.value.length < 6) loginErr.value = "Login already exists"
        else if (repo.validateLogin(login.value)) loginErr.value = "Login is repeated"
        else loginErr.value = ""
        enabledRegisterButton()
        enabledLoginButton()
    }

    fun validatePassword() {
        if (password.value.length < 8) passwordErr.value = "Password too easy"
        else passwordErr.value = ""
        enabledRegisterButton()
        enabledLoginButton()
    }

    fun validateRepeatPassword() {
        if (password.value != repeatPassword.value) repeatPasswordErr.value =
            "Passwords don't match"
        else repeatPasswordErr.value = ""
        enabledRegisterButton()
    }

    fun setGender(value: Gender) {
        gender.value = value
        enabledRegisterButton()
    }

    fun enabledRegisterButton() {
        isEnabledRegisterButton.value = login.value.isNotEmpty()
                && nickname.value.isNotEmpty()
                && repeatPassword.value.isNotEmpty()
                && password.value.isNotEmpty()
                && gender.value != null
    }

    fun enabledLoginButton() {
        isEnabledLoginButton.value = login.value.isNotEmpty() && password.value.isNotEmpty()
    }

    fun register() =
        repo.register(
            User(
                UUID.randomUUID(),
                login.value,
                nickname.value,
                password.value,
                gender.value?.name ?: Gender.OTHER.name
            )
        )

    fun login() = repo.login(login.value, password.value)
}