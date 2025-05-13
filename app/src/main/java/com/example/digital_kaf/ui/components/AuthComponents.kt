package com.example.digital_kaf.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.digital_kaf.domain.entities.Gender
import com.example.digital_kaf.ui.theme.Typography
import com.example.digital_kaf.viewmodel.RegistrationViewModel

@Composable
fun loginField(vm: RegistrationViewModel) {
    PrimaryTextField(
        value = vm.login.value,
        onValueChange = {
            vm.login.value = it
            vm.validateLogin()
        },
        label = { Text("Логин") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
    )
}

@Composable
fun passwordField(vm: RegistrationViewModel) {
    PrimaryTextField(
        value = vm.password.value,
        onValueChange = {
            vm.password.value = it
            vm.validatePassword()
        },
        label = { Text("Пароль") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
        isPassword = true
    )
}

@Composable
fun repeatPasswordField(vm: RegistrationViewModel) {
    PrimaryTextField(
        value = vm.repeatPassword.value,
        onValueChange = {
            vm.repeatPassword.value = it
            vm.validateRepeatPassword()
        },
        label = { Text("Повторите пароль") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
        isPassword = true
    )
}


@Composable
fun nicknameField(vm: RegistrationViewModel) {
    PrimaryTextField(
        value = vm.nickname.value,
        onValueChange = {
            vm.nickname.value = it
            vm.enabledRegisterButton()
        },
        label = { Text("Имя") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
    )
}

@Composable
fun genderSector(vm: RegistrationViewModel) {
    Column {
        Text(
            text = "Выберите пол:",
            modifier = Modifier.padding(bottom = 8.dp),
            style = Typography.bodyMedium
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.padding(vertical = 2.dp)
        ) {
            Checkbox(
                checked = vm.gender.value == Gender.MALE,
                onCheckedChange = {
                    vm.setGender(Gender.MALE)
                }
            )
            Text(text = "Мужской", style = Typography.bodySmall)
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.padding(vertical = 2.dp)
        ) {
            Checkbox(
                checked = vm.gender.value == Gender.FEMALE,
                onCheckedChange = {
                    vm.setGender(Gender.FEMALE)
                }
            )
            Text(text = "Женский", style = Typography.bodySmall)
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.padding(vertical = 2.dp)
        ) {
            Checkbox(
                checked = vm.gender.value == Gender.OTHER,
                onCheckedChange = {
                    vm.setGender(Gender.OTHER)
                }
            )
            Text(text = "Другое", style = Typography.bodySmall)
        }
    }
}