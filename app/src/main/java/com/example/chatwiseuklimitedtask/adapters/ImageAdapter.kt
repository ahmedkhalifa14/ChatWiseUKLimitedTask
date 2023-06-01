package com.example.chatwiseuklimitedtask.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chatwiseuklimitedtask.databinding.ImagesLayoutBinding
import com.example.chatwiseuklimitedtask.models.ImageItem

class ImageAdapter(): RecyclerView.Adapter<ImageAdapter.ImagesViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<ImageItem>() {
        override fun areContentsTheSame(oldItem: ImageItem, newItem:ImageItem): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areItemsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean {
            return oldItem.id == newItem.id
        }

    }
     val differ = AsyncListDiffer(this, diffCallback)
     inner class ImagesViewHolder(private val itemBinding: ImagesLayoutBinding
     ):RecyclerView.ViewHolder(itemBinding.root) {
         fun bindData(imageItem:ImageItem){
             itemBinding.root.apply {
                 Glide.with(context).load(imageItem.url).into(itemBinding.image)
             }
         }

    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
     val itemBinding=
         ImagesLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ImagesViewHolder(itemBinding)
    }

    override fun getItemCount(): Int =differ.currentList.size

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        val imageItem =differ.currentList[position]
        holder.apply {
            bindData(imageItem)
        }

    }
}