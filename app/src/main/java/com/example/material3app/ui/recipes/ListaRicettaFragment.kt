package com.example.material3app.ui.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.material3app.R
import com.example.material3app.Ricetta
import com.example.material3app.databinding.ActivityMainBinding

class ListaRicettaFragment : Fragment() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState!= null) {
          //TODO(salvataggio stato)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        val view = inflater.inflate(R.layout.fragment_lista_ricette, container, false)

        val recyclerView : RecyclerView = view.findViewById(R.id.recyclerView)


        val ricettaAdapter = RicettaAdapter()
        recyclerView.adapter = ricettaAdapter
        populateBooks(ricettaAdapter.recipeList)


       //Inserire model Provider DB


        return view
    }

    private fun populateBooks(recipes : MutableList<Ricetta>) {
        //TODO(Prendi da database)

        val ricetta1 = Ricetta(
            R.drawable.fagiolata,
            "Fagiolata",
            350,
            "Fagioli\nPasta,\nSale",
            "Bla bla bla bla"
        )
        recipes.add(ricetta1)

        val ricetta2 = Ricetta(
            R.drawable.ravioli,
            "Ravioli",
            800,
            "Farina\nCarne\nSale",
            "Bla bla bla bla"
        )
        recipes.add(ricetta2)

        val ricetta3 = Ricetta(
            R.drawable.cassatina,
            "Cassatina",
            400,
            "Canditi\nZucchero\nRicotta",
            "Bla bla bla bla"
        )
        recipes.add(ricetta3)

    }
}