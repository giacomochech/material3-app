package com.example.material3app.ui.home

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.material3app.Cibo
import com.example.material3app.R
import com.example.material3app.data.Food
import com.example.material3app.data.FoodViewModel
import com.example.material3app.ui.slideshow.CalcolaFragment
import com.example.material3app.ui.slideshow.CalcolaFragment.Companion.SHARED_PREF_PAGINA_CALC
import java.time.LocalDate
import java.time.format.DateTimeFormatter

// TODO: Rename parameter arguments, choose names that match


/**
 * A simple [Fragment] subclass.
 * Use the [ListaCiboFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@RequiresApi(Build.VERSION_CODES.O)
class ListaCiboFragment() : Fragment() {
    var KcalAssunte :Int = 0
    lateinit var date : LocalDate
    private lateinit var mFoodViewModel: FoodViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState!= null) {
            KcalAssunte = savedInstanceState.getInt(KCAL_SAVE)
            date = savedInstanceState.getSerializable(DATE_SAVE) as LocalDate
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lista_cibo, container, false)
        // Inflate the layout for this fragment
        val textKcalAssunte : TextView = view.findViewById(R.id.KcalAssunte)
        val textGiorno : TextView = view.findViewById(R.id.giorno_del_mese)
        val textMese : TextView = view.findViewById(R.id.mese)
        //val card : CardView = view.findViewById(R.id.cardView)
        val recyclerView : RecyclerView = view.findViewById(R.id.recyclerView)
      //  val


        val ciboAdapter = CiboAdapter()
        recyclerView.adapter = ciboAdapter


        textKcalAssunte.text = KcalAssunte.toString()

        textGiorno.text = date.dayOfMonth.toString()
        textMese.text = date.month.toString()

        mFoodViewModel = ViewModelProvider(this).get(FoodViewModel::class.java)
        mFoodViewModel.readFoodInSpecifiedDay(date.format(DateTimeFormatter.ISO_DATE)).
        observe(viewLifecycleOwner, Observer{
            food ->
            val listaCibo = toMutableCibo(food)
            KcalAssunte=0
            listaCibo.forEach {
                KcalAssunte += it.getKcal()
            }

            textKcalAssunte.text = KcalAssunte.toString()

            val sharedPref = activity?.getSharedPreferences(SHARED_PREF_PAGINA_CALC,Context.MODE_PRIVATE)
            val obiettivo = sharedPref?.getInt(CalcolaFragment.INT_OBIETTIVO,0)
           if(KcalAssunte> obiettivo!!){//TODO: Obiettivo
               // card.setCardBackgroundColor(ResourcesCompat.getColor(resources, com.google.android.material.R.color.m3_sys_color_dark_error,null))
                textKcalAssunte.setTextColor(ResourcesCompat.getColor(resources, com.google.android.material.R.color.m3_sys_color_dark_error_container,null))
            }
            else{
               textKcalAssunte.setTextColor(ResourcesCompat.getColor(resources, com.google.android.material.R.color.m3_ref_palette_primary50,null))
            }

            ciboAdapter.listaCibo.clear()
            ciboAdapter.listaCibo.addAll(listaCibo)
            ciboAdapter.notifyDataSetChanged()
        })





        return view
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KCAL_SAVE,KcalAssunte)
        outState.putSerializable(DATE_SAVE,date)
    }

    fun toMutableCibo(food: List<Food>) : MutableList<Cibo>{
        //List<Food> ---> MutableList<Cibo>
        val ret = mutableListOf<Cibo>()
        val it: ListIterator<Food> = food.listIterator()

        while (it.hasNext()) {
            val e = it.next()
            val c = Cibo(e.id, /*LocalDate.parse(e.date),*/e.nome,e.calorie)
            ret.add(c)
        }

        return ret
    }

companion object {
    const val KCAL_SAVE = "KCal"
    const val DATE_SAVE = "dataSave"
}
}