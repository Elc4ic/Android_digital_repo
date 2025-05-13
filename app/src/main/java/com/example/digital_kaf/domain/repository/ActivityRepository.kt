package com.example.digital_kaf.domain.repository

import androidx.paging.PagingData
import com.example.digital_kaf.domain.entities.Activity
import kotlinx.coroutines.flow.Flow

interface ActivityRepository {
    fun watchPages(my: Boolean): Flow<PagingData<Activity>>

    fun watchOne(id: String): Flow<Activity>

    fun addActivity(activity: Activity)

    fun updateActivity(activity: Activity)
}