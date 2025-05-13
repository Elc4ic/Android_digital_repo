package com.example.digital_kaf.domain.repository

import com.example.digital_kaf.domain.entities.User

interface RegistrationRepository {
    fun register(user: User): Boolean

    fun login(login: String, password: String): Boolean

    fun validateLogin(login: String): Boolean
}