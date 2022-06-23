package com.example.material3app.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.material3app.Cibo
import com.example.material3app.Ricetta

@Dao
interface FoodDao
{
    //Query dell'entità Cibo

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFood(food: Cibo)

    @Query("SELECT * FROM food_table ORDER BY id ASC")
    fun readAllCibo(): LiveData<List<Cibo>>

    @Query("SELECT * FROM food_table WHERE date = :date ")
    fun readFoodInSpecifiedDay(date: String): LiveData<List<Cibo>>

    @Query("DELETE FROM food_table WHERE id = :id")
    fun deleteCibo(id:Int)

    //Query dell'entità Ricetta

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRicetta(ricetta: Ricetta)

    @Query("SELECT * FROM recipe_table WHERE id = :id")
    fun selectRicettabyId(id: Int): LiveData<Ricetta>

    @Query("SELECT * FROM recipe_table ORDER BY id ASC")
    fun readAllRicetta(): LiveData<List<Ricetta>>

    @Query("DELETE FROM recipe_table WHERE id = :id")
    fun deleteRicetta(id:Int)

    @Query("SELECT date as giorno, SUM(Kcal) as somma FROM food_table GROUP BY date")
    suspend fun readDaySumCal(): List<GionoSommaCal>

}