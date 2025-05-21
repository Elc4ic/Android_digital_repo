package com.example.digital_kaf.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.digital_kaf.domain.entities.Activity
import com.example.digital_kaf.ui.components.ActivityCard
import com.example.digital_kaf.viewmodel.ActivityListViewModel

@Composable
fun OtherActivityScreen(
    navController: NavController,
    viewModel: ActivityListViewModel = viewModel()
) {
    val context = LocalContext.current
    val activities = viewModel.otherPages.collectAsState()
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
            count = activities.value.size,
            key = { activities.value[it].id },
            contentType = { Activity::class },
        ) { idx ->
            val item = activities.value[idx]
            ActivityCard(
                activity = item,
                onClick = { navController.navigate(Routes.Note(item.id.toString()).route) },
            )
        }
    }
}