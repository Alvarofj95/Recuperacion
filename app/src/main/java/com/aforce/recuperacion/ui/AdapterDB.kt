package com.aforce.recuperacion.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aforce.recuperacion.Extensions.imageUrl
import com.aforce.recuperacion.R
import com.aforce.recuperacion.databinding.ItemListBinding
import com.aforce.recuperacion.db.ProductDb
import okhttp3.internal.notify

class AdapterDB(
    private val onProductClicked:(ProductDb) -> Unit,
    private val likeNoLike:(ProductDb) -> Boolean,
    private val dislike: (ProductDb) -> Unit

) :
   ListAdapter<ProductDb, AdapterDB.AdapterDBViewHolder>(ProductDBItemCallBack()) {
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
        holder.binding.tvPriceItem.text = product.discountPrice.toString()
        holder.binding.ivProductItem.imageUrl(product.imageUrl)
        if (product.stock<= 5) {
            holder.binding.tvAlertItem.isVisible = true
        }

        holder.binding.ibNoLikeItem.setOnClickListener{
            dislike(product)
            Log.e("likeNoLike", "$likeNoLike")
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

        holder.binding.ibNoLikeItem.setImageResource(R.drawable.ic_baseline_favorite_24)
    }
}

class ProductDBItemCallBack : DiffUtil.ItemCallback<ProductDb>() {
    override fun areItemsTheSame(oldItem: ProductDb, newItem: ProductDb): Boolean {
        return oldItem.idApi == newItem.idApi
    }

    override fun areContentsTheSame(oldItem: ProductDb, newItem: ProductDb): Boolean {
        return oldItem.name == newItem.name
    }
}