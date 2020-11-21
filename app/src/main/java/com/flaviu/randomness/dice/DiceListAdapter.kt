package com.flaviu.randomness.dice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flaviu.randomness.R
import com.flaviu.randomness.databinding.SimpleImageBinding

enum class DiceNumber{
    DICE1,
    DICE2,
    DICE3,
    DICE4,
    DICE5,
    DICE6
}

class DiceListAdapter : RecyclerView.Adapter<DiceNumberHolder>() {
    var data = listOf<DiceNumber>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiceNumberHolder {
        return DiceNumberHolder.from(parent)
    }

    override fun onBindViewHolder(holder: DiceNumberHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size
}

class DiceNumberHolder private constructor(private val binding: SimpleImageBinding): RecyclerView.ViewHolder(binding.root){
    fun bind(diceNumber: DiceNumber) {
        binding.image.setImageResource(when(diceNumber) {
            DiceNumber.DICE1 -> R.drawable.dice_1
            DiceNumber.DICE2 -> R.drawable.dice_2
            DiceNumber.DICE3 -> R.drawable.dice_3
            DiceNumber.DICE4 -> R.drawable.dice_4
            DiceNumber.DICE5 -> R.drawable.dice_5
            DiceNumber.DICE6 -> R.drawable.dice_6
        })
    }

    companion object {
        fun from(parent: ViewGroup): DiceNumberHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = SimpleImageBinding.inflate(layoutInflater, parent, false)
            return DiceNumberHolder(binding)
        }
    }
}

