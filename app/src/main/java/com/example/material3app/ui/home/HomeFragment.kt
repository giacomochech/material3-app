package com.example.material3app.ui.home

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.material3app.databinding.FragmentHomeBinding
import java.time.LocalDate
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = FragmentHomeBinding.inflate(inflater, container, false)// infalete(r.layoutfragment home...)
        val root: View = binding.root



        val viewPager = binding.ViewPager//r.id.text_home
        viewPager.adapter = AdapterViewPager()
        viewPager.setCurrentItem(Int.MAX_VALUE/2,false)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
    inner class AdapterViewPager : FragmentStateAdapter(childFragmentManager,lifecycle){
        override fun getItemCount(): Int {
          return Int.MAX_VALUE
        }

        @RequiresApi(Build.VERSION_CODES.O)
        override fun createFragment(position: Int): Fragment {


            val day =  LocalDate.now().plusDays((position-Int.MAX_VALUE/2).toLong()).dayOfMonth
            val month = LocalDate.now().plusDays((position-Int.MAX_VALUE/2).toLong()).month

            return ListaCiboFragment(day,month,1000)
        }



    }
}