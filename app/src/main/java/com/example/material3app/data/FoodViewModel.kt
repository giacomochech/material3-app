package com.example.material3app.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.material3app.Cibo
import com.example.material3app.Ricetta
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FoodViewModel(application: Application): AndroidViewModel(application)
{
    private val readAllCibo:LiveData<List<Cibo>>


    private val repository: FoodRepository

    init {
        val foodDao = FoodDatabase.getDatabase(application).foodDao()
        repository = FoodRepository(foodDao)
        readAllCibo = repository.readAllCibo


    }

    fun addFood(food: Cibo){
        viewModelScope.launch(Dispatchers.IO){
            repository.addFood(food)
        }
    }

    fun deleteFood(id: Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteCibo(id)
        }
    }

    fun readFoodInSpecifiedDay(date : String) : LiveData<List<Cibo>>{
        return repository.readFoodInSpecifiedDay(date)
    }


    //Ricetta
    fun addRicetta(ricetta: Ricetta){
        viewModelScope.launch(Dispatchers.IO){
            repository.addRicetta(ricetta)
        }
    }

    fun selectRicettabyId(id: Int): LiveData<Ricetta>{
        return repository.selectRicettabyId(id)
    }

    fun readAllRicetta(): LiveData<List<Ricetta>>{
        return repository.readAllRicetta()
    }

    fun deleteRicetta(id: Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteRicetta(id)
        }
    }
}