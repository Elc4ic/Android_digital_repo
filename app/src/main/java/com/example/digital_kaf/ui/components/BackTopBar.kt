package com.example.digital_kaf.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.digital_kaf.ui.theme.primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarWithBackArrow(title: String, onBackClick: () -> Unit) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    tint = primary,
                    contentDescription = "Назад"
                )
            }
        },
        modifier = Modifier.padding(horizontal = 8.dp)
    )
}