package com.example.material3app

import java.lang.IllegalArgumentException

data class Cibo(private var name : String, private var Kcal : Int) {
      fun getName(): String {
         return name
     }
     fun setName(name: String) {
        this.name = name
    }
     fun getKcal(): Int {
        return Kcal
    }
     fun setKcal(Kcal: Int) {
        if(Kcal<0)
            throw IllegalArgumentException()

        this.Kcal= Kcal
    }



}