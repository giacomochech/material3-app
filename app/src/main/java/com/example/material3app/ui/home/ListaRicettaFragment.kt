package com.example.material3app.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.material3app.R
import com.example.material3app.Ricetta
import com.example.material3app.recipeList

class ListaRicettaFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState!= null) {
          //TODO(salvataggio stato)
        }

        populateBooks()
    }

    private fun populateBooks() {
        //TODO(Prendi da database)

        val ricetta1 = Ricetta(
            R.drawable.fagiolata,
            "Fagiolata",
            350,
            "Fagioli\nPasta,\nSale",
            "Bla bla bla bla"
        )
        recipeList.add(ricetta1)

        val ricetta2 = Ricetta(
            R.drawable.ravioli,
            "Ravioli",
            800,
            "Farina\nCarne\nSale",
            "Bla bla bla bla"
        )
        recipeList.add(ricetta2)

        val ricetta3 = Ricetta(
            R.drawable.cassatina,
            "Cassatina",
            400,
            "Canditi\nZucchero\nRicotta",
            "Bla bla bla bla"
        )
        recipeList.add(ricetta3)

    }
}