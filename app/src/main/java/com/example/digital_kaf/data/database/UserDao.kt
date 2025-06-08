package com.example.digital_kaf.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.digital_kaf.domain.entities.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(items: User)

    @Query("SELECT * FROM users WHERE login = :login AND password = :password")
    suspend fun findByLoginAndPassword(login: String, password: String): User?

    @Query("SELECT * FROM users WHERE login = :login")
    fun findByLogin(login: String): User?

    @Query("DELETE FROM users")
    suspend fun clear()
}