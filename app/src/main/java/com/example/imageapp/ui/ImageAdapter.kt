package com.example.imageapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imageapp.databinding.ItemLayoutBinding
import com.example.imageapp.models.ImageModelItem

class ImageAdapter(private var imagelist: List<ImageModelItem> , private var clickListener: onClickListener ) :
        RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
    class ImageViewHolder(binding : ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        val image = binding.imageView
        val name = binding.nameImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun getItemCount(): Int {
        return imagelist.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val model = imagelist[position]

        Glide.with(holder.itemView.context)
                .load(model.url)
                .centerCrop()
                .into(holder.image)
        holder.name.text = model.title

        holder.itemView.setOnClickListener {
            clickListener?.onClick(position)
        }
    }

    interface onClickListener{
        fun onClick(position: Int)
    }

}