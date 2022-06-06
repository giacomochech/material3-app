package com.example.material3app.data

import androidx.lifecycle.LiveData
import java.util.concurrent.Executors

class FoodRepository(private val foodDao:FoodDao)
{
    val readAllData: LiveData<List<Food>> = foodDao.readAllData()


     fun readFoodInSpecifiedDay(date : String) : LiveData<List<Food>>{
        return foodDao.readFoodInSpecifiedDay(date)
    }

    suspend fun addFood(food : Food){
        foodDao.addFood(food)
    }
}