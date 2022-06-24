@file:Suppress("unused")

package com.example.material3app.ui

import android.app.Application
import com.google.android.material.color.DynamicColors
//classe per implementare i colori dinamici

class Material3AppTheme : Application(){
    override fun onCreate(){
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}