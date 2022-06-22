package com.example.material3app.ui.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.material3app.R
import com.example.material3app.Ricetta
import com.example.material3app.data.FoodViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


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
        private val deleteButton: Button = itemView.findViewById(R.id.buttonElimina)
        private val modifyButton = itemView.findViewById<Button>(R.id.buttonModifica)

        private lateinit var mFoodViewModel: FoodViewModel



        fun bind(ricetta: Ricetta) {

            coverImageView.setImageBitmap(ricetta.cover)
            nomeTextView.text = ricetta.nome
            kcalTextView.text = ricetta.kcal.toString()
            ricettaCardView.setOnClickListener{
                clickListener.onClick(it,ricetta)
            }

            deleteButton.setOnClickListener {
                mFoodViewModel = ViewModelProvider(itemView.context as ViewModelStoreOwner)[FoodViewModel::class.java]
                runBlocking { delay(200) }
                mFoodViewModel.deleteRicetta(ricetta.id)
            }


            modifyButton.setOnClickListener {

                val dialog : DialogFragment = AddRicettaDialog()
                val args = Bundle()
                args.putInt("id",ricetta.id)
                dialog.arguments = args
                dialog.show((itemView.context as FragmentActivity).getSupportFragmentManager(),"ADD DIALOG")

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