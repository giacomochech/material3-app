package com.example.material3app

import androidx.lifecycle.ViewModel
import java.time.LocalDate
//viewModel per passare la data tra l'addfragment e il viewPager
class MainActivityViewModel : ViewModel() {
    lateinit var data : LocalDate
}