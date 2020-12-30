package com.carloshns.bin2dec

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.*

class MainViewModel: ViewModel() {

    val binaryString = MutableLiveData<String>()
    val binaryNumber = MutableLiveData<String>()
    val decimalNumber = MutableLiveData<Int>()
    val isValidNumber = MutableLiveData<Boolean>()

    fun setBinaryNumber(){
        val number = binaryString.value
        if(!number.isNullOrBlank()){
            if(isValidNumber(number)) {
                binaryNumber.postValue(number)
                decimalNumber.postValue(binaryToDecimal(number))
                isValidNumber.postValue(true)
                binaryString.postValue("")
            } else {
                isValidNumber.postValue(false)
            }
        }
    }

    private fun binaryToDecimal(number: String): Int {
        var sum: Int = 0
        for (i in 0..number.length-1){
            sum += ((number[i].toString().toInt() as Int) * 2.0.pow(number.length-1 - i)).toInt()
        }
        return sum
    }

    private fun isValidNumber(number: String): Boolean {
        return number?.let {
            hasEightDigits(it) && hasOnlyZeroAndOneDigits(it)
        }
    }

    private fun hasEightDigits(number:String): Boolean {
        return number.length == 8
    }

    private fun hasOnlyZeroAndOneDigits(number: String): Boolean {
        var result = true
        for (number in number.toList()){
            Log.d("Numero", number.toString())
            if(number != '0' && number != '1') result = false
        }
        return result
    }
}