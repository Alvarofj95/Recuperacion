package com.aforce.recuperacion.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.room.Room
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
    private var product: MutableList<String> = mutableListOf()
    private val adapter = AdapterDB({ onProductClick(it.id) }, { onNoLikeClicked(it.id)})


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

//TODO -> HACER PETICION A LA BBDD PARA QUE DEVUELVA TODO
//TODO -> HACER UNA FUNC QUE AL DAR DISLIKE BORRE EL REGISTRO

    //private fun toggleChecked(product: ProductDb) {
    //    product.like = !product.like
    //    db.dao().updateProduct(product)
    //    adapter.submitList(db.dao().getAll())
    //}



    private fun onProductClick(id: String) {
        val action = FragmentListDirections.actionFragmentListToFragmentDetail(id)
        findNavController().navigate(action)
    }

    private fun onNoLikeClicked(id: String): Boolean {
        return product.contains(id)
    }
}