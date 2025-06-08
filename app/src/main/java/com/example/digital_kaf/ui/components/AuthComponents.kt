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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.digital_kaf.domain.entities.Gender
import com.example.digital_kaf.ui.theme.Typography
import com.example.digital_kaf.viewmodel.RegistrationViewModel

@Composable
fun LoginField(vm: RegistrationViewModel) {
    val ui = vm.ui.collectAsState()
    PrimaryTextField(
        value = vm.ui.value.login ?: "",
        onValueChange = {
            vm.setLogin(it)
        },
        label = { Text("Логин") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
        isError = ui.value.loginErr != null
    )
}

@Composable
fun PasswordField(vm: RegistrationViewModel) {
    val ui = vm.ui.collectAsState()
    PrimaryTextField(
        value = vm.ui.value.password ?: "",
        onValueChange = {
            vm.setPassword(it)
        },
        label = { Text("Пароль") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
        isPassword = false,
        isError = ui.value.passwordErr != null
    )
}

@Composable
fun RepeatPasswordField(vm: RegistrationViewModel) {
    val ui = vm.ui.collectAsState()
    PrimaryTextField(
        value = vm.ui.value.password ?: "",
        onValueChange = {
            vm.setPasswordRepeat(it)
        },
        label = { Text("Повторите пароль") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
        isPassword = true,
        isError = ui.value.passwordRepeatErr != null
    )
}


@Composable
fun NicknameField(vm: RegistrationViewModel) {
    val ui = vm.ui.collectAsState()
    PrimaryTextField(
        value = vm.ui.value.password ?: "",
        onValueChange = {
            vm.setNickname(it)
        },
        label = { Text("Имя") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
        isError = ui.value.nicknameErr != null,
    )
}

@Composable
fun GenderSector(vm: RegistrationViewModel) {
    val ui = vm.ui.collectAsState()
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
                checked = ui.value.gender == Gender.MALE,
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
                checked = ui.value.gender == Gender.FEMALE,
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
                checked = ui.value.gender == Gender.OTHER,
                onCheckedChange = {
                    vm.setGender(Gender.OTHER)
                }
            )
            Text(text = "Другое", style = Typography.bodySmall)
        }
    }
}