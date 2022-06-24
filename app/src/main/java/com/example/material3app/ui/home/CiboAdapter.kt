package com.example.material3app.ui.home


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.material3app.Cibo
import com.example.material3app.R
import com.example.material3app.data.FoodViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

//adapter per il singolo alimento

class CiboAdapter :
    RecyclerView.Adapter<CiboAdapter.CiboViewHolder>() {
    val listaCibo: MutableList<Cibo> = mutableListOf()
    class CiboViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CiboViewHolder {

        val view : View = LayoutInflater.from(parent.context)
                 .inflate(R.layout.cibo_item,parent,false)


        return CiboViewHolder(view)
    }

    override fun onBindViewHolder(holder: CiboViewHolder, position: Int) {
            holder.bind(listaCibo[position].getId(),listaCibo[position].getName(),listaCibo[position].getKcal())
    }

    override fun getItemCount(): Int {

        return listaCibo.size
    }

}
