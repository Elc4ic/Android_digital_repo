package com.example.digital_kaf.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.digital_kaf.ui.components.ActivityCard
import com.example.digital_kaf.viewmodel.ActivityListViewModel

@Composable
fun MyActivitiesScreen(
    navController: NavController,
    viewModel: ActivityListViewModel = viewModel(),
) {
    val context = LocalContext.current
    val activities = viewModel.pages.collectAsLazyPagingItems()
    val error by viewModel.error.collectAsState()
    val lazyListState = rememberLazyListState()

    LaunchedEffect(error) {
        if (!error.isNullOrEmpty()) {
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
            viewModel.clearError()
        }
    }

    LazyColumn(state = lazyListState, modifier = Modifier.fillMaxSize()) {
        items(
            count = activities.itemCount,
            key = activities.itemKey { it.id },
            contentType = activities.itemContentType { "activity" },
        ) { idx ->
            val item = activities[idx]!!
            ActivityCard(
                activity = item,
                onClick = { navController.navigate(Route.Note(item.id.toString()).route) },
            )
        }
    }
}