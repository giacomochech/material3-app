package com.example.material3app.ui.recipes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.material3app.R
import com.example.material3app.Ricetta


class RicettaAdapter(
    private val clickListener: RicettaClickListener
)
    : RecyclerView.Adapter<RicettaAdapter.RicettaViewHolder>() {
    val recipeList: MutableList<Ricetta> = mutableListOf()


    class RicettaViewHolder(
        itemView: View,
        val clickListener: RicettaClickListener
    ) : RecyclerView.ViewHolder(itemView)
    {

        private val coverImageView: ImageView = itemView.findViewById(R.id.cover)
        private val nomeTextView: TextView = itemView.findViewById(R.id.nomeRicetta)
        private val kcalTextView: TextView = itemView.findViewById(R.id.kcalRicetta)
        private val ricettaCardView: CardView = itemView.findViewById(R.id.cardView)

        //Viewmodel DB

        fun bind(ricetta: Ricetta) {

            coverImageView.setImageResource(ricetta.cover)
            nomeTextView.text = ricetta.nome
            kcalTextView.text = ricetta.kcal.toString()
            ricettaCardView.setOnClickListener{
                clickListener.onClick(ricetta)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RicettaViewHolder {
        val view : View = LayoutInflater.from(parent.context)
            .inflate(R.layout.ricetta_item,parent,false)


        return RicettaViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: RicettaViewHolder, position: Int) {
        holder.bind(recipeList[position])
    }

    override fun getItemCount(): Int = recipeList.size


}