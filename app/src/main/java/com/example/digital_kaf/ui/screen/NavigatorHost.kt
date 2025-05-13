package com.example.digital_kaf.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.digital_kaf.ui.components.ScaffoldWithNavBar
import com.example.digital_kaf.viewmodel.ActivityListViewModel
import com.example.digital_kaf.viewmodel.ActivityViewModel
import com.example.digital_kaf.viewmodel.ActivityViewModel.Companion.provideFactory


sealed class Route(val route: String) {
    object Welcome : Route("welcome")
    object Login : Route("login")
    object Registration : Route("registration")
    object Activities : Route("activity")
    object Profile : Route("profile")
    class Note(id: String) : Route("note/${id}")
}

@Composable
fun NavigatorHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    avmf: ActivityViewModel.AssistedFactory,
) {
    NavHost(
        navController = navController,
        startDestination = Route.Login.route,
        modifier = modifier,
    ) {
        composable(Route.Welcome.route) {
            LoginScreen(navController = navController)
        }
        composable(Route.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(Route.Registration.route) {
            LoginScreen(navController = navController)
        }
        composable(Route.Activities.route) {
            var state by remember { mutableStateOf(0) }
            val titles = listOf("My", "Other")
            ScaffoldWithNavBar(navController = navController) {
                Column {
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
        composable(
            Route.Note("{id}").route,
            arguments = listOf(navArgument("id") { type = NavType.StringType }),
        ) {
            val activityId = it.arguments?.getString("id")!!
            ActivityScreen(
                viewModel = viewModel(factory = avmf.provideFactory(activityId))
            )
        }
    }

}