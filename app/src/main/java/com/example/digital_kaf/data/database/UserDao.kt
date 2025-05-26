package com.example.digital_kaf.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.digital_kaf.domain.entities.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(items: User)

    @Query("SELECT * FROM users WHERE login = :login AND password = :password")
    suspend fun login(login: String, password: String): User?

    @Query("SELECT * FROM users WHERE login = :login")
    fun validateLogin(login: String): User?

    @Query("DELETE FROM users")
    suspend fun clear()
}