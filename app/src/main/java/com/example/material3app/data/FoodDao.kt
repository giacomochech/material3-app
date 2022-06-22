package com.example.material3app.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.material3app.Cibo

@Dao
interface FoodDao
{
    @Insert(onConflict = OnConflictStrategy.IGNORE)

    suspend fun addFood(food: Cibo)

    @Query("SELECT * FROM food_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Cibo>>

    @Query("SELECT * FROM food_table WHERE date = :date ")
    fun readFoodInSpecifiedDay(date: String): LiveData<List<Cibo>>

    @Query("DELETE FROM food_table WHERE id = :id")
    fun delete(id:Int)

    @Query("SELECT date as giorno, SUM(Kcal) as somma FROM food_table GROUP BY date")
    suspend fun readDaySumCal(): List<GionoSommaCal>

}