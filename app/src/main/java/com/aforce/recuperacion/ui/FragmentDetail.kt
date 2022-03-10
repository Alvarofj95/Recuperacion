package com.aforce.recuperacion.ui

import android.os.Bundle
import android.os.Message
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.aforce.recuperacion.Extensions.imageUrl
import com.aforce.recuperacion.R
import com.aforce.recuperacion.databinding.FragmentDetailBinding
import com.aforce.recuperacion.model.Product
import com.aforce.recuperacion.network.NetworkConfig
import retrofit2.Call
import retrofit2.Response

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvNameDetail.text = args.id

        args.id?.let {
            requestData(it)
        } ?: showError("ProductId Null")
    }

    private fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun requestData(productId: String) {
        NetworkConfig.service.getProductById(productId).enqueue(object : retrofit2.Callback<Product> {
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                if(response.isSuccessful) {
                    populateUi(response.body())
                } else {
                    showError("Error en la respuesta")
                    val code = response.code()
                    val message = response.message()
                    Log.e("requestData", "error en la respuesta: $code <> $message")
                }
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                Log.e("requestData", "error", t)
                showError("Error en la conexion")
            }

        })
    }

    private fun populateUi(product: Product?) {
        product?.let {
            binding.tvNameDetail.text = it.name
            binding.tvCountDetail.text = it.stock.toString()
            binding.tvPriceDetail.text = it.regularPrice.toString()
            binding.tvPriceDisDetail.text = it.discountPrice.toString()
            binding.tvDescriptionDetail.text = it.description
            binding.ivProductDetail.imageUrl(it.imageUrl)


            if (it.discountPrice.toInt() < it.regularPrice.toInt()) {
                binding.tvPriceDisDetail.isVisible = true
                binding.textView9.isVisible = true
            }

            if (it.stock > 0 ) {
                binding.tvDisponible.isVisible = true
            }
        }
    }
}