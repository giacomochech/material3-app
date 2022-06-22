package com.example.material3app.data

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.material3app.Cibo
import com.example.material3app.ui.graph.MonthDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.time.Month
import java.time.Year
import java.time.format.DateTimeFormatter
import java.util.*

class FoodViewModel(application: Application) : AndroidViewModel(application) {
    private val readAllData: LiveData<List<Cibo>>


    private val repository: FoodRepository

    init {
        val foodDao = FoodDatabase.getDatabase(application).foodDao()
        repository = FoodRepository(foodDao)
        readAllData = repository.readAllData

    }
     suspend fun readDaySumCal(): List<GionoSommaCal> {

         return repository.readDaySumCal()
    }

    fun addFood(food: Cibo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addFood(food)
        }
    }

    fun readFoodInSpecifiedDay(date: String): LiveData<List<Cibo>> {
        return repository.readFoodInSpecifiedDay(date)
    }

    fun deleteFood(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(id)
        }
    }

    fun getSpecificMonthDetails(month: Month, year: Int, obiettivo : Int, fragment: Fragment) : MonthDetail {
        var specificMonthDetail = MonthDetail(month,year)
        val lista= mutableListOf<MonthDetail>()
        var databaseToLista : List<GionoSommaCal>
        runBlocking {
        databaseToLista = readDaySumCal()
    }

        databaseToLista.forEach {
                if (it.somma <= 0);
                else {
                    val dataObj = LocalDate.parse(it.giorno, DateTimeFormatter.ISO_DATE)
                    var added = false

                    lista.forEach { monthDet ->
                        if (monthDet.month.equals(dataObj.month) && monthDet.year == dataObj.year) {
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
            if (it.month.equals(month) && it.year == year)
                specificMonthDetail = it
        }
        return specificMonthDetail
    }

    fun getMonthDayKal(month: Month, year: Int): List<Int> {
        val retList = mutableListOf<Int>()
        var specificMonthDetail = MonthDetail(month, year)
        val monthDay = specificMonthDetail.getDayOfMonth()
        var databaseToLista: List<GionoSommaCal>
        runBlocking {
            databaseToLista = readDaySumCal()
        }
        for (i in 1..monthDay) {
            retList.add(0)
        }
        databaseToLista.forEach{
            if (it.somma <= 0);
            else {
                val dataObj = LocalDate.parse(it.giorno, DateTimeFormatter.ISO_DATE)
                if(dataObj.month==month)
                    retList[dataObj.dayOfMonth-1]=it.somma
            }
        }

        return retList.toList()
    }
}