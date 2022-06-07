package com.example.material3app.ui.home

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.example.material3app.Cibo
import com.example.material3app.R
import com.example.material3app.data.Food
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.NonDisposableHandle.parent
import java.security.AccessController.getContext
import java.time.LocalDate

class CiboAdapter(val listaCibo: MutableList<Cibo>) :
    RecyclerView.Adapter<CiboAdapter.CiboViewHolder>() {

    class CiboViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val nomeTextView : TextView = itemView.findViewById(R.id.NomeCibo)
        private val KCalTextView : TextView = itemView.findViewById(R.id.NumeroCalrie)
        private val deleteButton : ImageButton = itemView.findViewById(R.id.deleteButton)


        fun bind(id: Int, nome : String, Kcal : Int){

            nomeTextView.text = nome
            KCalTextView.text = Kcal.toString()
            deleteButton.setOnClickListener {
                //TODO : elimina qui l'element id
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CiboViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cibo_item,parent,false)


        return CiboViewHolder(view)
    }

    override fun onBindViewHolder(holder: CiboViewHolder, position: Int) {

        holder.bind(listaCibo[position].getId(),listaCibo[position].getName(),listaCibo[position].getKcal());
    }

    override fun getItemCount(): Int {
        return listaCibo.size
    }

}
