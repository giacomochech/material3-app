package com.example.material3app


data class Cibo(private val id: Int,private var name : String, private var Kcal : Int) {
      fun getName(): String {
         return name
     }

     fun getKcal(): Int {
        return Kcal
    }
    fun getId():Int{
        return id
    }

}