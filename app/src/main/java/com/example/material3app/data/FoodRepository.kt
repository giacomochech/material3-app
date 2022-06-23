package com.example.material3app.data

import androidx.lifecycle.LiveData
import com.example.material3app.Cibo
import com.example.material3app.Ricetta


class FoodRepository(private val foodDao:FoodDao) {
    val readAllCibo: LiveData<List<Cibo>> = foodDao.readAllCibo()

    suspend fun readDaySumCal(): List<GionoSommaCal> {
        return foodDao.readDaySumCal()
    }


    //Cibo
    fun readFoodInSpecifiedDay(date: String): LiveData<List<Cibo>> {
        return foodDao.readFoodInSpecifiedDay(date)
    }

    suspend fun addFood(food: Cibo) {
        foodDao.addFood(food)
    }

    fun deleteCibo(id: Int) {
        foodDao.deleteCibo(id)
    }

    //Ricetta
    suspend fun addRicetta(ricetta: Ricetta) {
        foodDao.addRicetta(ricetta)
    }

    fun selectRicettabyId(id: Int): LiveData<Ricetta> {
        return foodDao.selectRicettabyId(id)
    }

    fun readAllRicetta(): LiveData<List<Ricetta>> {
        return foodDao.readAllRicetta()
    }

    fun deleteRicetta(id: Int) {
        foodDao.deleteRicetta(id)
    }
}