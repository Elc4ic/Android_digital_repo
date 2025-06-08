package com.example.digital_kaf.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.digital_kaf.R
import com.example.digital_kaf.ui.components.LoginField
import com.example.digital_kaf.ui.components.PasswordField
import com.example.digital_kaf.ui.components.PrimaryButton
import com.example.digital_kaf.ui.components.TopBarWithBackArrow
import com.example.digital_kaf.viewmodel.RegistrationViewModel

@Composable
fun LoginScreen(
    navController: NavController?,
    vm: RegistrationViewModel = viewModel(),
) {
    val ui = vm.ui.collectAsState()
    Scaffold(
        topBar = {
            TopBarWithBackArrow(
                title = stringResource(R.string.topbar_log_in),
                onBackClick = {})
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(all = 16.dp),
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        bitmap = ImageBitmap.imageResource(R.drawable.bicycle_image),
                        contentDescription = "WelcomeImage"
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Box(
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxWidth()
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        LoginField(vm)
                        PasswordField(vm)
                        PrimaryButton(
                            onClick = {
                                vm.validateLogin()
                                if (ui.value.isFormValid) {
                                    vm.login()
                                    if (vm.regUser.value != null) {
                                        navController?.navigate(Routes.Activities.route)
                                    }
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Text(stringResource(R.string.login))
                        }
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun LoginPreview() {
    LoginScreen(null)
}