package com.example.material3app.ui.slideshow

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.material3app.R
import com.google.android.material.textfield.TextInputEditText

class CalcolaFragment : Fragment() {



    lateinit var dorpDown : AutoCompleteTextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val rootView = inflater.inflate(R.layout.fragment_calcola, container, false)
        val editTextEta = rootView.findViewById<TextInputEditText>(R.id.editTextInputEta)
        val editTextCalObiettivo = rootView.findViewById<TextInputEditText>(R.id.editTextInputCalorieObiettivo)
        val editTextPeso = rootView.findViewById<TextInputEditText>(R.id.editTextInputPeso)
        val buttonCalcola = rootView.findViewById<Button>(R.id.ButtonCalcola)
        dorpDown = rootView.findViewById(R.id.dropdown)
        val aggiungiButton = rootView.findViewById<Button>(R.id.AccettaCalc)
        val annullaButton = rootView.findViewById<Button>(R.id.AnnullaCalc)

        val sharedPref = activity?.getSharedPreferences(SHARED_PREF_PAGINA_CALC,Context.MODE_PRIVATE)
        sharedPref?.getInt(INT_OBIETTIVO,0)?.let { editTextCalObiettivo.setText(it.toString()) }

        val sex = resources.getStringArray(R.array.SelectSex)
        val ad =ArrayAdapter.createFromResource(requireContext(),R.array.SelectSex,R.layout.dropdown_sesso_item)
        dorpDown.setAdapter(ad)

        buttonCalcola.setOnClickListener {
            if(editTextPeso.text?.let { it1 -> editTextEta.text?.let { it2 ->
                    isInputNull(it1,
                        it2
                    )
                } } == true){
                Toast.makeText(requireContext(),"dati Inseriti Parzialmente",Toast.LENGTH_LONG).show()
            }
            else{
                var isF = true
                if(dorpDown.text.toString()==sex[0])
                    isF = false

                val peso   = editTextPeso.text.toString().toInt()
                val eta  = editTextEta.text.toString().toInt()
                    editTextCalObiettivo.setText(MET(peso,eta, isF).toString()) }
        }


        aggiungiButton.setOnClickListener {
            if(!editTextCalObiettivo.text.isNullOrBlank()){
            val obiettivo = editTextCalObiettivo.text.toString().toInt()
                val sharedPrefCalc = activity?.getSharedPreferences(SHARED_PREF_PAGINA_CALC,Context.MODE_PRIVATE)
                val editor = sharedPrefCalc?.edit()
                editor?.apply {
                    putInt(INT_OBIETTIVO,obiettivo)
                }?.apply()
                Toast.makeText(requireContext(),"obiettivo salvato",Toast.LENGTH_LONG).show()
            }
            else
                Toast.makeText(requireContext(),"obiettivo non immesso",Toast.LENGTH_LONG).show()
        }
        annullaButton.setOnClickListener {
            val sharedPrefCalc = activity?.getSharedPreferences(SHARED_PREF_PAGINA_CALC,Context.MODE_PRIVATE)
            sharedPrefCalc?.getInt(INT_OBIETTIVO,0)?.let { editTextCalObiettivo.setText(it.toString()) }
            Toast.makeText(requireContext(),"Annullato",Toast.LENGTH_SHORT).show()
        }

       return rootView
    }
    private fun MET(Peso :Int, eta : Int, sesso : Boolean): Int {
        val met: Double
        if(sesso) {
            met = when(eta) {
                in 3..9 -> (22.5 * Peso) + 499
                in 10 .. 17 -> (12.2 * Peso) + 746
                in 18  .. 29  -> (14.7 * Peso) + 496
                in 30   .. 60   -> (8.7  * Peso) + 829
                !in 3   .. 60  -> (10.5 * Peso) + 596
                else -> 0.00
            }
    }else
        met = when(eta) {
            in 3..9 -> (22.7 * Peso) + 495
            in 10 .. 17 -> (22.7 * Peso) + 651
            in 18  .. 29  -> (15.3 * Peso) + 679
            in 30   .. 60   -> (11.6  * Peso) + 879
            !in 3   .. 60  -> (13.5 * Peso) + 487
            else -> 0.00
        }

    return met.toInt()
}
    private fun isInputNull(peso: Editable,eta: Editable):Boolean{
        return peso.isBlank()||eta.isBlank()
}
    companion object{
        const val SHARED_PREF_PAGINA_CALC = "sharPref"
        const val INT_OBIETTIVO = "sharedObiettivo"
    }

    override fun onResume() {

        val ad =ArrayAdapter.createFromResource(requireContext(),R.array.SelectSex,R.layout.dropdown_sesso_item)
        dorpDown.setAdapter(ad)
        super.onResume()
    }
}