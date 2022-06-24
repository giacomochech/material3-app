package com.example.material3app.ui.slideshow

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.material3app.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class CalcolaFragment : Fragment() {


    private lateinit var dropDown: AutoCompleteTextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val rootView = inflater.inflate(R.layout.fragment_calcola, container, false)
        val editTextEta = rootView.findViewById<TextInputEditText>(R.id.editTextInputEta)
        val editTextCalObiettivo =
            rootView.findViewById<TextInputEditText>(R.id.editTextInputCalorieObiettivo)
        val editTextPeso = rootView.findViewById<TextInputEditText>(R.id.editTextInputPeso)
        val buttonCalcola = rootView.findViewById<Button>(R.id.ButtonCalcola)
        dropDown = rootView.findViewById(R.id.dropdown)
        val aggiungiButton = rootView.findViewById<Button>(R.id.AccettaCalc)
        val annullaButton = rootView.findViewById<Button>(R.id.AnnullaCalc)

        val sharedPref =
            activity?.getSharedPreferences(SHARED_PREF_PAGINA_CALC, Context.MODE_PRIVATE)
        sharedPref?.getInt(INT_OBIETTIVO, 0)?.let { editTextCalObiettivo.setText(it.toString()) }

        val sex = resources.getStringArray(R.array.SelectSex)
        val ad = ArrayAdapter(requireContext(), R.layout.dropdown_sesso_item, sex)
        dropDown.setAdapter(ad)

        buttonCalcola.setOnClickListener {
            val textLayoutPeso = rootView.findViewById<TextInputLayout>(R.id.editTextPeso)
            val textLayoutEta = rootView.findViewById<TextInputLayout>(R.id.editTextEta)
            val textLayoutDropdown = rootView.findViewById<TextInputLayout>(R.id.editTextDropdown)

            if (editTextPeso.text.isNullOrBlank() || editTextEta.text.isNullOrBlank() || dropDown.text.isNullOrBlank()) {


                if (editTextPeso.text.isNullOrBlank())
                    textLayoutPeso.error = "valore non valido"
                else
                    textLayoutPeso.error = null

                if (editTextEta.text.isNullOrBlank())
                    textLayoutEta.error = "valore non valido"
                else
                    textLayoutEta.error = null

                if (dropDown.text.isNullOrBlank())
                    textLayoutDropdown.error = "valore non valido"
                else
                    textLayoutDropdown.error = null

            } else {

                textLayoutPeso.error = null
                textLayoutEta.error = null
                textLayoutDropdown.error = null


                var isF = true
                if (dropDown.text.toString() == sex[0])
                    isF = false

                val peso = editTextPeso.text.toString().toInt()
                val eta = editTextEta.text.toString().toInt()
                editTextCalObiettivo.setText(met(peso, eta, isF).toString())
            }
        }


        aggiungiButton.setOnClickListener {
            if (!editTextCalObiettivo.text.isNullOrBlank()) {
                val textLayout =
                    rootView.findViewById<TextInputLayout>(R.id.editTextCalorieObiettivo)
                textLayout.error = null
                val obiettivo = editTextCalObiettivo.text.toString().toInt()
                val sharedPrefCalc =
                    activity?.getSharedPreferences(SHARED_PREF_PAGINA_CALC, Context.MODE_PRIVATE)
                val editor = sharedPrefCalc?.edit()
                editor?.apply {
                    putInt(INT_OBIETTIVO, obiettivo)
                }?.apply()
            } else {
                val textLayout =
                    rootView.findViewById<TextInputLayout>(R.id.editTextCalorieObiettivo)
                textLayout.error = "obiettivo recuperato"
            }

        }
        annullaButton.setOnClickListener {
            val sharedPrefCalc =
                activity?.getSharedPreferences(SHARED_PREF_PAGINA_CALC, Context.MODE_PRIVATE)
            sharedPrefCalc?.getInt(INT_OBIETTIVO, 0)
                ?.let { editTextCalObiettivo.setText(it.toString()) }
            Toast.makeText(requireContext(), "Annullato", Toast.LENGTH_SHORT).show()
        }

        return rootView
    }

    private fun met(Peso: Int, eta: Int, sesso: Boolean): Int {
        val met: Double
        if (sesso) {
            met = when (eta) {
                in 3..9 -> (22.5 * Peso) + 499
                in 10..17 -> (12.2 * Peso) + 746
                in 18..29 -> (14.7 * Peso) + 496
                in 30..60 -> (8.7 * Peso) + 829
                !in 3..60 -> (10.5 * Peso) + 596
                else -> 0.00
            }
        } else
            met = when (eta) {
                in 3..9 -> (22.7 * Peso) + 495
                in 10..17 -> (22.7 * Peso) + 651
                in 18..29 -> (15.3 * Peso) + 679
                in 30..60 -> (11.6 * Peso) + 879
                !in 3..60 -> (13.5 * Peso) + 487
                else -> 0.00
            }

        return met.toInt()
    }

    companion object {
        const val SHARED_PREF_PAGINA_CALC = "sharPref"
        const val INT_OBIETTIVO = "sharedObiettivo"
    }

    override fun onResume() {

        val ad = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.SelectSex,
            R.layout.dropdown_sesso_item
        )
        dropDown.setAdapter(ad)
        super.onResume()
    }
}