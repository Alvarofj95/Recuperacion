package com.aforce.recuperacion.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.aforce.recuperacion.databinding.FragmentAddBinding
import com.aforce.recuperacion.model.ProductBody
import com.aforce.recuperacion.network.NetworkConfig
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class FragmentAdd : Fragment() {
    private var _binding: FragmentAddBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnConfirm.setOnClickListener{
            if(validateFields()){
                sendProductToServer()
            }
        }
    }

    private fun validateFields(): Boolean {
        val productName = binding.etName.text.toString()
        if (productName.isEmpty()) {
            showError("Product Name is empty")
            return false
        }

        val productDescription = binding.etDescription.text.toString()
        if (productDescription.isEmpty()) {
            showError("Product Description is empty")
            return false
        }

        val productStock = binding.etStock.text.toString()
        if (productStock.isEmpty()) {
            showError("Product Stock is empty")
            return false
        }

        val productRegularPrice = binding.etRegularPrice.text.toString()
        if (productRegularPrice.isEmpty()) {
            showError("Product Price is empty")
            return false
        }

        val productDiscountPrice = binding.etDiscountPrice.text.toString()
        if (productDiscountPrice.isEmpty()) {
            showError("Product Discount is empty")
            return false
        }

        val productImage = binding.etImage.text.toString()
        if (productImage.isEmpty()) {
            showError("Product Image is empty")
            return false
        }

        return true
    }

    private fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun sendProductToServer() {
        val productName = binding.etName.text.toString()
        val productDescription = binding.etDescription.text.toString()
        val productStock = binding.etStock.text.toString()
        val productRegularPrice = binding.etRegularPrice.text.toString()
        val productDiscountPrice = binding.etDiscountPrice.text.toString()
        val productImage = binding.etImage.text.toString()
        var productAvailable: Boolean = false
        if (productStock.toInt() > 0) {
            productAvailable = true
        }

        val productRequest = ProductBody(productName, productDescription, productStock.toInt(), productRegularPrice.toFloat(), productDiscountPrice.toFloat(), productImage, productAvailable)

        NetworkConfig.service.createProduct(productRequest).enqueue(object : Callback<Any> {
            override fun onResponse(Call: Call<Any>, response : Response<Any>) {
                if (response.isSuccessful) {
                    Toast.makeText(context, "Añadido correctamente", Toast.LENGTH_SHORT).show()
                    resetScreen()
                } else {
                    showError("Error en la respuesta")
                    val code = response.code()
                    val message = response.message()
                    Log.e("requestData", "error en la respuesta: $code <> $message")
                }
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {

            }
        })
    }

    private fun resetScreen() {
        binding.etName.text = null
        binding.etDescription.text = null
        binding.etStock.text = null
        binding.etRegularPrice.text = null
        binding.etDiscountPrice.text = null
        binding.etImage.text = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}