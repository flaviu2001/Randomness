package com.flaviu.randomness.range

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.flaviu.randomness.R
import com.flaviu.randomness.adapters.TextAdapter
import com.flaviu.randomness.databinding.FragmentRangeBinding
import com.google.android.material.snackbar.Snackbar
import kotlin.math.abs
import kotlin.random.Random

class RangeFragment : Fragment() {
    private lateinit var binding: FragmentRangeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentRangeBinding.inflate(inflater)
        val adapter = TextAdapter()
        binding.numberList.adapter = adapter
        binding.generate.setOnClickListener{
            val from: Long
            val to: Long
            try{
                from = binding.fromEditText.text.toString().toLong()
                to = binding.toEditText.text.toString().toLong()
                if (abs(from) > 1000_000_000_000_000_000L || abs(to) > 1000_000_000_000_000_000L)
                    throw Exception()
            }catch (e: Exception) {
                Snackbar.make(requireView(), requireContext().getString(R.string.valid_number3), Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (from > to) {
                Snackbar.make(requireView(), requireContext().getString(R.string.valid_number4), Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            binding.numberList.visibility = RecyclerView.GONE
            binding.result.visibility = TextView.VISIBLE
            binding.result.text = requireContext().getString(R.string.result_format).format(Random.nextLong(from, to))
            binding.result.startAnimation(AnimationUtils.loadAnimation(requireContext(),
                R.anim.coin_animation
            ))
        }
        binding.shuffle.setOnClickListener{
            val from: Long
            val to: Long
            try{
                from = binding.fromEditText.text.toString().toLong()
                to = binding.toEditText.text.toString().toLong()
                if (abs(from) > 1_000_000_000_000_000_000L || abs(to) > 1_000_000_000_000_000_000L)
                    throw Exception()
            }catch (e: Exception) {
                Snackbar.make(requireView(), requireContext().getString(R.string.valid_number3), Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (from > to) {
                Snackbar.make(requireView(), requireContext().getString(R.string.valid_number4), Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (to-from+1 > 1_000_000L) {
                Snackbar.make(requireView(), requireContext().getString(R.string.valid_number5), Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val list = List((to-from+1).toInt()){
                from+it
            }.shuffled(Random)
            binding.numberList.visibility = RecyclerView.VISIBLE
            binding.result.visibility = TextView.GONE
            adapter.data = list
            binding.numberList.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in))
        }
        return binding.root
    }
}