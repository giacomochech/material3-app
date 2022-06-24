package com.example.material3app.ui.user

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.material3app.R
import com.example.material3app.ui.slideshow.CalcolaFragment
import com.google.android.material.card.MaterialCardView

class UserFragment : Fragment() {
    private lateinit var titoloTextView : TextView
    private lateinit var nomeUserTextView : TextView
    private lateinit var mailUserTextView : TextView
    private lateinit var obiettivoAttualeTextView : TextView



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(R.layout.fragment_user, container, false)
        val card = rootView.findViewById<MaterialCardView>(R.id.cardUser)
        obiettivoAttualeTextView = rootView.findViewById(R.id.ObiettivoCaloricoAttuale)
        val image = rootView.findViewById<ImageView>(R.id.imageView)
        image.setImageResource(R.drawable.avatar)
        titoloTextView = rootView.findViewById(R.id.Saluto)
        nomeUserTextView = rootView.findViewById(R.id.NomeUtente)
        mailUserTextView = rootView.findViewById(R.id.MailUtente)



        card.setOnClickListener {
            val d = ModifyUserFragment()
            d.callback={nome,email->
                val titolo = "Ciao, $nome"
                titoloTextView.text = titolo
                nomeUserTextView.text = nome
                mailUserTextView.text=email
            }
            d.show(parentFragmentManager,"ADD DIALOG MODIFY USER")
        }
        val sharedPref = activity?.getSharedPreferences(
            CalcolaFragment.SHARED_PREF_PAGINA_CALC,
            Context.MODE_PRIVATE)
        sharedPref?.getInt(CalcolaFragment.INT_OBIETTIVO,0)?.let {

            val s = it.toString()
            obiettivoAttualeTextView.text = s
        }
        val sharedPrefUser = activity?.getSharedPreferences(ModifyUserFragment.SHARED_PREF_USER_DATA,Context.MODE_PRIVATE)
        val stringNomeUser = sharedPrefUser!!.getString(ModifyUserFragment.STRING_NOME_USER,"Name")
        val mailUser = sharedPrefUser.getString(ModifyUserFragment.STRING_MAIL_USER,"example@ex.com")

        val titolo = "Ciao, $stringNomeUser"
        titoloTextView.text = titolo
        nomeUserTextView.text = stringNomeUser
        mailUserTextView.text=mailUser





        return rootView
    }

    override fun onResume() {
        super.onResume()
        val sharedPref = activity?.getSharedPreferences(
            CalcolaFragment.SHARED_PREF_PAGINA_CALC,
            Context.MODE_PRIVATE)
        sharedPref?.getInt(CalcolaFragment.INT_OBIETTIVO,0)?.let { val s =
            "$it"
            obiettivoAttualeTextView.text = s
        }
        val sharedPrefUser = activity?.getSharedPreferences(ModifyUserFragment.SHARED_PREF_USER_DATA,Context.MODE_PRIVATE)
        val stringNomeUser = sharedPrefUser!!.getString(ModifyUserFragment.STRING_NOME_USER,"Name")
        val mailUser = sharedPrefUser.getString(ModifyUserFragment.STRING_MAIL_USER,"example@ex.com")

        val s = "Ciao, $stringNomeUser"
        titoloTextView.text = s
        nomeUserTextView.text = stringNomeUser
        mailUserTextView.text=mailUser
    }
}