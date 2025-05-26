package com.example.digital_kaf.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.digital_kaf.domain.entities.Activity
import kotlinx.coroutines.flow.Flow

@Dao
interface ActivityDao {
    @Query("SELECT * FROM activities")
    fun getAll(): Flow<List<Activity>>

    @Query("SELECT * FROM activities WHERE id = :id")
    fun getById(id: String): Flow<Activity>

    @Query("SELECT * FROM activities WHERE id == :myid ORDER BY end_time, id")
    fun getMyPagingSource(myid: String): List<Activity>

    @Query("SELECT * FROM activities WHERE id != :myid ORDER BY end_time, id")
    fun getOtherPagingSource(myid: String): List<Activity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<Activity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg items: Activity)

    @Query("DELETE FROM activities")
    fun clear()
}