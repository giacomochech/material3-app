package com.example.material3app.ui.recipes


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.example.material3app.R
import com.example.material3app.Ricetta
import com.example.material3app.data.FoodViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListaRicettaFragment : Fragment(), RicettaClickListener {

    private lateinit var mFoodViewModel: FoodViewModel


    @SuppressLint("NotifyDataSetChanged")
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

        val detailsLayout = view.findViewById<LinearLayout>(R.id.details)
        val v: Int = if(detailsLayout.visibility == View.GONE) View.VISIBLE else View.GONE

        TransitionManager.beginDelayedTransition(detailsLayout, AutoTransition())
        detailsLayout.visibility = v


    }
}