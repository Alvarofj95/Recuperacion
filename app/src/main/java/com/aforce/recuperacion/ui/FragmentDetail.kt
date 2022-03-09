package com.aforce.recuperacion.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.aforce.recuperacion.R
import com.aforce.recuperacion.databinding.FragmentDetailBinding

class FragmentDetail : Fragment() {
    private val args: FragmentDetailArgs by navArgs()
    private var _binding: FragmentDetailBinding? = null
    private val binding
    get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }



}