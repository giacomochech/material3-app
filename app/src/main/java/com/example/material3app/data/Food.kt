package com.example.material3app.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "food_table")
data class Food(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    //val date : Date,
    val nome: String,
    val calorie: Int
)


//data e id food

//data lista