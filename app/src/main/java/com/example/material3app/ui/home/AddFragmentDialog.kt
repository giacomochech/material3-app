package com.example.material3app.ui.home

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.material3app.Cibo
import com.example.material3app.R
import com.example.material3app.data.FoodViewModel
import com.example.material3app.ui.home.HomeFragment.Companion.BOUNDLE_DATE
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*


class AddFragmentDialog : DialogFragment() {

    private lateinit var mFoodViewModel: FoodViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_add_dialog, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val dateButton : Button = rootView.findViewById(R.id.data_button)
        val dateTextView : TextView = rootView.findViewById(R.id.dataPiked)
        val nomeCibo : TextInputEditText = rootView.findViewById(R.id.AddNomeCibo)
        val kCal : TextInputEditText = rootView.findViewById(R.id.AddCalorieCibo)
        val confirmButton : Button = rootView.findViewById(R.id.AddConfermaBotton)
        val annullaButton : Button = rootView.findViewById(R.id.AddAnnullaBotton)


        mFoodViewModel = ViewModelProvider(this)[FoodViewModel::class.java]


        val dataCurrent = arguments?.getString(BOUNDLE_DATE,"")
        var dataPiked = LocalDate.now()
        if(dataCurrent!="" && dataCurrent != null){
            dataPiked = LocalDate.parse(dataCurrent, DateTimeFormatter.ISO_DATE)
        }

        dateTextView.text= localDataToSting(dataPiked)

        val id = arguments?.getInt("id")
        if(id != 0 && id != null) {
            mFoodViewModel.selectRicettabyId(id).observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                 it ->

                    nomeCibo.setText(it.nome)
                    kCal.setText(it.kcal.toString())

            })
        }


        val datePicker = MaterialDatePicker.Builder.datePicker()
                .build()
         dateButton.setOnClickListener{
             datePicker.show(parentFragmentManager,"DATE_Piker")
         }
        datePicker.addOnPositiveButtonClickListener {
            val calendar: Calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
            datePicker.selection?.let { it1 -> calendar.timeInMillis = it1 }

            dataPiked = LocalDateTime.ofInstant(calendar.toInstant(), ZoneId.systemDefault()).toLocalDate()

            dateTextView.text= localDataToSting(dataPiked)
        }


        confirmButton.setOnClickListener{

            var alimento = Cibo(0, "", "",-1)
                try {
                alimento = Cibo(0,dataPiked.format(DateTimeFormatter.ISO_DATE), nomeCibo.text.toString(),  kCal.text.toString().toInt())
            } catch (e:  java.lang.NumberFormatException) {
                Cibo(0,dataPiked.format(DateTimeFormatter.ISO_DATE), nomeCibo.text.toString(), -1)

            }


            insertDataToDatabase(alimento)

            dismiss()
        }
        annullaButton.setOnClickListener {
            dismiss()
        }
        return rootView
    }

    private fun localDataToSting(dataPiked: LocalDate): String {
        val s = "L'alimento verrà inserito in data \n${
            dataPiked.format(
                DateTimeFormatter
                    .ofLocalizedDate(FormatStyle.LONG)
            )
        }"
        return s

    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun insertDataToDatabase(alimento: Cibo){

        val nome = alimento.getName()
        val kcal = alimento.getKcal()
        val date = alimento.getDate()


        if(inputCheck(alimento,date)){
            val food = Cibo(0,date,nome,kcal)

            mFoodViewModel.addFood(food)
            Toast.makeText(requireContext(),"Dati inseriti correttamente", Toast.LENGTH_LONG).show()

        }
        else{
            Toast.makeText(requireContext(), "Dati non inseriti", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(food: Cibo, date: String) : Boolean{
        return !(TextUtils.isEmpty(food.getName()) || (food.getKcal() == -1) || TextUtils.isEmpty(date) )
    }

}


