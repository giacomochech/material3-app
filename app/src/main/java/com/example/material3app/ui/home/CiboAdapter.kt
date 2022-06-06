package com.example.material3app.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.material3app.Cibo
import com.example.material3app.R
import com.example.material3app.data.Food

class CiboAdapter(private val listaCibo: MutableList<Cibo>) :
    RecyclerView.Adapter<CiboAdapter.CiboViewHolder>() {

    class CiboViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val nomeTextView : TextView = itemView.findViewById(R.id.NomeCibo)
        private val KCalTextView : TextView = itemView.findViewById(R.id.NumeroCalrie)

        fun bind( nome : String, Kcal : Int){
            nomeTextView.text = nome
            KCalTextView.text = Kcal.toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CiboViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cibo_item,parent,false)

        return CiboViewHolder(view)
    }

    override fun onBindViewHolder(holder: CiboViewHolder, position: Int) {

        holder.bind(listaCibo[position].getName(),listaCibo[position].getKcal());
    }

    fun setData(food: List<Food>){
        //List<Food> ---> MutableList<Cibo>
        val it: ListIterator<Food> = food.listIterator()

        while (it.hasNext()) {
            val e = it.next()
            val c = Cibo(e.nome,e.calorie)
            listaCibo.add(c)
        }
    }

    override fun getItemCount(): Int {
        return listaCibo.size
    }

}
