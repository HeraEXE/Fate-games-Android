package com.hera.fate.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.hera.fate.R
import com.hera.fate.databinding.FragmentDayPredictionBinding

class DayPrediction : Fragment() {
    private var _binding: FragmentDayPredictionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        // Defining binding.
        _binding = FragmentDayPredictionBinding.inflate(inflater, container, false)

        // Setting action bar title.
        (activity as AppCompatActivity).supportActionBar?.setTitle(R.string.day_prediction_title)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}