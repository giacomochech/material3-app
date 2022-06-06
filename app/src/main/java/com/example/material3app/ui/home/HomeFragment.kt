package com.example.material3app.ui.home

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.material3app.databinding.FragmentHomeBinding
import java.time.LocalDate

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = FragmentHomeBinding.inflate(inflater, container, false)// infalete(r.layoutfragment home...)
        val root: View = binding.root

        /*
       
        //TODO() : riempi da database

*/
        val viewPager = binding.ViewPager//r.id.text_home
        viewPager.adapter = AdapterViewPager()
        viewPager.setCurrentItem(Int.MAX_VALUE/2,false)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
    inner class AdapterViewPager() : FragmentStateAdapter(childFragmentManager,lifecycle){
        override fun getItemCount(): Int {
          return Int.MAX_VALUE
        }

        @RequiresApi(Build.VERSION_CODES.O)
        override fun createFragment(position: Int): Fragment {
            val key = LocalDate.now().plusDays((position-Int.MAX_VALUE/2).toLong())

            return ListaCiboFragment().apply {
                date=key
                KcalAssunte=0
            }
        }



    }
}