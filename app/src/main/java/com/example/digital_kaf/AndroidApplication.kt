package com.example.digital_kaf

import android.app.Application
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import org.osmdroid.config.Configuration
import org.osmdroid.library.BuildConfig
import timber.log.Timber

@HiltAndroidApp
class AndroidApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Configuration.getInstance().userAgentValue = BuildConfig.LIBRARY_PACKAGE_NAME

    }
}