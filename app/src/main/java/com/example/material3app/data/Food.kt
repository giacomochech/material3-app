package com.example.material3app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_table")
data class Food(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val date : String,
    val nome: String,
    val calorie: Int
)


//data e id food

//data lista