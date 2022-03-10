package com.aforce.recuperacion.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aforce.recuperacion.R
import com.aforce.recuperacion.databinding.FragmentDetailBinding
import com.aforce.recuperacion.db
import com.aforce.recuperacion.db.ProductDb
import com.aforce.recuperacion.model.Product
import java.util.*

class FragmentListFav : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding
        get() = _binding!!
    private val adapter = Adapter(::toggleChecked, ::onTaskClicked)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    private fun toggleChecked(product: ProductDb) {
        product.like = !product.like
        db.dao().updateProduct(product)
        adapter.submitList(db.dao().getAll())
    }
}