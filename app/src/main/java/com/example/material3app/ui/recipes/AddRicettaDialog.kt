package com.example.material3app.ui.recipes


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.ImageDecoder
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.material3app.R
import com.example.material3app.Ricetta
import com.example.material3app.data.FoodViewModel
import com.google.android.material.textfield.TextInputEditText


class AddRicettaDialog: DialogFragment() {

    private lateinit var mFoodViewModel: FoodViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_addricetta_dialog, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val id = arguments?.getInt("id")



        val imageButtonView : Button = rootView.findViewById(R.id.image_button)

        val removeImageButtonView : Button = rootView.findViewById(R.id.remove_image_button)

        val nomeRicettaView : TextInputEditText = rootView.findViewById(R.id.addNomeRicetta)

        val kcalRicettaView : TextInputEditText  = rootView.findViewById(R.id.addKcal)

        val ingredientiView : TextInputEditText = rootView.findViewById(R.id.addIng)

        val descrizioneView : TextInputEditText  = rootView.findViewById(R.id.addDesc)




        val salvaButton : Button = rootView.findViewById(R.id.salvaButton)
        val annullaButton : Button = rootView.findViewById(R.id.annullaButton)

        var imageBitmap  = BitmapFactory.decodeResource(resources, R.drawable.default2)

        mFoodViewModel = ViewModelProvider(this)[FoodViewModel::class.java]

        if(id != 0 && id != null){
            mFoodViewModel.selectRicettabyId(id).observe(viewLifecycleOwner, Observer {

                ricetta->

                imageBitmap = ricetta.cover
                nomeRicettaView.setText(ricetta.nome)
                kcalRicettaView.setText(ricetta.kcal.toString())
                ingredientiView.setText(ricetta.ingredienti)
                descrizioneView.setText(ricetta.descrizione)
                removeImageButtonView.visibility = View.VISIBLE
            })


        }



        mFoodViewModel = ViewModelProvider(this)[FoodViewModel::class.java]
        salvaButton.setOnClickListener{



            var ricetta = Ricetta(0, imageBitmap, "",-1, "","")


            try {
                ricetta = Ricetta(0,imageBitmap, nomeRicettaView.text.toString(),kcalRicettaView.text.toString().toInt(), ingredientiView.text.toString(),descrizioneView.text.toString())
            } catch (e:  java.lang.NumberFormatException) {
                Ricetta(0,imageBitmap, nomeRicettaView.text.toString(),-1, ingredientiView.text.toString(),descrizioneView.text.toString())

            }


            insertDataToDatabase(ricetta)
            if(id != 0 && id != null) mFoodViewModel.deleteRicetta(id)
            dismiss()
        }


        annullaButton.setOnClickListener {
            dismiss()
        }

        val getContent = registerForActivityResult(ActivityResultContracts.GetContent()){uri: Uri?->


            if(uri != null){
                val notScaledImage = ImageDecoder.decodeBitmap(ImageDecoder.createSource(requireActivity().contentResolver, uri))
                imageBitmap = resize(notScaledImage)
                removeImageButtonView.visibility = View.VISIBLE

            } else{
               imageBitmap =  BitmapFactory.decodeResource(resources, R.drawable.default2)
            }

        }

        imageButtonView.setOnClickListener{

            getContent.launch("image/*")

        }

        removeImageButtonView.setOnClickListener{
            imageBitmap =  BitmapFactory.decodeResource(resources, R.drawable.default2)
            removeImageButtonView.visibility = View.GONE
        }
        return rootView
    }

    private fun insertDataToDatabase(ricetta: Ricetta){

        val immagine = ricetta.cover
        val nome = ricetta.nome
        val kcal = ricetta.kcal
        val ing = ricetta.ingredienti
        val description = ricetta.descrizione


        if(inputCheck(nome,kcal)){
            val recipe = Ricetta(0,immagine,nome,kcal,ing,description)

            mFoodViewModel.addRicetta(recipe)
            Toast.makeText(requireContext(),"Dati inseriti correttamente", Toast.LENGTH_LONG).show()

        }
        else{
            Toast.makeText(requireContext(), "Dati non inseriti", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(name : String, kcal: Int) : Boolean{
        return !(TextUtils.isEmpty(name) || (kcal == -1) )
    }

    private fun resize(image: Bitmap): Bitmap {
        val maxWidth = 1066
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
}