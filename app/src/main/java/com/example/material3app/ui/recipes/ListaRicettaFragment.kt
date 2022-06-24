package com.example.material3app.ui.recipes


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.TranslateAnimation
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.material3app.R
import com.example.material3app.Ricetta
import com.example.material3app.data.FoodViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

//fragment base delle Ricette
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
        if(detailsLayout.visibility == View.GONE){
            val set = AnimationSet(true)

            val animateT = TranslateAnimation(0f,0f ,500F,0F )
            animateT.duration = 200
            set.addAnimation(animateT)

            val anim : Animation = AlphaAnimation(0F, 1.0f)
            anim.duration =300
            animateT.fillAfter = true
            set.addAnimation(anim)
            detailsLayout.startAnimation(set)

            detailsLayout.visibility = View.VISIBLE

        }
        else {
            detailsLayout.visibility = View.GONE
        }




    }
}