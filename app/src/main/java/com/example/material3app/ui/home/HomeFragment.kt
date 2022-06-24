package com.example.material3app.ui.home

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.material3app.MainActivityViewModel
import com.example.material3app.R
import com.example.material3app.databinding.FragmentHomeBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class HomeFragment : Fragment() {
//fragment base della schermata diario
    private lateinit var binding: FragmentHomeBinding

    private val viewModel by activityViewModels<MainActivityViewModel>()


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = FragmentHomeBinding.inflate(inflater, container, false)// infalete(r.layoutfragment home...)
        val root: View = binding.root



        val fab = root.findViewById<FloatingActionButton>(R.id.floating_action_button)
        fab.setOnClickListener {
            val dialog : DialogFragment = AddFragmentDialog()
            val args = Bundle()
            args.putString(BOUNDLE_DATE,viewModel.data.format(DateTimeFormatter.ISO_DATE))
            dialog.arguments = args
            dialog.show(parentFragmentManager,"ADD DIALOG")

        }

        val viewPager = binding.ViewPager//r.id.text_home
        viewPager.adapter = AdapterViewPager()
        viewPager.setCurrentItem(Int.MAX_VALUE/2,false)

        return root
    }
//adapter per il viewPager
    inner class AdapterViewPager : FragmentStateAdapter(childFragmentManager,lifecycle){
        override fun getItemCount(): Int {
          return Int.MAX_VALUE
        }

        @RequiresApi(Build.VERSION_CODES.O)
        override fun createFragment(position: Int): Fragment {
            val key = LocalDate.now().plusDays((position-Int.MAX_VALUE/2).toLong())

            return ListaCiboFragment().apply {
                date=key
                kCalAssunte=0

            }
        }



    }
    companion object{
        const val BOUNDLE_DATE = "dateCurret"
    }


}