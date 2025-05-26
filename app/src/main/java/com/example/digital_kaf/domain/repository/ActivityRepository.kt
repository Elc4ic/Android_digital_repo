package com.example.digital_kaf.domain.repository

import com.example.digital_kaf.domain.entities.Activity
import kotlinx.coroutines.flow.Flow

interface ActivityRepository {
    fun getAll(my: Boolean): Flow<List<Activity>>

    fun getOne(id: String): Flow<Activity>

    suspend fun add(activity: Activity): Boolean
}