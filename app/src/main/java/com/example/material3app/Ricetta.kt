package com.example.material3app

var recipeList = mutableListOf<Ricetta>()

class Ricetta (
    var cover: Int,
    var nome: String,
    var kcal: Int,
    var ingredienti: String,
    var descrizione: String,
    val id: Int? = recipeList.size

)