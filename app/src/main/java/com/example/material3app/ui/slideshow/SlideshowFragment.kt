package com.example.material3app.ui.slideshow

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.material3app.R
import com.example.material3app.databinding.FragmentSlideshowBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.NonDisposableHandle.parent

class SlideshowFragment : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val rootView = inflater.inflate(R.layout.fragment_slideshow, container, false)
        val editTextEta = rootView.findViewById<TextInputEditText>(R.id.editTextInputEta)
        var editTextCalObiettivo = rootView.findViewById<TextInputEditText>(R.id.editTextInputCalorieObiettivo)
        var editTextPeso = rootView.findViewById<TextInputEditText>(R.id.editTextInputPeso)
        val buttonCalcola = rootView.findViewById<Button>(R.id.ButtonCalcola)
        val dorpDown = rootView.findViewById<AutoCompleteTextView>(R.id.dropdown)
        val sex = resources.getStringArray(R.array.SelectSex)
        dorpDown.setAdapter(ArrayAdapter(requireContext(),R.layout.dropdown_sesso_item,sex))

        buttonCalcola.setOnClickListener {
            if(editTextPeso.text?.let { it1 -> editTextEta.text?.let { it2 ->
                    isInputNull(it1,
                        it2
                    )
                } } == true){
                Toast.makeText(requireContext(),"dati Inseriti Parzialmente",Toast.LENGTH_LONG).show()
            }
            else{
                var isF : Boolean = true
                if(dorpDown.text.toString()==sex[0])
                    isF = false

                val peso   = editTextPeso.text.toString().toInt()
                val età  = editTextEta.text.toString().toInt()
                    editTextCalObiettivo.setText(MET(peso,età, isF).toString()) }
        }


       return rootView
    }
private fun MET(Peso :Int, età : Int, sesso : Boolean): Int {
    var Met: Double  = 0.00
    if(sesso) {
        when(età) {
            in 3..9 ->Met = (22.5 * Peso) + 499
            in 10 .. 17 -> Met =(12.2 * Peso) + 746
            in 18  .. 29  -> Met =(14.7 * Peso) + 496
            in 30   .. 60   -> Met =(8.7  * Peso) + 829
            !in 3   .. 60  -> Met =(10.5 * Peso) + 596
            else -> Met = 0.00
        }
    }else
        when(età) {
            in 3..9 ->Met = (22.7 * Peso) + 495
            in 10 .. 17 -> Met =(22.7 * Peso) + 651
            in 18  .. 29  -> Met =(15.3 * Peso) + 679
            in 30   .. 60   -> Met =(11.6  * Peso) + 879
            !in 3   .. 60  -> Met =(13.5 * Peso) + 487
            else -> Met = 0.00
        }

    return Met.toInt()
}
    private fun isInputNull(peso: Editable,eta: Editable):Boolean{
        return peso.isNullOrBlank()||eta.isNullOrBlank()
}

}