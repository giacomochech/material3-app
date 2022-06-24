package com.example.material3app.ui.user

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
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
    private lateinit var immagineUserView: ImageView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(R.layout.fragment_user, container, false)
        val card = rootView.findViewById<MaterialCardView>(R.id.cardUser)
        obiettivoAttualeTextView = rootView.findViewById(R.id.ObiettivoCaloricoAttuale)
        titoloTextView = rootView.findViewById(R.id.Saluto)
        nomeUserTextView = rootView.findViewById(R.id.NomeUtente)
        mailUserTextView = rootView.findViewById(R.id.MailUtente)
        immagineUserView = rootView.findViewById(R.id.imageUser)




        val imageUser:String

        card.setOnClickListener {
            val d = ModifyUserFragment()
            d.callback={nome,email, image ->
                val titolo = "Ciao, $nome"
                titoloTextView.text = titolo
                nomeUserTextView.text = nome


                val bitmapImage = decodeBase64(image)
                immagineUserView.setImageBitmap(bitmapImage)

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
        imageUser = sharedPrefUser.getString(ModifyUserFragment.STRING_IMAGE_USER,"")!!
        val titolo = "Ciao, $stringNomeUser"
        titoloTextView.text = titolo
        nomeUserTextView.text = stringNomeUser
        mailUserTextView.text=mailUser



        if(imageUser == ""){
            immagineUserView.setImageResource(R.drawable.avatar)
        }
        else
        {
            val bitmapImage = decodeBase64(imageUser)
            immagineUserView.setImageBitmap(bitmapImage)
        }

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

    private fun decodeBase64(input: String?): Bitmap {
        val decodedByte = Base64.decode(input, 0)
        return BitmapFactory
            .decodeByteArray(decodedByte, 0, decodedByte.size)
    }

}