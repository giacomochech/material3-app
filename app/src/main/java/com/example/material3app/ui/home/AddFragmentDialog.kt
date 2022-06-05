package com.example.material3app.ui.home

import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
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
import com.example.material3app.Cibo
import com.example.material3app.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


class AddFragmentDialog : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

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
                     },dataPiked.year.toInt(),dataPiked.monthValue,dataPiked.dayOfMonth)
                 dpd.show()
         }
        confirmButton.setOnClickListener{
            try {
                val alimento = Cibo(nomeCibo.text.toString(),  Kcal.text.toString().toInt())
            } catch (e:  java.lang.NumberFormatException) {
                val alimento = Cibo(nomeCibo.text.toString(),  0)
                Toast.makeText(requireContext(), "kCal non Valido Inserimento Automatico", Toast.LENGTH_SHORT).show()
            }

            // TODO : Carica in db alimento e dataPiked
            dismiss()
        }
        annullaButton.setOnClickListener {
            dismiss()
        }
        return rootView
    }


}


