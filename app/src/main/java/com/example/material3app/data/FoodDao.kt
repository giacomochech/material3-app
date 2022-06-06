package com.example.material3app.data

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Dao

@Dao
interface FoodDao
{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFood(food:Food)

    @Query("SELECT * FROM food_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Food>>
}