package com.example.material3app.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.material3app.Cibo
import com.example.material3app.data.FoodViewModel
import java.security.AccessController.getContext
import java.time.LocalDate
@RequiresApi(Build.VERSION_CODES.O)
private lateinit var mFoodViewModel: FoodViewModel
class DataFormDb {


    var mappa = mutableMapOf<LocalDate,MutableList<Cibo>>()

    @RequiresApi(Build.VERSION_CODES.O)
    //fun getListCibo(date :LocalDate) : MutableList<Cibo>{

        //prendi tb da database
        //crea oggetti cibo da questa tabella
        //riempi una lista con questi oggetti

        //val lista = mappa[date]

        //mFoodViewModel = ViewModelProvider(getContext()).get(FoodViewModel::class.java)
        //return if(lista==null) mutableListOf<Cibo>() else lista
       // }

    fun riempiMap(){
        for(i in 1..10){
            val listaAlimento= mutableListOf<Cibo>()
            for (j in 1..10){
                var alimento= Cibo("aliemto n $i$j",1950+(j*13)+i)
                listaAlimento.add(alimento)
            }
            mappa[LocalDate.now().plusDays(i.toLong())]= listaAlimento

        }
    }
}
