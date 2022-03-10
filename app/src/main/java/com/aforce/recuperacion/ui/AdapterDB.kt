package com.aforce.recuperacion.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aforce.recuperacion.Extensions.imageUrl
import com.aforce.recuperacion.R
import com.aforce.recuperacion.databinding.ItemListBinding
import com.aforce.recuperacion.model.Product

class AdapterDB(
    private val onProductClicked:(Product) -> Unit,
    private val likeNoLike:(Product) -> Boolean

) :
    ListAdapter<Product, AdapterDB.AdapterDBViewHolder>(ProductDBItemCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterDBViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(inflater, parent, false)
        return AdapterDBViewHolder(binding)
    }

    private fun isLike(holder: AdapterDBViewHolder, likeNoLike: (Boolean)){
        with(holder.binding) {
            if (likeNoLike) {
                ibNoLikeItem.setImageResource(R.drawable.ic_baseline_favorite_24)
            } else {
                ibNoLikeItem.setImageResource(R.drawable.ic_baseline_favorite_border_35)
            }
        }
    }

    inner class AdapterDBViewHolder(val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: AdapterDB.AdapterDBViewHolder, position: Int) {
        val product = getItem(position)
        var likeNoLike = likeNoLike(product)
        //TODO PONER PRECIO CON DESCUENTO
        holder.binding.tvNameItem.text = product.name
        holder.binding.tvPriceItem.text = product.regularPrice.toString()
        holder.binding.ivProductItem.imageUrl(product.imageUrl)
        if (product.stock<= 5) {
            holder.binding.tvAlertItem.isVisible = true
        }

        holder.binding.ibNoLikeItem.setOnClickListener{
            if (likeNoLike == true) {
                likeNoLike = false
            } else {
                likeNoLike = true
            }
            isLike(holder, likeNoLike)
        }

        holder.binding.root.setOnClickListener{
            onProductClicked(product)
        }


    }

}

class ProductDBItemCallBack : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.name == newItem.name
    }
}