package com.example.material3app.ui.recipes

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.material3app.R
import com.example.material3app.RICETTA_ID_EXTRA
import com.example.material3app.Ricetta
import com.example.material3app.data.FoodViewModel
import com.example.material3app.databinding.ActivityMainBinding
import com.example.material3app.ui.home.AddFragmentDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class ListaRicettaFragment : Fragment(), RicettaClickListener {

    private lateinit var mFoodViewModel: FoodViewModel


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
        val ricettaAdapter = RicettaAdapter(this)
        recyclerView.adapter = ricettaAdapter
        //populateBooks()

        mFoodViewModel = ViewModelProvider(this)[FoodViewModel::class.java]
        mFoodViewModel.readAllRicetta().observe(viewLifecycleOwner, Observer{
            recipe ->

            ricettaAdapter.recipeList.clear()
            ricettaAdapter.recipeList.addAll(recipe)
            ricettaAdapter.notifyDataSetChanged()

        })

        val fab = view.findViewById<FloatingActionButton>(R.id.floating_action_buttonRicetta)
        fab.setOnClickListener {
            val dialog : DialogFragment = AddRicettaDialog()
            dialog.show(parentFragmentManager,"ADD DIALOG")
        }


        return view
    }

    override fun onClick(view: View, ricetta: Ricetta) {

        val message = ricetta.id
        val action = ListaRicettaFragmentDirections.actionNavRecipesToNavRecDetail(message)
        view.findNavController().navigate(action)
    }
}