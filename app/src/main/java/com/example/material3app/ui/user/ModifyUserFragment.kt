package com.example.material3app.ui.user

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.material3app.R
import com.google.android.material.textfield.TextInputEditText


class ModifyUserFragment : DialogFragment() {
    var callback :((String,String)->Unit)? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_modify_user, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val textEditNomeUser = rootView.findViewById<TextInputEditText>(R.id.editTextInputNomeUtente)
        val textEditMail = rootView.findViewById<TextInputEditText>(R.id.editTextInputMail)
        val buttonOk = rootView.findViewById<Button>(R.id.accettoUtenteButton)
        val buttonCanc = rootView.findViewById<Button>(R.id.annullaUtenteButton)

        val sharedPref = activity?.getSharedPreferences(SHARED_PREF_USER_DATA,Context.MODE_PRIVATE)
        textEditNomeUser.setText(sharedPref!!.getString(STRING_NOME_USER,null))
        textEditMail.setText(sharedPref.getString(STRING_MAIL_USER,null))

        buttonCanc.setOnClickListener {
            Toast.makeText(requireContext(),"Annullato",Toast.LENGTH_SHORT).show()
            dismiss()
        }
        buttonOk.setOnClickListener {
           if(inputCheckOk(textEditNomeUser.text ,textEditMail.text)){
               val sharedPrefUsr = activity?.getSharedPreferences(SHARED_PREF_USER_DATA,Context.MODE_PRIVATE)
               val editor = sharedPrefUsr?.edit()
               editor?.apply {
                   putString(STRING_NOME_USER,textEditNomeUser.text.toString())
                   putString(STRING_MAIL_USER,textEditMail.text.toString())
               }?.apply()

               callback?.invoke(textEditNomeUser.text.toString(),textEditMail.text.toString())
               Toast.makeText(requireContext(),"Nome e Mail aggiornati",Toast.LENGTH_LONG).show()
            dismiss()
           }
            else{
               Toast.makeText(requireContext(), "Inserire Dati", Toast.LENGTH_SHORT).show()
            }
        }

        return rootView
    }
    private fun inputCheckOk(nome : Editable?,mail : Editable? ) : Boolean{
        return !(nome.isNullOrBlank()||mail.isNullOrBlank())
    }
    companion object {
        const val SHARED_PREF_USER_DATA = "sharPrefUserAdd"
        const val STRING_NOME_USER = "sharedNomeUser"
        const val STRING_MAIL_USER = "sharedMailUser"
    }
}