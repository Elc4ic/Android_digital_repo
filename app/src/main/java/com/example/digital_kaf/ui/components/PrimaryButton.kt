package com.example.digital_kaf.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.digital_kaf.ui.theme.Typography
import com.example.digital_kaf.ui.theme.background
import com.example.digital_kaf.ui.theme.line
import com.example.digital_kaf.ui.theme.primary

@Composable
fun PrimaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = primary,
            contentColor = background,
            disabledContainerColor = line,
        ),
        shape = MaterialTheme.shapes.extraSmall,
        contentPadding = PaddingValues(12.dp)
    ) {
        ProvideTextStyle(
            Typography.titleMedium,
            content = content
        )
    }
}

@Preview
@Composable
fun buttonPreview() {
    PrimaryButton(onClick = {}, modifier = Modifier.fillMaxWidth()) {
        Text("Button sjkbfhbr")
    }
}