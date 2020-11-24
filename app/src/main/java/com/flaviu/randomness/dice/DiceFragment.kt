package com.flaviu.randomness.dice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.flaviu.randomness.adapters.PhotoTypeAdapter
import com.flaviu.randomness.R
import com.flaviu.randomness.databinding.FragmentDiceBinding
import com.google.android.material.snackbar.Snackbar

class DiceFragment : Fragment() {
    private lateinit var binding: FragmentDiceBinding
    private lateinit var diceViewModel: DiceViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentDiceBinding.inflate(inflater)
        diceViewModel = ViewModelProvider(this).get(DiceViewModel::class.java)
        binding.viewModel = diceViewModel
        binding.lifecycleOwner = this
        val adapter = PhotoTypeAdapter()
        binding.diceList.adapter = adapter
        diceViewModel.rollPressed.observe(viewLifecycleOwner) {
            if (it) {
                val editTest = binding.editTextNumber.text.toString()
                if (editTest.isEmpty()) {
                    Snackbar.make(requireView(), requireContext().getString(R.string.valid_number), Snackbar.LENGTH_SHORT).show()
                    diceViewModel.onRollPressedFinished()
                    return@observe
                }
                val numberDice: Int
                try{
                    numberDice = editTest.toInt()
                }catch (e: NumberFormatException) {
                    Snackbar.make(requireView(), requireContext().getString(R.string.valid_number2), Snackbar.LENGTH_SHORT).show()
                    diceViewModel.onRollPressedFinished()
                    return@observe
                }
                if (numberDice == 0 || numberDice > 1000000) {
                    Snackbar.make(requireView(), requireContext().getString(R.string.valid_number2), Snackbar.LENGTH_SHORT).show()
                    diceViewModel.onRollPressedFinished()
                    return@observe
                }
                if (numberDice >= 3)
                    binding.diceList.layoutManager = GridLayoutManager(activity, 3)
                else binding.diceList.layoutManager = GridLayoutManager(activity, numberDice)
                adapter.data = diceViewModel.getRandomList(editTest.toInt())
                binding.diceList.scheduleLayoutAnimation()
                diceViewModel.onRollPressedFinished()
            }
        }
        return binding.root
    }
}