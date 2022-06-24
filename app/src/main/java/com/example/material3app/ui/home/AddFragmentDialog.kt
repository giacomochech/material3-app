package com.example.material3app.ui.home

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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
import com.google.android.material.textfield.TextInputLayout
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

//fragment Dialog per l'aggiunta di alimenti

class AddFragmentDialog : DialogFragment() {

    private lateinit var mFoodViewModel: FoodViewModel
    private lateinit var nomeCibo : TextInputEditText
    private lateinit var kCal : TextInputEditText
    private lateinit var nomeCibolayout : TextInputLayout
    private lateinit var kCalLayout : TextInputLayout

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_add_dialog, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val dateButton : Button = rootView.findViewById(R.id.data_button)
        val dateTextView : TextView = rootView.findViewById(R.id.dataPiked)
        nomeCibo = rootView.findViewById(R.id.AddNomeCibo)
        kCal  = rootView.findViewById(R.id.AddCalorieCibo)

        nomeCibolayout = rootView.findViewById(R.id.AddNomeCiboLayout)
        kCalLayout  = rootView.findViewById(R.id.AddCalorieCiboLayout)
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


            if(insertDataToDatabase(alimento)) {
                dismiss()
            }
        }
        annullaButton.setOnClickListener {
            dismiss()
        }
        return rootView
    }
//funzione per trasformata un local dato nella sua stringa ppersonalizzata
    private fun localDataToSting(dataPiked: LocalDate): String {
        return "L'alimento verrà inserito in data \n${
            dataPiked.format(
                DateTimeFormatter
                    .ofLocalizedDate(FormatStyle.LONG)
            )
        }"

    }

//funzione per aggiungere i dati al Database
    @RequiresApi(Build.VERSION_CODES.O)
    private fun insertDataToDatabase(alimento: Cibo): Boolean{

        val nome = alimento.getName()
        val kcal = alimento.getKcal()
        val date = alimento.getDate()


        if(inputCheck()){
            val food = Cibo(0,date,nome,kcal)

            mFoodViewModel.addFood(food)
            Toast.makeText(requireContext(),"Dati inseriti correttamente", Toast.LENGTH_LONG).show()
            return true
        }
        else{
            if(nomeCibo.text.isNullOrBlank())
                nomeCibolayout.error = "Nome non valido"
            else
                nomeCibolayout.error = null

            if(kCal.text.isNullOrBlank())
                kCalLayout.error = "Valore non valido"
            else
                kCalLayout.error = null




            return false
        }
    }

//funzione di check
    private fun inputCheck() : Boolean{
        return !(nomeCibo.text.isNullOrBlank()||kCal.text.isNullOrBlank())
    }
}


