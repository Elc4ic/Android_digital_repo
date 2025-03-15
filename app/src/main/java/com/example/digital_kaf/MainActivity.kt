package com.example.digital_kaf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.digital_kaf.domain.NavItem
import com.example.digital_kaf.ui.screen.LoginScreen
import com.example.digital_kaf.ui.screen.RegistrationScreen
import com.example.digital_kaf.ui.screen.WelcomeScreen
import com.example.digital_kaf.ui.theme.Digital_kafTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            Digital_kafTheme {
                NavHost(navController, startDestination = NavItem.Login.route) {
                    composable(NavItem.Welcome.route) {
                        WelcomeScreen(navController)
                    }
                    composable(NavItem.Registration.route) {
                        RegistrationScreen(navController)
                    }
                    composable(NavItem.Login.route) {
                        LoginScreen(navController)
                    }
                }
            }
        }
    }
}