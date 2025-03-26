package com.example.digital_kaf.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


class RegistrationViewModel : ViewModel() {

    val login = mutableStateOf<String>("")
    val loginErr = mutableStateOf<String>("")

    val password = mutableStateOf<String>("")
    val passwordErr = mutableStateOf<String>("")

    val repeatPassword = mutableStateOf<String>("")
    val repeatPasswordErr = mutableStateOf<String>("")

    val nickname = mutableStateOf<String>("")

    val isMale = mutableStateOf<Boolean>(false)
    val isFemale = mutableStateOf<Boolean>(false)
    val isOther = mutableStateOf<Boolean>(false)

    var isEnabledRegisterButton = mutableStateOf<Boolean>(false)
    var isEnabledLoginButton = mutableStateOf<Boolean>(false)


    fun validateLogin() {
        if (login.value.length < 6) loginErr.value = "Login already exists"
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
        if (password.value != repeatPassword.value) repeatPasswordErr.value = "Passwords don't match"
        else repeatPasswordErr.value = ""
        enabledRegisterButton()
    }

    fun setGender(value: Boolean, m: Boolean, f: Boolean, o: Boolean) {
        if (m) {
            isMale.value = value
            isFemale.value = false
            isOther.value = false
        } else if (f) {
            isMale.value = false
            isFemale.value = value
            isOther.value = false
        } else if (o) {
            isMale.value = false
            isFemale.value = false
            isOther.value = value
        }
        enabledRegisterButton()
    }

    fun enabledRegisterButton() {
        isEnabledRegisterButton.value = login.value.isNotEmpty()
                && nickname.value.isNotEmpty()
                && repeatPassword.value.isNotEmpty()
                && password.value.isNotEmpty()
                && (isMale.value || isFemale.value || isOther.value)
    }

    fun enabledLoginButton() {
        isEnabledLoginButton.value = login.value.isNotEmpty() && password.value.isNotEmpty()
    }

}