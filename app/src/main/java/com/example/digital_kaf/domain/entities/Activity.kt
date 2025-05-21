package com.example.digital_kaf.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.w3c.dom.Comment
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.UUID

@Entity(tableName = "activities")
data class Activity(
    @PrimaryKey val id: UUID,
    val distance: Float,
    @ColumnInfo(name = "activity_type") val activityType: String,
    @ColumnInfo(name = "start_time") val startTime: Long,
    @ColumnInfo(name = "end_time") val endTime: Long,
    @ColumnInfo(name = "user_id") val userId: UUID,
    val comment: String
)