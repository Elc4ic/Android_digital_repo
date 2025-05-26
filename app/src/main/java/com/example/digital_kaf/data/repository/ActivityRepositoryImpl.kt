package com.example.digital_kaf.data.repository

import com.example.digital_kaf.data.database.Database
import com.example.digital_kaf.domain.entities.Activity
import com.example.digital_kaf.domain.repository.ActivityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

class ActivityRepositoryImpl
@Inject constructor(
    private val database: Database,
) : ActivityRepository {

    override fun getAll(my: Boolean): Flow<List<Activity>> {
        return {
            if (my) {
                database.activityDao().getMyPagingSource("myid")
            } else {
                database.activityDao().getOtherPagingSource("myid_to_extinct")
            }
        }.asFlow()
    }

    override fun getOne(id: String): Flow<Activity> {
        return database.activityDao().getById(id)
    }

    override suspend fun add(activity: Activity): Boolean {
        database.activityDao().insert(activity)
        return true
    }

}