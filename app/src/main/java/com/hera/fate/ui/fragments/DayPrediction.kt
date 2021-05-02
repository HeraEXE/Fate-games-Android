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
import com.hera.fate.databinding.FragmentDayPredictionBinding
import com.hera.fate.utils.ImageCollection.faceImages
import kotlin.random.Random

class DayPrediction : Fragment() {
    private var _binding: FragmentDayPredictionBinding? = null
    private val binding get() = _binding!!
    private var result: Int? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        // Defining binding.
        _binding = FragmentDayPredictionBinding.inflate(inflater, container, false)

        // Setting action bar title.
        (activity as AppCompatActivity).supportActionBar?.setTitle(R.string.day_prediction_title)

        // Getting data from bundle.
        if (savedInstanceState != null) {
            with(savedInstanceState) {
                result = getInt(FACE_IMAGE)
                if (result == 0)
                    result = null
            }
        }

        // Load animations.
        val zoomIn = AnimationUtils.loadAnimation(activity, R.anim.zoom_in)

        // Change face image when user clicks.
        binding.faceImage.setOnClickListener {
            if (binding.predictDayHint.visibility == View.VISIBLE)
                binding.predictDayHint.visibility = View.GONE

            result = faceImages[Random.nextInt(faceImages.size)]
            loadFaceImage()
            binding.faceImage.startAnimation(zoomIn)
        }

        if (result != null) {
            binding.predictDayHint.visibility = View.GONE
            loadFaceImage()
            binding.faceImage.startAnimation(zoomIn)
        }

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putInt(FACE_IMAGE, result ?: 0)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun loadFaceImage() {
        Glide.with(binding.root)
                .load(result)
                .into(binding.faceImage)
    }

    companion object {
        const val FACE_IMAGE = "face_image"
    }
}