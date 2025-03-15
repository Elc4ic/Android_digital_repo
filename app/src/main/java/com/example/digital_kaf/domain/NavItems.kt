package com.example.digital_kaf.domain

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavItem(
    val route: String,
    val icon: ImageVector
) {
    object Welcome : NavItem("welcome", Icons.Filled.Home)
    object Login : NavItem("login", Icons.Filled.AccountBox)
    object Registration : NavItem("registration", Icons.Filled.AccountBox)
    object Home : NavItem("home", Icons.Filled.AccountBox)
    object Profile : NavItem("profile", Icons.Filled.AccountBox)
}