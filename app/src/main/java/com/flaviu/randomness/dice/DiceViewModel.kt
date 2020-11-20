package com.flaviu.randomness.dice

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class DiceViewModel : ViewModel() {
    val rollPressed = MutableLiveData<Boolean>()

    fun onRollPressed() {
        rollPressed.value = true
    }

    fun onRollPressedFinished() {
        rollPressed.value = false
    }

    fun getRandomList(n: Int): List<DiceNumber> {
        return List(n){
            when(Random.nextInt(1, 7)) {
                1 -> DiceNumber.DICE1
                2 -> DiceNumber.DICE2
                3 -> DiceNumber.DICE3
                4 -> DiceNumber.DICE4
                5 -> DiceNumber.DICE5
                else -> DiceNumber.DICE6
            }
        }
    }
}