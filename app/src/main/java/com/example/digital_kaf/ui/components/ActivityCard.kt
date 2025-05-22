package com.example.digital_kaf.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.digital_kaf.data.TimeParser
import com.example.digital_kaf.domain.entities.Activity
import com.example.digital_kaf.ui.theme.Typography
import com.example.digital_kaf.ui.theme.dark
import java.time.Duration
import java.util.UUID

@SuppressLint("NewApi")
@Composable
fun ActivityCard(activity: Activity, onClick: () -> Unit) {
    val duration = activity.endTime - activity.startTime
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("${activity.distance} км", style = Typography.titleMedium)
            Text(TimeParser.getDuration(duration), style = Typography.bodyMedium)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row {
                    Text(activity.activityType, style = Typography.bodyMedium)
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "decdcd",
                        tint = dark
                    )
                }
                Text(TimeParser.timeBack(activity.endTime), style = Typography.labelMedium)
            }
        }
    }
}

@SuppressLint("NewApi")
@Preview
@Composable
fun NotePreview() {
    val note = Activity(
        id = UUID.randomUUID(),
        distance = 13f,
        activityType = "cdjcnc",
        startTime = System.currentTimeMillis() - Duration.ofHours(3).toMillis(),
        endTime = System.currentTimeMillis() - Duration.ofHours(1).toMillis(),
        userId = UUID.randomUUID(),
        comment = ""
    )
    ActivityCard(activity = note, onClick = {})
}



