package com.example.digital_kaf.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.digital_kaf.data.TimeParser
import com.example.digital_kaf.ui.screen.Routes
import com.example.digital_kaf.viewmodel.AddViewModel

@Composable
fun TimerBottomSheet(
    navController: NavController?,
    avm: AddViewModel = viewModel(),
) {
    val distance by avm.distance.collectAsState()
    val time by avm.timeElapsed.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("$distance")
            Text(TimeParser.parseTimer(time))
        }
        IconButton(
            onClick = {
                avm.saveActivity()
                navController?.navigate(Routes.Activities.route)
            }
        ) {
            Icons.Default.Done
        }
    }
}