package com.example.digital_kaf.data.repository

import com.example.digital_kaf.data.database.Database
import com.example.digital_kaf.domain.entities.User
import com.example.digital_kaf.domain.repository.RegistrationRepository
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

class RegistrationRepositoryImpl @Inject constructor(
    private val database: Database,
) : RegistrationRepository {
    override fun register(user: User): Boolean {
        runBlocking {
            database.userDao().insert(user)
        }
        return true
    }

    override fun login(login: String, password: String): Boolean {
        return true
    }

    override fun validateLogin(login: String): Boolean {
        return true
    }

}