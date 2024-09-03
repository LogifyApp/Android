package com.example.logify

import android.app.Application
import android.content.pm.PackageManager
import com.google.android.libraries.places.api.Places
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LogifyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Retrieve the API key inside onCreate(), after the context is available
        val apiKey = applicationContext.packageManager
            .getApplicationInfo(applicationContext.packageName, PackageManager.GET_META_DATA)
            .metaData
            .getString("com.google.android.geo.API_KEY")

        // Initialize Places with the API key
        if (!Places.isInitialized() && apiKey != null) {
            Places.initialize(applicationContext, apiKey)
        }
    }
}