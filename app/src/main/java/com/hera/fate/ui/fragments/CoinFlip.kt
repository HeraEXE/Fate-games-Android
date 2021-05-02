package com.hera.fate.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.hera.fate.R
import com.hera.fate.databinding.FragmentCoinFlipBinding
import com.hera.fate.utils.ImageCollection.coinImages
import kotlin.random.Random

class CoinFlip : Fragment() {
    private var _binding: FragmentCoinFlipBinding? = null
    private val binding get() = _binding!!
    private var result: Int? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        // Define binding.
        _binding = FragmentCoinFlipBinding.inflate(inflater, container, false)

        // Setting action bar title.
        (activity as AppCompatActivity).supportActionBar?.setTitle(R.string.coin_flip_title)

        // Getting data from bundle.
        if (savedInstanceState != null) {
            with(savedInstanceState) {
                result = getInt(COIN_IMAGE)
                if (result == 0)
                    result = null
            }
        }

        // Load animations.
        val rotate = AnimationUtils.loadAnimation(activity, R.anim.rotate)

        // Change coin image when user clicks.
        binding.coinImage.setOnClickListener {
            if (binding.flipCoinHint.visibility == View.VISIBLE)
                binding.flipCoinHint.visibility = View.GONE

            result = coinImages[Random.nextInt(coinImages.size)]
            loadCoinImage()
            binding.coinImage.startAnimation(rotate)
        }

        if (result != null) {
            binding.flipCoinHint.visibility = View.GONE
            loadCoinImage()
            binding.coinImage.startAnimation(rotate)
        }

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putInt(COIN_IMAGE, result ?: 0)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun loadCoinImage() {
        Glide.with(binding.root)
                .load(result)
                .into(binding.coinImage)
    }

    companion object {
        const val COIN_IMAGE = "coin_image"
    }
}