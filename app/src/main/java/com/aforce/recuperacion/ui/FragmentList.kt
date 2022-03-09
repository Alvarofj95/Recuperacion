package com.aforce.recuperacion.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.aforce.recuperacion.databinding.FragmentListBinding
import com.aforce.recuperacion.model.Product
import com.aforce.recuperacion.network.NetworkConfig
import retrofit2.Call
import retrofit2.Response


class FragmentList : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding
        get() = _binding!!

    private val adapter = Adapter { onProductClick(it.id) }

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
                    Toast.makeText(context, "Error en la respuesta", Toast.LENGTH_SHORT).show()
                    val code = response.code()
                    val message = response.message()
                    Log.e("requestData", "error en la respuesta: $code <> $message")
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                Toast.makeText(context, "Error en la conexion", Toast.LENGTH_SHORT).show()
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}



