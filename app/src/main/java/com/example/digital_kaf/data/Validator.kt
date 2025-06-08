package com.example.digital_kaf.data

import com.example.digital_kaf.domain.entities.Gender
import com.example.digital_kaf.domain.entities.User
import com.example.digital_kaf.domain.repository.RegistrationRepository

data class AuthValidator(
    val login: String? = null,
    var loginErr: String? = null,
    val nickname: String? = null,
    var nicknameErr: String? = null,
    val password: String? = null,
    var passwordErr: String? = null,
    val passwordRepeat: String? = null,
    var passwordRepeatErr: String? = null,
    val gender: Gender? = null,
    val isFormValid: Boolean = false
) {
    suspend fun isRegistrationValid(repo: RegistrationRepository): AuthValidator {
        validateRegLogin(repo)
        validatePassword()
        validateRepeatPassword()
        validateNickname()

        return copy(
            isFormValid = loginErr == null &&
                    passwordErr == null &&
                    passwordRepeatErr == null &&
                    nicknameErr == null &&
                    gender != null
        )
    }

    suspend fun isLoginValid(repo: RegistrationRepository): Boolean {
        validateLogin(repo)
        validatePassword()
        return loginErr == null && passwordErr == null
    }

    suspend fun validateRegLogin(repo: RegistrationRepository) {
        if (login.isNullOrBlank()) loginErr = "Login must not be empty"
        else if (repo.validateLogin(login)) loginErr = "Login is repeated"
        else loginErr = null
    }

    suspend fun validateLogin(repo: RegistrationRepository) {
        if (login.isNullOrBlank()) loginErr = "Login must not be empty"
        else if (!repo.validateLogin(login)) loginErr = "User with this login not found"
        else loginErr = null
    }

    fun validatePassword() {
        if (password.isNullOrBlank()) passwordErr = "Password must not be empty"
        else if (password.length < 6) passwordErr = "Password must be at least 6 characters long"
        else passwordErr = null
    }

    fun validateRepeatPassword() {
        if (passwordRepeat != password) passwordRepeatErr = "Passwords do not match"
        else passwordRepeatErr = null
    }

    private fun validateNickname() {
        if (nickname.isNullOrBlank()) nicknameErr = "Nickname must not be empty"
        else if (nickname.length < 3 || nickname.length > 30) nicknameErr =
            "Nickname must be 3-30 characters long"
        else nicknameErr = null
    }

    fun createUser(): User {
        return User(
            login!!,
            nickname!!,
            password!!,
            gender?.name ?: Gender.OTHER.name
        )
    }

}