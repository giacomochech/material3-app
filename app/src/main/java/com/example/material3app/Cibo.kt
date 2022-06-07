package com.example.material3app

import java.lang.IllegalArgumentException
import java.time.LocalDate

data class Cibo(private val id: Int,/*private val date: LocalDate,*/private var name : String, private var Kcal : Int) {
      fun getName(): String {
         return name
     }

     fun getKcal(): Int {
        return Kcal
    }
    fun getId():Int{
        return id
    }

   /* fun getDate() : LocalDate{
        return date
    }*/
// TODO : cancella i commenti

}