package com.example.notes.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.notes.R
import com.example.notes.databinding.FragmentChartBinding


class ChartFragment : Fragment() {
        lateinit var binding: FragmentChartBinding
        private var progr : Int ?= null
        val note by navArgs<ChartFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentChartBinding.inflate(layoutInflater,container,false)

        updateProgressBar()



        return binding.root
    }

    private fun updateProgressBar(){

        progr = note.data.hour.toInt() * 100 / 8

        binding.progressBar.progress =  progr!!.toInt()
        binding.textViewProgress.text = "$progr%"
    }


}