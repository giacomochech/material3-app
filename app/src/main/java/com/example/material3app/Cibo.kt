package com.example.material3app

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
//classe entità
@Entity(tableName = "food_table")
data class Cibo(

    @PrimaryKey(autoGenerate = true)
    private val id:Int,

    @ColumnInfo
    private val date : String,

    @ColumnInfo
    private var name : String,

    @ColumnInfo
    private var Kcal : Int)
{
    fun getName(): String {
         return name
    }

    fun getKcal(): Int {
        return Kcal
    }

    fun getId(): Int {
        return id
    }

    fun getDate(): String {
        return date
    }
}