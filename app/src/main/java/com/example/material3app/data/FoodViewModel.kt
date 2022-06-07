package com.example.material3app.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FoodViewModel(application: Application): AndroidViewModel(application)
{
    private val readAllData:LiveData<List<Food>>

    private val repository: FoodRepository

    init {
        val foodDao = FoodDatabase.getDatabase(application).foodDao()
        repository = FoodRepository(foodDao)
        readAllData = repository.readAllData

    }

    fun addFood(food: Food){
        viewModelScope.launch(Dispatchers.IO){
            repository.addFood(food)
        }
    }

    fun readFoodInSpecifiedDay(date : String) : LiveData<List<Food>>{
        return repository.readFoodInSpecifiedDay(date)
    }

    fun deleteFood(id: Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.delete(id)
        }
    }

}