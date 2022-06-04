package com.example.material3app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.material3app.databinding.FragmentHomeBinding

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

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
    inner class AdapterViewPager : FragmentStateAdapter(childFragmentManager,lifecycle){
        override fun getItemCount(): Int {
          return Int.MAX_VALUE
        }

        override fun createFragment(position: Int): Fragment {
            return ListaCiboFragment()
        }



    }
}