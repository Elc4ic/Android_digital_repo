package com.example.digital_kaf.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.digital_kaf.ui.screen.NavigatorHost
import com.example.digital_kaf.ui.theme.Digital_kafTheme
import com.example.digital_kaf.viewmodel.ActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var assisted: ActivityViewModel.AssistedFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()

            Digital_kafTheme {
                NavigatorHost(
                    avmf = assisted,
                    navController = navController
                )
            }
        }
    }
}