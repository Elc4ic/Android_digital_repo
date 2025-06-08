package com.example.digital_kaf.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.digital_kaf.R
import com.example.digital_kaf.ui.components.GenderSector
import com.example.digital_kaf.ui.components.LoginField
import com.example.digital_kaf.ui.components.NicknameField
import com.example.digital_kaf.ui.components.PasswordField
import com.example.digital_kaf.ui.components.PrimaryButton
import com.example.digital_kaf.ui.components.RepeatPasswordField
import com.example.digital_kaf.ui.components.TopBarWithBackArrow
import com.example.digital_kaf.ui.theme.Typography
import com.example.digital_kaf.viewmodel.RegistrationViewModel

@Composable
fun RegistrationScreen(
    navController: NavController? = null,
    vm: RegistrationViewModel = viewModel(),
) {
    val ui = vm.ui.collectAsState()

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
                LoginField(vm)
                NicknameField(vm)
                PasswordField(vm)
                RepeatPasswordField(vm)
                GenderSector(vm)
                PrimaryButton(
                    onClick = {
                        vm.validateRegister()
                        if (ui.value.isFormValid) {
                            vm.register()
                            if (vm.regUser.value != null) navController?.navigate(Routes.Activities.route)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
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