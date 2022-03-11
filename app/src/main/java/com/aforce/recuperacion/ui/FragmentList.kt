package com.aforce.recuperacion.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.aforce.recuperacion.databinding.FragmentListBinding
import com.aforce.recuperacion.db.DatabaseProduct
import com.aforce.recuperacion.db.ProductDb
import com.aforce.recuperacion.model.Product
import com.aforce.recuperacion.network.NetworkConfig
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import retrofit2.Call
import retrofit2.Response


class FragmentList : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding
        get() = _binding!!
    private var product: MutableList<Product> = mutableListOf()
    private val adapter = Adapter ({ onProductClick(it.id) }, { onNoLikeClicked(it)}, {saveDelete(it)})

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fabAddProduct.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
        binding.searchView.visibility = View.VISIBLE

        configUi()
        binding.rvProduct.adapter = adapter
        binding.rvProduct.layoutManager = GridLayoutManager(context, 2)
        binding.fabAddProduct.setOnClickListener {
            val action = FragmentListDirections.actionFragmentListToFragmentAdd()
            findNavController().navigate(action)
        }

        requestData()
    }

    private fun requestData() {
        NetworkConfig.service.getProduct().enqueue(object : retrofit2.Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                if(response.isSuccessful) {
                    binding.progressBar.visibility = View.GONE
                    binding.fabAddProduct.visibility = View.VISIBLE
                    binding.searchView.visibility = View.VISIBLE
                    adapter.submitList(response.body())
                } else {
                    MaterialAlertDialogBuilder(requireContext())
                        .setTitle("Ups...")
                        .setMessage("Error en la respuesta")
                        .setPositiveButton("OK") { dialog, which ->}
                        .show()

                    val code = response.code()
                    val message = response.message()
                    Log.e("requestData", "error en la respuesta: $code <> $message")
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Ups...")
                    .setMessage("Error en la conexion")
                    .setPositiveButton("OK") { dialog, which ->}
                    .show()
                Log.e("requestData", "error", t)
            }
        })
    }

    private fun configUi() {
        binding.fabAddProduct.setOnClickListener {
            val action = FragmentListDirections.actionFragmentListToFragmentAdd()
            findNavController().navigate(action)
        }
    }

    private fun onProductClick(id: String) {

        val action = FragmentListDirections.actionFragmentListToFragmentDetail(id)
        findNavController().navigate(action)
    }

    private fun onNoLikeClicked(productUnit: Product): Boolean {
        return product.contains(productUnit)
    }
    //TODO -> HACER PETICION A LA BBDD PARA QUE DEVUELVA TODO
//TODO -> HACER UNA FUNC QUE AL DAR DISLIKE BORRE EL REGISTRO
//TODO -> FUNCION QUE AL DAR LIKE SE GUARDE EN LA BBDD
//TODO -> HACER EL SEARCH
    private fun searchProduct() {
        
    }

    private fun productLike(productLik: Product){

        val newProduct = ProductDb(
            productLik.id,
            productLik.name,
            productLik.stock,
            productLik.discountPrice,
            productLik.imageUrl,
            liked = true
        )

        DatabaseProduct.getInstance(requireContext()).dao().insertProduct(newProduct)
        product.add(productLik)
    }

    private fun productDislike(productLik: Product){

        val newProduct = ProductDb(
            productLik.id,
            productLik.name,
            productLik.stock,
            productLik.discountPrice,
            productLik.imageUrl,
            liked = false
        )

        DatabaseProduct.getInstance(requireContext()).dao().deleteProduct(newProduct)
        product.remove(productLik)
    }

    private fun saveDelete(productUnit: Product){
        if (product.contains(productUnit)) {
            productDislike(productUnit)
        } else {
            productLike(productUnit)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}



