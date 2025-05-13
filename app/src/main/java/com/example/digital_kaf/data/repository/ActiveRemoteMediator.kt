package com.example.digital_kaf.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.digital_kaf.data.database.Database
import com.example.digital_kaf.domain.entities.Activity

@OptIn(ExperimentalPagingApi::class)
class ActiveRemoteMediator(
    private val my: Boolean,
    private val database: Database,
) : RemoteMediator<Int, Activity>() {
    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

    @OptIn(ExperimentalPagingApi::class)
    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Activity>,
    ): MediatorResult {
        val page =
            when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextKey?.minus(1) ?: STARTING_PAGE_INDEX
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    remoteKeys?.prevKey
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    remoteKeys?.nextKey
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                }
            }

        return MediatorResult.Success(false)

//        return try {
//            val resp =
//                stationAPI.getManyStations(
//                    getManyStationsRequest {
//                        this.query = search
//                        this.page = page.toLong()
//                        this.limit = state.config.pageSize.toLong()
//                        token = jwt.token
//                    }
//                )
//            val items = resp.stations.dataList
//            val endOfPaginationReached = items.isEmpty()
//            database.withTransaction<Unit> {
//                if (loadType == LoadType.REFRESH) {
//                    database.remoteKeysDao().clear()
//                    database.activityDao().clear()
//                }
//                val prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1
//                val nextKey = if (endOfPaginationReached) null else page + 1
//                val keys =
//                    items.map { RemoteKeys(id = it.id, prevKey = prevKey, nextKey = nextKey) }
//                database.remoteKeysDao().insert(keys)
//                database.activityDao().insert(items.map { Station.fromGrpc(it) })
//            }
//            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
//        } catch (exception: IOException) {
//            MediatorResult.Error(exception)
//        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Activity>,
    ): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                database.remoteKeysDao().findById(id.toString())
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Activity>): RemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()?.let { item ->
                database.remoteKeysDao().findById(item.id.toString())
            }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Activity>): RemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()?.let { item ->
                database.remoteKeysDao().findById(item.id.toString())
            }
    }
}