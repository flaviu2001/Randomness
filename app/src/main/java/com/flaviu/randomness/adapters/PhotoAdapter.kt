package com.flaviu.randomness.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flaviu.randomness.PhotoType
import com.flaviu.randomness.R
import com.flaviu.randomness.databinding.SimpleImageBinding

class PhotoTypeAdapter : RecyclerView.Adapter<PhotoTypeHolder>() {
    var data = listOf<PhotoType>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoTypeHolder {
        return PhotoTypeHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PhotoTypeHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size
}

class PhotoTypeHolder private constructor(private val binding: SimpleImageBinding): RecyclerView.ViewHolder(binding.root){
    fun bind(photoType: PhotoType) {
        binding.image.setImageResource(when (photoType) {
            PhotoType.DICE1 -> R.drawable.dice_1
            PhotoType.DICE2 -> R.drawable.dice_2
            PhotoType.DICE3 -> R.drawable.dice_3
            PhotoType.DICE4 -> R.drawable.dice_4
            PhotoType.DICE5 -> R.drawable.dice_5
            PhotoType.DICE6 -> R.drawable.dice_6
            PhotoType.HEADS -> R.drawable.heads
            PhotoType.TAILS -> R.drawable.tails
        })
    }

    companion object {
        fun from(parent: ViewGroup): PhotoTypeHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = SimpleImageBinding.inflate(layoutInflater, parent, false)
            return PhotoTypeHolder(binding)
        }
    }
}

