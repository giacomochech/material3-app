package com.example.material3app

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

@RequiresApi(Build.VERSION_CODES.O)

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        WindowCompat.setDecorFitsSystemWindows(window,false)
        window.navigationBarColor= Color.TRANSPARENT

        val navigationBar = findViewById<BottomNavigationView>(R.id.bottom_navigation_main)
        navigationBar.setOnApplyWindowInsetsListener{ _,insets->
            val inset1= WindowInsetsCompat.toWindowInsetsCompat(insets).getInsets(WindowInsetsCompat.Type.systemBars())
            navigationBar.setPadding(0,0,0,inset1.bottom)
            findViewById<View>(R.id.fr_wreapper).setPadding(0,inset1.top,0,0)
            insets
        }

        navigationBar.setupWithNavController((supportFragmentManager.findFragmentById(R.id.fr_wreapper) as NavHostFragment).navController)

    }


}