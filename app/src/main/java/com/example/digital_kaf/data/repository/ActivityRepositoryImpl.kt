package com.example.digital_kaf.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.digital_kaf.data.database.Database
import com.example.digital_kaf.domain.entities.Activity
import com.example.digital_kaf.domain.repository.ActivityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ActivityRepositoryImpl
@Inject
constructor(
    private val database: Database,
) : ActivityRepository {
    companion object {
        private const val PAGE_SIZE = 20
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun watchPages(my: Boolean): Flow<PagingData<Activity>> {
        val pagingSourceFactory = {
            if (my) {
                database.activityDao().getMyPagingSource("my")
            } else {
                database.activityDao().getOtherPagingSource("Other")
            }
        }
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            remoteMediator =
            ActiveRemoteMediator(
                my = my,
                database = database,
            ),
            pagingSourceFactory = pagingSourceFactory,
        ).flow.flowOn(Dispatchers.Default)
    }

    override fun watchOne(id: String): Flow<Activity> {
        return database.activityDao().getById(id)
    }

    override fun addActivity(activity: Activity) {
        TODO("Not yet implemented")
    }

    override fun updateActivity(activity: Activity) {
        TODO("Not yet implemented")
    }
}