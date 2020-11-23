package com.flaviu.randomness.coin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.flaviu.randomness.PhotoTypeAdapter
import com.flaviu.randomness.databinding.FragmentCoinBinding
import com.google.android.material.snackbar.Snackbar

class CoinFragment : Fragment() {
    private lateinit var binding: FragmentCoinBinding
    private lateinit var coinViewModel: CoinViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentCoinBinding.inflate(inflater)
        coinViewModel = ViewModelProvider(this).get(CoinViewModel::class.java)
        binding.viewModel = coinViewModel
        binding.lifecycleOwner = this
        val adapter = PhotoTypeAdapter()
        binding.coinsList.adapter = adapter
        coinViewModel.tossPressed.observe(viewLifecycleOwner) {
            if (it) {
                val editTest = binding.editTextNumber.text.toString()
                if (editTest.isEmpty()) {
                    Snackbar.make(requireView(), "Please enter a valid number", Snackbar.LENGTH_SHORT).show()
                    coinViewModel.onTossPressedFinished()
                    return@observe
                }
                val numberCoins: Int
                try{
                    numberCoins = editTest.toInt()
                }catch (e: NumberFormatException) {
                    Snackbar.make(requireView(), "Please enter a smaller number", Snackbar.LENGTH_SHORT).show()
                    coinViewModel.onTossPressedFinished()
                    return@observe
                }
                if (numberCoins == 0 || numberCoins > 1000000) {
                    Snackbar.make(requireView(), "Please enter a number between 1 and 1000000", Snackbar.LENGTH_SHORT).show()
                    coinViewModel.onTossPressedFinished()
                    return@observe
                }
                if (numberCoins >= 3)
                    binding.coinsList.layoutManager = GridLayoutManager(activity, 3)
                else binding.coinsList.layoutManager = GridLayoutManager(activity, numberCoins)
                adapter.data = coinViewModel.getRandomList(editTest.toInt())
                binding.coinsList.scheduleLayoutAnimation()
                coinViewModel.onTossPressedFinished()
            }
        }
        return binding.root
    }
}