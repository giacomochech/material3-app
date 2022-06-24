package com.example.material3app

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//classe entita delle ricette
@Entity(tableName = "recipe_table")
data class Ricetta (
    @PrimaryKey(autoGenerate = true)
    val id : Int,

    @ColumnInfo
    var cover: Bitmap,

    @ColumnInfo
    var nome: String,

    @ColumnInfo
    var kcal: Int,

    @ColumnInfo
    var ingredienti: String,

    @ColumnInfo
    var descrizione: String,


)
