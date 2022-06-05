package com.example.material3app.ui.home

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
import androidx.recyclerview.widget.RecyclerView
import com.example.material3app.R
import java.time.LocalDate

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
        val card : CardView = view.findViewById(R.id.cardView)
        val recyclerView : RecyclerView = view.findViewById(R.id.recyclerView)

        val listaCibo = DataFormDb().getListCibo(date)


      if(KcalAssunte> 2000){//TODO: Obiettivo
          card.setCardBackgroundColor(ResourcesCompat.getColor(resources, com.google.android.material.R.color.m3_sys_color_dark_error,null))
          textKcalAssunte.setTextColor(ResourcesCompat.getColor(resources, com.google.android.material.R.color.m3_sys_color_dark_on_error,null))
      }
        textKcalAssunte.text = KcalAssunte.toString()

        textGiorno.text = date.dayOfMonth.toString()
        textMese.text = date.month.toString()



        recyclerView.adapter= CiboAdapter(listaCibo)

        return view
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KCAL_SAVE,KcalAssunte)
        outState.putSerializable(DATE_SAVE,date)
    }

companion object {
    const val KCAL_SAVE = "KCal"
    const val DATE_SAVE = "dataSave"
}
}