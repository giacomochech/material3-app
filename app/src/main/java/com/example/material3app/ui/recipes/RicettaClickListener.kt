package com.example.material3app.ui.recipes

import android.view.View
import com.example.material3app.Ricetta

interface RicettaClickListener
{
    fun onClick(view: View, ricetta: Ricetta)
}