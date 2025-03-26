package com.example.digital_kaf.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.digital_kaf.R
import com.example.digital_kaf.domain.NavItem
import com.example.digital_kaf.ui.components.PrimaryButton
import com.example.digital_kaf.ui.components.TopBarWithBackArrow
import com.example.digital_kaf.ui.components.genderSector
import com.example.digital_kaf.ui.components.loginField
import com.example.digital_kaf.ui.components.nicknameField
import com.example.digital_kaf.ui.components.passwordField
import com.example.digital_kaf.ui.components.repeatPasswordField
import com.example.digital_kaf.ui.theme.Typography
import com.example.digital_kaf.viewmodel.RegistrationViewModel

@Composable
fun RegistrationScreen(
    navController: NavController? = null,
    vm: RegistrationViewModel = viewModel()
) {
    var checked by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopBarWithBackArrow(
                title = stringResource(R.string.topbar_sign),
                onBackClick = { navController?.popBackStack() })
        },
        content = { padding ->
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(all = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                loginField(vm)
                nicknameField(vm)
                passwordField(vm)
                repeatPasswordField(vm)
                genderSector(vm)
                PrimaryButton(
                    onClick = {
                        navController?.navigate(NavItem.Home.route)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = vm.isEnabledRegisterButton.value
                ) {
                    Text(stringResource(R.string.sign_up))
                }
                Text(
                    stringResource(R.string.user_agreement),
                    style = Typography.titleSmall
                )
            }
        }
    )
}

@Preview
@Composable
fun RegistrationPreview() {
    RegistrationScreen()
}