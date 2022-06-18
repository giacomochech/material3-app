package com.example.material3app.ui.home

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.material3app.R
import com.example.material3app.Ricetta
import com.example.material3app.data.FoodViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class RicettaAdapter(private val ricette: List<Ricetta>)
    : RecyclerView.Adapter<RicettaAdapter.RicettaViewHolder>()
{
    class RicettaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val nomeTextView : TextView = itemView.findViewById(R.id.NomeCibo)
        private val kCalTextView : TextView = itemView.findViewById(R.id.NumeroCalrie)
        private val deleteButton : Button = itemView.findViewById(R.id.deleteButton)
        private lateinit var mFoodViewModel: FoodViewModel

        fun bind(id: Int, nome : String, Kcal : Int){

            nomeTextView.text = nome
            kCalTextView.text = Kcal.toString()
            deleteButton.setOnClickListener {
                mFoodViewModel = ViewModelProvider(itemView.context as ViewModelStoreOwner)[FoodViewModel::class.java]
                runBlocking { delay(200) }
                mFoodViewModel.deleteFood(id)
            }

        }

    }
}