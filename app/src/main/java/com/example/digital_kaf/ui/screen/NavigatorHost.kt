package com.example.digital_kaf.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.digital_kaf.ui.components.BottomBarNavigation
import com.example.digital_kaf.viewmodel.ActivityViewModel
import com.example.digital_kaf.viewmodel.ActivityViewModel.Companion.provideFactory
import com.example.digital_kaf.viewmodel.AddViewModel
import com.example.digital_kaf.viewmodel.RegistrationViewModel


sealed class Routes(val route: String) {
    object Welcome : Routes("welcome")
    object Login : Routes("login")
    object Registration : Routes("registration")
    object Activities : Routes("activity")
    object Profile : Routes("profile")
    object Add : Routes("add")
    class Note(id: String) : Routes("note/${id}")

    companion object {
        val routes = listOf(Activities, Profile, Note(""))

        data class NavBarItem(val icon: ImageVector, val label: String, val route: String)

        val bottomNavItems = listOf(
            NavBarItem(
                label = "Activities",
                icon = Icons.Filled.Search,
                route = Activities.route
            ),
            NavBarItem(
                label = "Profile",
                icon = Icons.Filled.Person,
                route = Profile.route
            )
        )
    }
}

@Composable
fun NavigatorHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    avmf: ActivityViewModel.AssistedFactory,
) {
    val currentRoute = currentRoute(navController)

    Scaffold(
        bottomBar = {
            if (currentRoute in Routes.routes.map { it.route } || currentRoute?.startsWith("note") == true) {
                BottomBarNavigation(navController)
            }
        }
    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = Routes.Login.route,
            modifier = modifier.padding(paddingValues),
        ) {
            //NoNavBar
            composable(Routes.Welcome.route) { WelcomeScreen(navController) }
            composable(Routes.Login.route) {
                LoginScreen(
                    navController,
                    hiltViewModel<RegistrationViewModel>()
                )
            }
            composable(Routes.Registration.route) {
                RegistrationScreen(
                    navController,
                    hiltViewModel<RegistrationViewModel>()
                )
            }
            composable(Routes.Add.route) {
                AddScreen(navController, hiltViewModel<AddViewModel>())
            }
            //WithNavBar
            composable(Routes.Activities.route) { MainScreen(navController) }
            composable(Routes.Profile.route) {
                ProfileScreen(
                    navController,
                    hiltViewModel<RegistrationViewModel>()
                )
            }
            composable(
                Routes.Note("{id}").route,
                arguments = listOf(navArgument("id") { type = NavType.StringType }),
            ) {
                val activityId = it.arguments?.getString("id")!!
                ActivityScreen(
                    viewModel = viewModel(factory = avmf.provideFactory(activityId))
                )
            }
        }
    }
}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}