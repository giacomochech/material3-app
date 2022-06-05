package com.example.material3app.ui.home

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.content.res.AppCompatResources
import androidx.cardview.widget.CardView
import androidx.core.content.res.ResourcesCompat
import com.example.material3app.R
import com.example.material3app.ui.Material3AppTheme
import com.google.android.material.color.MaterialColors
import java.time.LocalDate

// TODO: Rename parameter arguments, choose names that match


/**
 * A simple [Fragment] subclass.
 * Use the [ListaCiboFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@RequiresApi(Build.VERSION_CODES.O)
class ListaCiboFragment(date : LocalDate, private val KcalAssunte: Int) : Fragment() {


    private val day = date.dayOfMonth
    private val month = date.month
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        var card : CardView = view.findViewById(R.id.cardView)
      if(KcalAssunte> 2000){//TODO: Obiettivo
          card.setCardBackgroundColor(ResourcesCompat.getColor(resources, com.google.android.material.R.color.m3_sys_color_dark_error,null))
          textKcalAssunte.setTextColor(ResourcesCompat.getColor(resources, com.google.android.material.R.color.m3_sys_color_dark_on_error,null))
      }
        textKcalAssunte.text = KcalAssunte.toString()

        textGiorno.text = day.toString()
        textMese.text = month.toString()



        return view
    }





}