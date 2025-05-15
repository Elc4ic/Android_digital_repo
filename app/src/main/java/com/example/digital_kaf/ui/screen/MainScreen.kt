package com.example.digital_kaf.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.digital_kaf.viewmodel.ActivityListViewModel

@Composable
fun MainScreen(navController: NavController) {
    var state by remember { mutableIntStateOf(0) }
    val titles = listOf("Моя", "Пользователей")
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Routes.Add.route)
            }) {
                Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "add")
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            TabRow(selectedTabIndex = state) {
                titles.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(title) },
                        selected = state == index,
                        onClick = { state = index },
                    )
                }
            }
            when (state) {
                0 ->
                    MyActivitiesScreen(
                        navController = navController,
                        viewModel = hiltViewModel<ActivityListViewModel>(),
                    )

                1 -> OtherActivityScreen(
                    navController = navController,
                    viewModel = hiltViewModel<ActivityListViewModel>(),
                )
            }
        }
    }

}