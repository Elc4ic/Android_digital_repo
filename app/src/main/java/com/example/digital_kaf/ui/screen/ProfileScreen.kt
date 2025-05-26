package com.example.digital_kaf.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.room.util.TableInfo
import com.example.digital_kaf.ui.components.PrimaryButton
import com.example.digital_kaf.ui.components.ProfileTabBar
import com.example.digital_kaf.ui.components.loginField
import com.example.digital_kaf.ui.components.nicknameField
import com.example.digital_kaf.viewmodel.RegistrationViewModel

@Composable
fun ProfileScreen(
    navController: NavController? = null,
    vm: RegistrationViewModel = viewModel(),
) {
    Scaffold(
        topBar = { ProfileTabBar() },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(8.dp)
                ) {
                    loginField(vm)
                    nicknameField(vm)
                    TextButton(onClick = {}) {
                        Text("Изменить пароль")
                    }
                }
                PrimaryButton(onClick = {}) { Text("Выйти") }
            }
        }
    )
}