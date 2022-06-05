package com.example.material3app.ui.home

import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
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
import androidx.navigation.fragment.findNavController
import com.example.material3app.Cibo
import com.example.material3app.R
import com.example.material3app.data.Food
import com.example.material3app.data.FoodViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


class AddFragmentDialog : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

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
        val nomeCibo : EditText = rootView.findViewById(R.id.AddNomeCibo)
        val Kcal : EditText = rootView.findViewById(R.id.AddCalorieCibo)
        val confirmButton : Button = rootView.findViewById(R.id.AddConfermaBotton)
        val annullaButton : Button = rootView.findViewById(R.id.AddAnnullaBotton)
        var dataPiked = LocalDate.now()
        dateTextView.text= dataPiked.format(DateTimeFormatter
            .ofLocalizedDate(FormatStyle.MEDIUM))


         dateButton.setOnClickListener{
             val dpd = DatePickerDialog(requireContext(),DatePickerDialog.OnDateSetListener { datePicker, y, m, d ->
                         dataPiked = LocalDate.of(y,m+1,d)

                         dateTextView.text= dataPiked.format(DateTimeFormatter
                             .ofLocalizedDate(FormatStyle.MEDIUM))
                     },dataPiked.year.toInt(),dataPiked.monthValue-1,dataPiked.dayOfMonth)
                 dpd.show()
         }

        mFoodViewModel = ViewModelProvider(this).get(FoodViewModel::class.java)
        confirmButton.setOnClickListener{

            var alimento : Cibo = Cibo("",0)
                try {
                alimento = Cibo(nomeCibo.text.toString(),  Kcal.text.toString().toInt())
            } catch (e:  java.lang.NumberFormatException) {
                alimento = Cibo(nomeCibo.text.toString(),  -1)

            }


            insertDataToDatabase(alimento,dataPiked)

            dismiss()
        }
        annullaButton.setOnClickListener {
            dismiss()
        }
        return rootView
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun insertDataToDatabase(alimento: Cibo, data: LocalDate){

        val nome = alimento.getName()
        val kcal = alimento.getKcal()
        val date = data.format(DateTimeFormatter.ISO_DATE)

        if(inputCheck(alimento,date)){
            val food = Food(0,date,nome,kcal)

            mFoodViewModel.addFood(food)
            Toast.makeText(requireContext(),"Dati inseriti correttamente", Toast.LENGTH_LONG).show()
            // Navigate Back
        }
        else{
            Toast.makeText(requireContext(), "Dati non inseriti", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(food: Cibo, date: String) : Boolean{
        return !(TextUtils.isEmpty(food.getName()) || (food.getKcal() == -1) || TextUtils.isEmpty(date) )
    }

}

