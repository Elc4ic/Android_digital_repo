package com.example.digital_kaf.data.database

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.digital_kaf.domain.entities.User
import kotlinx.coroutines.flow.Flow

interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<User>)

    @Query("SELECT * FROM users")
    fun getUser(): Flow<User>

    @Query("DELETE FROM users")
    suspend fun clear()
}