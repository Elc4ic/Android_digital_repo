package com.example.digital_kaf.di

import android.content.Context
import androidx.room.Room
import com.example.digital_kaf.data.database.ActivityDao
import com.example.digital_kaf.data.database.Converters
import com.example.digital_kaf.data.database.Database
import com.example.digital_kaf.data.database.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import kotlinx.serialization.json.Json

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideJson() = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context, json: Json): Database {
        val listConverters = Converters.create(json)
        return Room.databaseBuilder(context, Database::class.java, "cache.db")
            .addTypeConverter(listConverters)
            .build()
    }

    @Singleton
    @Provides
    fun provideActivityDao(database: Database): ActivityDao {
        return database.activityDao()
    }

    @Singleton
    @Provides
    fun provideUserDao(database: Database): UserDao {
        return database.userDao()
    }

}