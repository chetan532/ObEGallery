package com.cv.obegallery.presentation.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.cv.obegallery.databinding.ItemFullImagePagerBinding
import com.cv.obegallery.models.NasaData

class DetailsPagerAdapter : ListAdapter<NasaData, RecyclerView.ViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<NasaData>() {
        override fun areItemsTheSame(oldItem: NasaData, newItem: NasaData): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: NasaData, newItem: NasaData): Boolean {
            return oldItem == newItem
        }
    }

    inner class DetailsPagerViewHolder(private val binding: ItemFullImagePagerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(nasaData: NasaData, position: Int) {
            binding.largeImage.load(nasaData.url)
            binding.title.text = nasaData.title
            binding.date.text = nasaData.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemFullImagePagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailsPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as DetailsPagerViewHolder
        val cast = getItem(position)

        cast.let {
            viewHolder.onBind(it, position)
        }
    }
}