package com.example.digital_kaf.ui.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.digital_kaf.data.Consts
import com.example.digital_kaf.viewmodel.AddViewModel

@Composable
fun TypeBottomSheet(
    avm: AddViewModel = viewModel(),
) {
    val type = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Погнали?:)", style = MaterialTheme.typography.headlineSmall)
        Row(
            modifier = Modifier.horizontalScroll(ScrollState(0))
        ) {
            Consts.sportsType.forEach {
                Card(
                    onClick = {
                        type.value = it
                    }
                ) {
                    Text(it)
                }
            }
        }
        Button(
            onClick = {
                avm.setType(type.value)
            }
        ) {
            Text("Начать")
        }
    }
}

