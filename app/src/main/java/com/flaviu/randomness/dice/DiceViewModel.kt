package com.flaviu.randomness.dice

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.flaviu.randomness.PhotoType
import kotlin.random.Random

class DiceViewModel : ViewModel() {
    val rollPressed = MutableLiveData<Boolean>()

    fun onRollPressed() {
        rollPressed.value = true
    }

    fun onRollPressedFinished() {
        rollPressed.value = false
    }

    fun getRandomList(n: Int): List<PhotoType> {
        return List(n){
            when(Random.nextInt(1, 7)) {
                1 -> PhotoType.DICE1
                2 -> PhotoType.DICE2
                3 -> PhotoType.DICE3
                4 -> PhotoType.DICE4
                5 -> PhotoType.DICE5
                else -> PhotoType.DICE6
            }
        }
    }
}