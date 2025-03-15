package com.example.digital_kaf.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.digital_kaf.R
import com.example.digital_kaf.domain.NavItem
import com.example.digital_kaf.ui.components.TopBarWithBackArrow
import com.example.digital_kaf.ui.theme.Typography

@Composable
fun RegistrationScreen(
    navController: NavController? = null,
) {
    var login by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }
    var isMale by remember { mutableStateOf(false) }
    var isFemale by remember { mutableStateOf(false) }
    var isOther by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopBarWithBackArrow(title = stringResource(R.string.topbar_sign), onBackClick = {}) },
        content = { padding ->
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(all = 16.dp)
            ) {
                TextField(
                    value = login,
                    onValueChange = { login = it },
                    label = { Text("Логин") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Имя") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Пароль") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = repeatPassword,
                    onValueChange = { repeatPassword = it },
                    label = { Text("Повторитепароль") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Выберите пол:",
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Checkbox(
                        checked = isMale,
                        onCheckedChange = { isChecked ->
                            isMale = isChecked
                            if (isChecked) {
                                isFemale = false
                                isOther = false
                            }
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Мужской")
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Checkbox(
                        checked = isFemale,
                        onCheckedChange = { isChecked ->
                            isFemale = isChecked
                            if (isChecked) {
                                isMale = false
                                isOther = false
                            }
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Женский")
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Checkbox(
                        checked = isOther,
                        onCheckedChange = { isChecked ->
                            isOther = isChecked
                            if (isChecked) {
                                isMale = false
                                isOther = false
                            }
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Другое")
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        navController?.navigate(NavItem.Home.route)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(R.string.sign_up))
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(stringResource(R.string.user_agreement), style = Typography.bodySmall)
            }
        }
    )
}

@Preview
@Composable
fun RegistrationPreview() {
    RegistrationScreen()
}