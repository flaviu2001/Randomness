package com.flaviu.randomness.coin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.flaviu.randomness.PhotoType
import kotlin.random.Random

class CoinViewModel : ViewModel() {
    val tossPressed = MutableLiveData<Boolean>()

    fun onTossPressed() {
        tossPressed.value = true
    }

    fun onTossPressedFinished() {
        tossPressed.value = false
    }

    fun getRandomList(n: Int): List<PhotoType> {
        return List(n){
            when(Random.nextInt(1, 3)) {
                1 -> PhotoType.HEADS
                else -> PhotoType.TAILS
            }
        }
    }
}