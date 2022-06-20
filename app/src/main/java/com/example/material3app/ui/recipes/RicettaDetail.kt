package com.example.material3app.ui.recipes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.material3app.R
import com.example.material3app.RICETTA_ID_EXTRA
import com.example.material3app.Ricetta
import com.example.material3app.databinding.ActivityRicettaDetailBinding

class RicettaDetail : AppCompatActivity()
{
    private lateinit var binding: ActivityRicettaDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRicettaDetailBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_ricetta_detail)

        val ricettaID = intent.getIntExtra(RICETTA_ID_EXTRA, -1)
        //val ricetta = ricettafromID() //chiamata database
    }

    /*private fun ricettafromID(): Ricetta {

        return
    }
    */

}