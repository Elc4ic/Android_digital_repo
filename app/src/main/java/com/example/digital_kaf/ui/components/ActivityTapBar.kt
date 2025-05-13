package com.example.digital_kaf.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.digital_kaf.domain.entities.Activity
import com.example.digital_kaf.ui.theme.primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityTapBar(a: Activity?, onBackClick: () -> Unit) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Text(text = a?.activityType ?: "Error..")
                    Icons.Filled.Face
                }
                Row {
                    IconButton(onClick = {}) {
                        Icons.Default.Delete
                    }
                    IconButton(onClick = {}) {
                        Icons.Default.Share
                    }
                }
            }
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
        modifier = Modifier.padding(horizontal = 4.dp)
    )
}