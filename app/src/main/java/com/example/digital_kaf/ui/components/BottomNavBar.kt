package com.example.digital_kaf.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.digital_kaf.ui.screen.Route
import com.example.digital_kaf.ui.theme.primary

data class NavBarItem(val icon: ImageVector, val label: String, val route: String)


val BottomNavItems = listOf(
    NavBarItem(
        label = "Activities",
        icon = Icons.Filled.Search,
        route = Route.Activities.route
    ),
    NavBarItem(
        label = "Profile",
        icon = Icons.Filled.Person,
        route = Route.Profile.route
    )
)

@Composable
fun ScaffoldWithNavBar(
    navController: NavController,
    content: @Composable (() -> Unit),
) {
    Scaffold(
        bottomBar = { BottomBarNavigation(navController) },
        content = { content }
    )
}

@Composable
fun BottomBarNavigation(navController: NavController) {
    NavigationBar(containerColor = primary) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        BottomNavItems.forEach { navItem ->
            NavigationBarItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.route)
                },
                icon = {
                    Icon(imageVector = navItem.icon, contentDescription = navItem.label)
                },
                label = {
                    Text(text = navItem.label)
                },
                alwaysShowLabel = false,

                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    indicatorColor = Color(0xff9578e8)
                )
            )
        }
    }
}