package com.example.material3app.data

import androidx.lifecycle.LiveData
import com.example.material3app.Cibo


class FoodRepository(private val foodDao:FoodDao)
{
    val readAllData: LiveData<List<Cibo>> = foodDao.readAllData()

    suspend fun readDaySumCal(): List<GionoSommaCal> {
        return foodDao.readDaySumCal()
    }

     fun readFoodInSpecifiedDay(date : String) : LiveData<List<Cibo>>{
        return foodDao.readFoodInSpecifiedDay(date)
    }

    suspend fun addFood(food : Cibo){
        foodDao.addFood(food)
    }

    fun delete(id:Int){
        foodDao.delete(id)
    }

}