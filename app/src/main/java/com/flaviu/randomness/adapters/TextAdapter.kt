package com.flaviu.randomness.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flaviu.randomness.R
import com.flaviu.randomness.databinding.SimpleTextBinding

class TextAdapter : RecyclerView.Adapter<TextHolder>() {
    var data = listOf<Long>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextHolder {
        return TextHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TextHolder, position: Int) {
        holder.bind(Pair(position+1, data[position]))
    }

    override fun getItemCount() = data.size
}

class TextHolder private constructor(private val binding: SimpleTextBinding): RecyclerView.ViewHolder(binding.root){
    fun bind(pair: Pair<Int, Long>) {
        binding.textView.text = binding.textView.context.getString(R.string.result_format2).format(pair.first, pair.second)
    }

    companion object {
        fun from(parent: ViewGroup): TextHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = SimpleTextBinding.inflate(layoutInflater, parent, false)
            return TextHolder(binding)
        }
    }
}