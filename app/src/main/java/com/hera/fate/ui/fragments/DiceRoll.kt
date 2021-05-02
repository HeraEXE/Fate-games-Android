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
import com.hera.fate.databinding.FragmentDiceRollBinding
import com.hera.fate.utils.ImageCollection.diceImages
import kotlin.random.Random

class DiceRoll : Fragment() {
    private var _binding: FragmentDiceRollBinding? = null
    private val binding get() = _binding!!
    private var result: Int? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        // Defining binding.  
        _binding = FragmentDiceRollBinding.inflate(inflater, container, false)

        // Setting action bar title.
        (activity as AppCompatActivity).supportActionBar?.setTitle(R.string.dice_roll_title)

        // Getting data from bundle.
        if (savedInstanceState != null) {
            with(savedInstanceState) {
                result = getInt(DICE_IMAGE)
                if (result == 0)
                    result = null
            }
        }

        // Load animations.
        val fadeIn = AnimationUtils.loadAnimation(activity, R.anim.fade_in)

        // Change dice image when user clicks.
        binding.diceImage.setOnClickListener {
            if (binding.rollDiceHint.visibility == View.VISIBLE)
                binding.rollDiceHint.visibility = View.GONE
            result = diceImages[Random.nextInt(diceImages.size)]
            loadDiceImage()
            binding.diceImage.startAnimation(fadeIn)
        }

        if (result != null) {
            binding.rollDiceHint.visibility = View.GONE
            loadDiceImage()
            binding.diceImage.startAnimation(fadeIn)
        }

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putInt(DICE_IMAGE, result ?: 0)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun loadDiceImage() {
        Glide.with(binding.root)
            .load(result)
            .into(binding.diceImage)
    }

    companion object {
        const val DICE_IMAGE = "dice_image"
    }
}