package com.example.material3app.ui.user

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.material3app.R
import com.example.material3app.ui.home.AddFragmentDialog
import com.example.material3app.ui.slideshow.CalcolaFragment
import com.google.android.material.card.MaterialCardView

class UserFragment : Fragment() {
    lateinit var titoloTextView : TextView
    lateinit var NomeUserTextView : TextView
    lateinit var mailUserTextView : TextView
    lateinit var obiettivoAttualeTextView : TextView



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(R.layout.fragment_user, container, false)
        val card = rootView.findViewById<MaterialCardView>(R.id.cardUser)
        obiettivoAttualeTextView = rootView.findViewById<TextView>(R.id.ObiettivoCaloricoAttuale)
        val image = rootView.findViewById<ImageView>(R.id.imageView)
        image.setImageResource(R.drawable.photo)
        titoloTextView = rootView.findViewById<TextView>(R.id.Saluto)
        NomeUserTextView = rootView.findViewById<TextView>(R.id.NomeUtente)
        mailUserTextView = rootView.findViewById<TextView>(R.id.MailUtente)



        card.setOnClickListener {
            val d = ModifyUserFragment()
            d.callback={nome,email->
                titoloTextView.text = "Ciao, $nome"
                NomeUserTextView.text = nome
                mailUserTextView.text=email
            }
            d.show(parentFragmentManager,"ADD DIALOG MODIFY USER")
        }
        val sharedPref = activity?.getSharedPreferences(
            CalcolaFragment.SHARED_PREF_PAGINA_CALC,
            Context.MODE_PRIVATE)
        sharedPref?.getInt(CalcolaFragment.INT_OBIETTIVO,0)?.let { obiettivoAttualeTextView.setText("${it.toString()} KCalorie") }
        val sharedPrefUser = activity?.getSharedPreferences(ModifyUserFragment.SHARED_PREF_USER_DATA,Context.MODE_PRIVATE)
        val stringNomeUser = sharedPrefUser!!.getString(ModifyUserFragment.STRING_NOME_USER,"Name")
        val mailUser = sharedPrefUser!!.getString(ModifyUserFragment.STRING_MAIL_USER,"example@ex.com")

        titoloTextView.text = "Ciao, $stringNomeUser"
        NomeUserTextView.text = stringNomeUser
        mailUserTextView.text=mailUser





        return rootView
    }

    override fun onResume() {
        super.onResume()
        val sharedPref = activity?.getSharedPreferences(
            CalcolaFragment.SHARED_PREF_PAGINA_CALC,
            Context.MODE_PRIVATE)
        sharedPref?.getInt(CalcolaFragment.INT_OBIETTIVO,0)?.let { obiettivoAttualeTextView.setText("${it.toString()} KCalorie") }
        val sharedPrefUser = activity?.getSharedPreferences(ModifyUserFragment.SHARED_PREF_USER_DATA,Context.MODE_PRIVATE)
        val stringNomeUser = sharedPrefUser!!.getString(ModifyUserFragment.STRING_NOME_USER,"Name")
        val mailUser = sharedPrefUser!!.getString(ModifyUserFragment.STRING_MAIL_USER,"example@ex.com")

        titoloTextView.text = "Ciao, $stringNomeUser"
        NomeUserTextView.text = stringNomeUser
        mailUserTextView.text=mailUser
    }
    override fun onDestroyView() {
        super.onDestroyView()
    }
}