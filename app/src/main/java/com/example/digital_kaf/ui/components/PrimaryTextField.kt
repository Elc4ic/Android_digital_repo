package com.example.digital_kaf.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.digital_kaf.ui.theme.background
import com.example.digital_kaf.ui.theme.dark
import com.example.digital_kaf.ui.theme.line
import com.example.digital_kaf.ui.theme.primary

@Composable
fun PrimaryTextField(
    value: String,
    label: @Composable (String) -> Unit,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    isPassword: Boolean = false,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    var visible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
        label = { label },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = background,
            focusedBorderColor = primary,
            unfocusedBorderColor = line,
            unfocusedContainerColor = background,
            focusedLabelColor = primary,
            unfocusedLabelColor = line,
        ),
        isError = isError,
        visualTransformation = if (visible) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            if (isPassword) {
                if (!visible) {
                    Icon(
                        imageVector = Icons.Default.AccountBox,
                        contentDescription = "Hide",
                        tint = line,
                        modifier = Modifier.clickable(onClick = { visible = false })
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Show",
                        tint = line,
                        modifier = Modifier.clickable(onClick = { visible = true })
                    )
                }
            }
        },
        keyboardOptions = keyboardOptions
    )
}

@Preview
@Composable
fun fieldPreview() {
    PrimaryTextField(
        value = "",
        label = { Text("vhsdvbsd") },
        onValueChange = {},
        modifier = Modifier.fillMaxWidth(),
        isPassword = true
    )
}

