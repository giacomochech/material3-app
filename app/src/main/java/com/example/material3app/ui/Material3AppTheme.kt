package com.example.material3app.ui

import android.app.Application
import com.google.android.material.color.DynamicColors

class Material3AppTheme : Application(){
    override fun onCreate(){
        super.onCreate()
        // Apply dynamic color
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}