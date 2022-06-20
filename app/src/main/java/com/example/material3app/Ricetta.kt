package com.example.material3app

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey


const val RICETTA_ID_EXTRA: String ="ricettaExtra"


data class Ricetta (
    @PrimaryKey(autoGenerate = true)
    val id : Int,

    @ColumnInfo
    var cover: Int,

    @ColumnInfo
    var nome: String,

    @ColumnInfo
    var kcal: Int,

    @ColumnInfo
    var ingredienti: String,

    @ColumnInfo
    var descrizione: String,


)