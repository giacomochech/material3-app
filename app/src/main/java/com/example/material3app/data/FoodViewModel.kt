package com.example.material3app.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.material3app.Cibo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FoodViewModel(application: Application): AndroidViewModel(application)
{
    private val readAllData:LiveData<List<Cibo>>

    private val repository: FoodRepository

    init {
        val foodDao = FoodDatabase.getDatabase(application).foodDao()
        repository = FoodRepository(foodDao)
        readAllData = repository.readAllData

    }

    fun addFood(food: Cibo){
        viewModelScope.launch(Dispatchers.IO){
            repository.addFood(food)
        }
    }

    fun readFoodInSpecifiedDay(date : String) : LiveData<List<Cibo>>{
        return repository.readFoodInSpecifiedDay(date)
    }

    fun deleteFood(id: Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.delete(id)
        }
    }

}