package com.hera.fate.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.hera.fate.R
import com.hera.fate.databinding.FragmentCoinFlipBinding

class CoinFlip : Fragment() {
    private var _binding: FragmentCoinFlipBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        // Define binding.
        _binding = FragmentCoinFlipBinding.inflate(inflater, container, false)

        // Setting action bar title.
        (activity as AppCompatActivity).supportActionBar?.setTitle(R.string.coin_flip_title)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}