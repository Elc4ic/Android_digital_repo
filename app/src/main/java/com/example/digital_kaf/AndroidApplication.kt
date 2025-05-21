package com.example.digital_kaf

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class AndroidApplication : Application() {
    override fun onCreate() {
        super.onCreate()

    }
}