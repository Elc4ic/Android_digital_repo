package com.example.digital_kaf.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.digital_kaf.domain.entities.Activity
import com.example.digital_kaf.domain.entities.User
import javax.inject.Singleton

@Singleton
@Database(entities = [Activity::class, User::class], version = 1)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {

    abstract fun activityDao(): ActivityDao

    abstract fun userDao(): UserDao
}