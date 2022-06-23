package com.example.material3app.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.material3app.Cibo
import com.example.material3app.Ricetta
import com.example.material3app.ui.graph.MonthDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.time.Month
import java.time.format.DateTimeFormatter

class FoodViewModel(application: Application) : AndroidViewModel(application) {
    private val readAllData: LiveData<List<Cibo>>
    private val repository: FoodRepository

    init {
        val foodDao = FoodDatabase.getDatabase(application).foodDao()
        repository = FoodRepository(foodDao)
        readAllData = repository.readAllCibo
    }

     private suspend fun readDaySumCal(): List<GionoSommaCal> {

         return repository.readDaySumCal()
    }

    fun addFood(food: Cibo){
        viewModelScope.launch(Dispatchers.IO){
            repository.addFood(food)
        }
    }

    fun readFoodInSpecifiedDay(date: String): LiveData<List<Cibo>> {
        return repository.readFoodInSpecifiedDay(date)
    }

    fun deleteFood(id: Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteCibo(id)
        }
    }

    //Ricetta
    fun addRicetta(ricetta: Ricetta) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addRicetta(ricetta)
        }
    }

    fun selectRicettabyId(id: Int): LiveData<Ricetta>{
        return repository.selectRicettabyId(id)
    }

    fun readAllRicetta(): LiveData<List<Ricetta>>{
        return repository.readAllRicetta()
    }
        fun getSpecificMonthDetails(month: Month, year: Int, obiettivo : Int) : MonthDetail {
            var specificMonthDetail = MonthDetail(month,year)
            val lista= mutableListOf<MonthDetail>()
            var databaseToLista : List<GionoSommaCal>
            runBlocking {
                databaseToLista = readDaySumCal()
            }

            databaseToLista.forEach {
                if (it.somma > 0)
                {
                    val dataObj = LocalDate.parse(it.giorno, DateTimeFormatter.ISO_DATE)
                    var added = false

                    lista.forEach { monthDet ->
                        if (monthDet.month == dataObj.month && monthDet.year == dataObj.year) {
                            if (it.somma > obiettivo) monthDet.incrementNotGoalReached()
                            else monthDet.incrementGoalReached()
                            added = true
                        }


                    }
                    if (!added) {
                        val newMonthDet = MonthDetail(dataObj.month, dataObj.year)
                        if (it.somma > obiettivo) newMonthDet.incrementNotGoalReached()
                        else newMonthDet.incrementGoalReached()
                        lista.add(newMonthDet)
                    }


                }
            }

            lista.forEach {
                if (it.month == month && it.year == year)
                    specificMonthDetail = it
            }
            return specificMonthDetail
        }

        fun getMonthDayKal(month: Month, year: Int): List<Int> {
            val retList = mutableListOf<Int>()
            val specificMonthDetail = MonthDetail(month, year)
            val monthDay = specificMonthDetail.getDayOfMonth()
            var databaseToLista: List<GionoSommaCal>
            runBlocking {
                databaseToLista = readDaySumCal()
            }
            for (i in 1..monthDay) {
                retList.add(0)
            }
            databaseToLista.forEach{
                if (it.somma > 0){
                    val dataObj = LocalDate.parse(it.giorno, DateTimeFormatter.ISO_DATE)
                    if(dataObj.month==month)
                        retList[dataObj.dayOfMonth-1]=it.somma
                }
            }

            return retList.toList()
        }
    fun deleteRicetta(id: Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteRicetta(id)
        }
    }
}