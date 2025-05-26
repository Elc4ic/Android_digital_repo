package com.example.digital_kaf.domain.repository

import com.example.digital_kaf.domain.entities.User

interface RegistrationRepository {
    suspend fun register(user: User)

    suspend fun login(login: String, password: String): User?

    suspend fun validateLogin(login: String):Boolean
}