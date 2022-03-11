package com.aforce.recuperacion.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.aforce.recuperacion.databinding.FragmentListFavBinding
import com.aforce.recuperacion.db.DatabaseProduct
import com.aforce.recuperacion.db.ProductDb
import com.aforce.recuperacion.model.Product
import java.util.*

class FragmentListFav : Fragment() {

    private var _binding: FragmentListFavBinding? = null
    private val binding
        get() = _binding!!
    private var product: MutableList<ProductDb> = mutableListOf()
    private val adapter = AdapterDB({ onProductClick(it.idApi) }, { onNoLikeClicked()})


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListFavBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvProductFav.adapter = adapter
        binding.rvProductFav.layoutManager = GridLayoutManager(context, 2)

        requestDataDatabase()
    }

//TODO -> HACER PETICION A LA BBDD PARA QUE DEVUELVA TODO
//TODO -> HACER UNA FUNC QUE AL DAR DISLIKE BORRE EL REGISTRO

    //private fun toggleChecked(product: ProductDb) {
    //    product.like = !product.like
    //    db.dao().updateProduct(product)
    //    adapter.submitList(db.dao().getAll())
    //}

    private fun requestDataDatabase() {
        product = DatabaseProduct.getInstance(requireContext()).dao().findByFav().toMutableList()
        Log.e("producto", "$product")
        adapter.submitList(product)
    }


    private fun onProductClick(idApi: String) {
        val action = FragmentListDirections.actionFragmentListToFragmentDetail(idApi)
        findNavController().navigate(action)
    }

    private fun onNoLikeClicked(): Boolean {
        return true
    }
}