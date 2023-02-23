package com.cv.obegallery.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.cv.obegallery.databinding.ItemGridPhotosBinding
import com.cv.obegallery.models.NasaData

class HomeGridAdapter : ListAdapter<NasaData, RecyclerView.ViewHolder>(DiffCallback) {

    inner class HomeGridViewHolder(private val binding: ItemGridPhotosBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(nasaData: NasaData, position: Int) {
            binding.thumbnail.load(nasaData.url)
            binding.title.text = nasaData.title
            binding.date.text = nasaData.date
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<NasaData>() {
        override fun areItemsTheSame(oldItem: NasaData, newItem: NasaData): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: NasaData, newItem: NasaData): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemGridPhotosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeGridViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val adjustViewHolder = holder as HomeGridViewHolder
        val cast = getItem(position)

        cast.let {
            adjustViewHolder.onBind(it, position)
        }
    }
}