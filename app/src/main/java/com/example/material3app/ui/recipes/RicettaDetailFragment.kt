package com.example.material3app.ui.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.material3app.R
import com.example.material3app.data.FoodViewModel


class RicettaDetailFragment : Fragment()
{

    private lateinit var mFoodViewModel: FoodViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ricetta_detail,container,false)
        //find by ViewbyId
        val image: ImageView = view.findViewById(R.id.cover)
        val name: TextView = view.findViewById(R.id.nomeRicetta)
        val kcal: TextView = view.findViewById(R.id.kcalRicetta)
        val ingredients: TextView = view.findViewById(R.id.ingredienti)
        val description: TextView = view.findViewById(R.id.descrizioneRicetta)

        mFoodViewModel = ViewModelProvider(this)[FoodViewModel::class.java]

        var id = RicettaDetailFragmentArgs.fromBundle(requireArguments()).message
        mFoodViewModel.selectRicettabyId(id).observe(viewLifecycleOwner, Observer{
            recipe ->

            image.setImageBitmap(recipe.cover)
            name.text = recipe.nome
            kcal.text = recipe.kcal.toString()
            ingredients.text =  recipe.ingredienti
            description.text =  recipe.descrizione

        })

        return view
    }


}