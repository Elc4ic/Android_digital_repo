package com.example.digital_kaf.ui.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.digital_kaf.data.TimeParser
import com.example.digital_kaf.domain.entities.Activity
import com.example.digital_kaf.ui.components.ActivityTapBar
import com.example.digital_kaf.ui.theme.Typography
import com.example.digital_kaf.viewmodel.ActivityViewModel

@Composable
fun ActivityScreen(
    viewModel: ActivityViewModel = viewModel(),
) {
    val activity by viewModel.activity.collectAsState()
    val duration = (activity?.endTime ?: 0) - (activity?.startTime ?: 0)
    Scaffold(
        modifier = Modifier.padding(24.dp),
        topBar = {
            ActivityTapBar(
                a = activity,
                onBackClick = { }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Column {
                    Text("${activity?.distance} км", style = Typography.titleLarge)
                    Text(
                        TimeParser.timeBack(activity?.endTime ?: System.currentTimeMillis()),
                        style = Typography.labelMedium
                    )
                }
                Column {
                    Text("$duration", style = Typography.bodyMedium)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Text("Старт ${activity?.startTime} ", style = Typography.titleMedium)
                        Text("|", style = Typography.titleMedium)
                        Text("Финиш${activity?.endTime}", style = Typography.titleMedium)
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(2.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
                        .padding(4.dp)
                ) {
                    Text("${activity?.comment}", style = Typography.labelLarge)
                }
            }
        }
    )
}
