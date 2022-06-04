package com.example.material3app.ui.home

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.material3app.R
import java.time.LocalDateTime
import java.time.Month
import java.time.MonthDay

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListaCiboFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListaCiboFragment(giorno: Int, mese: Month, KcalAssunte :Int) : Fragment() {
    // TODO: Rename and change types of parameters
  /*  private var param1: String? = null
    private var param2: String? = null*/
    val day = giorno
    val month = mese
    val KcalAssunte = KcalAssunte
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
          /*  param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)*/
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lista_cibo, container, false)
        // Inflate the layout for this fragment
        val textKcalAssunte : TextView = view.findViewById(R.id.KcalAssunte)
        val textGiorno : TextView = view.findViewById(R.id.giorno_del_mese)
        val textMese : TextView = view.findViewById(R.id.mese)
        textKcalAssunte.text = KcalAssunte.toString()

        textGiorno.text = day.toString()
        textMese.text = month.toString()



        return view
    }

    /*companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListaCiboFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            /*ListaCiboFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }*/
            }
    }*/
}