@file:Suppress("RedundantSamConstructor")

package com.example.material3app.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.material3app.MainActivityViewModel
import com.example.material3app.R
import com.example.material3app.data.FoodViewModel
import com.example.material3app.ui.slideshow.CalcolaFragment
import com.example.material3app.ui.slideshow.CalcolaFragment.Companion.SHARED_PREF_PAGINA_CALC
import java.time.LocalDate
import java.time.format.DateTimeFormatter
//fragment delle schermate del ViewPager

@RequiresApi(Build.VERSION_CODES.O)
class ListaCiboFragment : Fragment() {
    private val viewModel by activityViewModels<MainActivityViewModel>()
    var kCalAssunte :Int = 0
    lateinit var date : LocalDate
    private lateinit var mFoodViewModel: FoodViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState!= null) {
            kCalAssunte = savedInstanceState.getInt(KCAL_SAVE)
            date = savedInstanceState.getSerializable(DATE_SAVE) as LocalDate
        }
    }


    @SuppressLint("PrivateResource", "NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lista_cibo, container, false)

        val textKcalAssunte : TextView = view.findViewById(R.id.KcalAssunte)
        val textGiorno : TextView = view.findViewById(R.id.giorno_del_mese)
        val textMese : TextView = view.findViewById(R.id.mese)

        val recyclerView : RecyclerView = view.findViewById(R.id.recyclerView)



        val ciboAdapter = CiboAdapter()
        recyclerView.adapter = ciboAdapter


        textKcalAssunte.text = kCalAssunte.toString()

        textGiorno.text = date.dayOfMonth.toString()
        textMese.text = date.month.toString()

        mFoodViewModel = ViewModelProvider(this)[FoodViewModel::class.java]
        mFoodViewModel.readFoodInSpecifiedDay(date.format(DateTimeFormatter.ISO_DATE)).

        observe(viewLifecycleOwner, Observer { food ->
            kCalAssunte = 0
            food.forEach {
                kCalAssunte += it.getKcal()
            }

            textKcalAssunte.text = kCalAssunte.toString()

            val sharedPref =
                activity?.getSharedPreferences(SHARED_PREF_PAGINA_CALC, Context.MODE_PRIVATE)
            val obiettivo = sharedPref?.getInt(CalcolaFragment.INT_OBIETTIVO, 0)
            if (kCalAssunte > obiettivo!!) {
                val typedValue = TypedValue()
                context?.theme?.resolveAttribute(androidx.appcompat.R.attr.colorError, typedValue, true)
                typedValue.data
                textKcalAssunte.setTextColor(typedValue.data)
            } else {
                textKcalAssunte.setTextColor(
                    ResourcesCompat.getColor(
                        resources,
                        com.google.android.material.R.color.primary_text_default_material_light,
                        null
                    )
                )
            }

            ciboAdapter.listaCibo.clear()
            ciboAdapter.listaCibo.addAll(food)
            ciboAdapter.notifyDataSetChanged()
        })





        return view
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KCAL_SAVE,kCalAssunte)
        outState.putSerializable(DATE_SAVE,date)
    }

    override fun onResume() {
        super.onResume()
        viewModel.data = date
    }

companion object {
    const val KCAL_SAVE = "KCal"
    const val DATE_SAVE = "dataSave"
}
}