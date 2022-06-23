package com.example.material3app.ui.graph

import java.time.LocalDate
import java.time.Month

data class MonthDetail(
    var month: Month,
    val year: Int
){
    private var ggGoalReached : Int =0
    private var ggGoalNotReached : Int = 0
    init {
        if(ggGoalReached+ggGoalNotReached > getDayOfMonth())
            throw IllegalArgumentException()
    }

    fun getDayOfMonth () : Int {
        return (LocalDate.of(year,month,1).lengthOfMonth())
    }
    fun getDetailInt(): List<Int>{
        return listOf(ggGoalReached,ggGoalNotReached,(getDayOfMonth()-(ggGoalReached+ggGoalNotReached)))
    }
    fun incrementGoalReached(){
        ggGoalReached++
    }
    fun incrementNotGoalReached(){
        ggGoalNotReached++
    }

}
