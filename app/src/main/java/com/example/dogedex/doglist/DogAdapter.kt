package com.example.dogedex.doglist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.dogedex.model.Dog
import com.example.dogedex.databinding.DogListItemBinding

class DogAdapter() : ListAdapter<Dog, DogAdapter.DogViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Dog>() {
        override fun areItemsTheSame(oldItem: Dog, newItem: Dog): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Dog, newItem: Dog): Boolean {
            return  oldItem.id == newItem.id
        }
    }

    private var onItemClickListener : ((Dog) -> Unit)? = null
    fun setOnItemClickListerner(onItemClickListener : (Dog) -> Unit) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val binding = DogListItemBinding.inflate(LayoutInflater.from(parent.context))
        val viewHolder = DogViewHolder(binding)
        return viewHolder
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val dog = getItem(position)
        holder.bind(dog)
    }

    inner class DogViewHolder(val binding: DogListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dog : Dog) {
            binding.dogImage.load(dog.imageUrl)
            binding.dogListItemLayout.setOnClickListener {
                onItemClickListener?.invoke(dog)
            }
        }
    }

}