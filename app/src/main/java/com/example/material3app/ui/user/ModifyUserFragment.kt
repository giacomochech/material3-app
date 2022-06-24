package com.example.material3app.ui.user

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.ImageDecoder
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import com.example.material3app.R
import com.google.android.material.textfield.TextInputEditText
import java.io.ByteArrayOutputStream



class ModifyUserFragment : DialogFragment() {
    var callback :((String,String,String)->Unit)? = null


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
        val selImageButton = rootView.findViewById<Button>(R.id.image_user_button)

        val sharedPref = activity?.getSharedPreferences(SHARED_PREF_USER_DATA,Context.MODE_PRIVATE)
        textEditNomeUser.setText(sharedPref!!.getString(STRING_NOME_USER,null))
        textEditMail.setText(sharedPref.getString(STRING_MAIL_USER,null))

        buttonCanc.setOnClickListener {
            Toast.makeText(requireContext(),"Annullato",Toast.LENGTH_SHORT).show()
            dismiss()
        }

        var imageBase64 = ""

        val getContent = registerForActivityResult(ActivityResultContracts.GetContent()){ uri: Uri?->


            if(uri != null){
                val notScaledImage = ImageDecoder.decodeBitmap(ImageDecoder.createSource(requireActivity().contentResolver, uri))
                val imageBitmap = resize(notScaledImage)

                imageBase64 = encodeToBase64(imageBitmap)

            }


        }


            selImageButton.setOnClickListener{


                getContent.launch("image/*")

            }

        buttonOk.setOnClickListener {
           if(inputCheckOk(textEditNomeUser.text ,textEditMail.text)){
               val sharedPrefUsr = activity?.getSharedPreferences(SHARED_PREF_USER_DATA,Context.MODE_PRIVATE)
               val editor = sharedPrefUsr?.edit()
               editor?.apply {
                   putString(STRING_NOME_USER,textEditNomeUser.text.toString())
                   putString(STRING_MAIL_USER,textEditMail.text.toString())

                   if(imageBase64 != "")
                    putString(STRING_IMAGE_USER,imageBase64)
               }?.apply()

               if (sharedPrefUsr != null) {
                   sharedPrefUsr.getString(STRING_IMAGE_USER,"")?.let { it1 ->
                       callback?.invoke(textEditNomeUser.text.toString(),textEditMail.text.toString(),
                           it1
                       )
                   }
               }
               Toast.makeText(requireContext(),"Profilo aggiornato",Toast.LENGTH_LONG).show()
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

    private fun encodeToBase64(image: Bitmap): String {
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val b: ByteArray = baos.toByteArray()
        val imageEncoded: String = Base64.encodeToString(b, Base64.DEFAULT)

        return imageEncoded
    }

    private fun resize(image: Bitmap): Bitmap {
        val maxWidth = 800
        val maxHeight = 800
        var img = image
        return if (maxHeight > 0 && maxWidth > 0) {
            val width = img.width
            val height = img.height
            val ratioBitmap = width.toFloat() / height.toFloat()
            val ratioMax = maxWidth.toFloat() / maxHeight.toFloat()
            var finalWidth = maxWidth
            var finalHeight = maxHeight
            if (ratioMax > ratioBitmap) {
                finalWidth = (maxHeight.toFloat() * ratioBitmap).toInt()
            } else {
                finalHeight = (maxWidth.toFloat() / ratioBitmap).toInt()
            }

            img = Bitmap.createScaledBitmap(img, finalWidth, finalHeight, true)
            img
        } else {
            img
        }
    }

    companion object {
        const val SHARED_PREF_USER_DATA = "sharPrefUserAdd"
        const val STRING_NOME_USER = "sharedNomeUser"
        const val STRING_MAIL_USER = "sharedMailUser"
        const val STRING_IMAGE_USER = "sharedImageUser"
    }
}