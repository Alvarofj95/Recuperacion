package com.aforce.recuperacion.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aforce.recuperacion.databinding.ItemListBinding
import com.aforce.recuperacion.model.Product

class Adapter(private val onProductClicked:(Product) -> Unit) :
        ListAdapter<Product, Adapter.ViewHolder>(ProductItemCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = getItem(position)
        //holder.binding.tvName.text = anime.name
        //holder.binding.tvCategory.text = anime.category
        //holder.binding.ivAnime.imageUrl(anime.url)
        //holder.binding.root.setOnClickListener{
        //    onAnimeClicked(anime)
        //}
    }

    inner class ViewHolder(val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root)

}

class ProductItemCallBack : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.name == newItem.name
    }
}