package com.example.material3app.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.material3app.Cibo
import java.time.LocalDate
@RequiresApi(Build.VERSION_CODES.O)
class DataFormDb {
    var mappa = mutableMapOf<LocalDate,MutableList<Cibo>>()

    fun getListCibo(date :LocalDate) : MutableList<Cibo>{

        riempiMap()//riempi map da DataBase

        val lista = mappa[date]

        return if(lista==null) mutableListOf<Cibo>() else lista
    }

    fun riempiMap(){
        for(i in 1..10){
            val listaAlimeto= mutableListOf<Cibo>()
            for (j in 1..10){
                var alimento= Cibo("aliemto n $i$j",1950+(j*13)+i)
                listaAlimeto.add(alimento)
            }
        mappa[LocalDate.now().plusDays(i.toLong())]= listaAlimeto

        }
    }
}
