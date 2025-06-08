package com.example.digital_kaf.data.repository

import com.example.digital_kaf.data.database.Database
import com.example.digital_kaf.domain.entities.User
import com.example.digital_kaf.domain.repository.RegistrationRepository
import javax.inject.Inject

class RegistrationRepositoryImpl @Inject constructor(
    private val database: Database,
) : RegistrationRepository {

    override suspend fun register(user: User) {
        database.userDao().insert(user)
    }

    override suspend fun login(login: String, password: String):User? {
        return database.userDao().findByLoginAndPassword(login, password)
    }

    override suspend fun validateLogin(login: String): Boolean {
        return database.userDao().findByLogin(login) != null
    }

}