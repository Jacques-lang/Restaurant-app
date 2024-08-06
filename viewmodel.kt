package com.example.restaurantapp.ui

import androidx.lifecycle.ViewModel
import com.example.restaurantapp.data.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

const val TAXES= 0.84

class ViewModel:ViewModel() {
    companion object{
        const val MAX_SIZE=3
    }
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()
    private val sumOfPrices: MutableList<Double> = mutableListOf()

    fun getTotalWithTaxes(): Double {
        val withTaxes = sumOfPrices.sum() + TAXES
        _uiState.update {
            it.copy(totalPrice = withTaxes) }
        return withTaxes
    }
    fun getSubTotal(): Double {
        val updatedSum = sumOfPrices.sum()
        _uiState.update {
            it.copy(
                subtotal = updatedSum
            )
        }
        return updatedSum
    }
    fun setEntreeChoice(mealChoice: String) {
        _uiState.update {
            it.copy(
                entreeChoice = mealChoice,
            )
        }
    }
    fun setEntreePrice(price: Double) {
        if(sumOfPrices.size>= MAX_SIZE){
           sumOfPrices[0]=price
        }
        else{sumOfPrices.add(price)}
        _uiState.update {
            it.copy(
                entreePrices = price
            )
        }
    }
    fun setSideDish(mealChoice: String) {
        _uiState.update {
            it.copy(
                sideChoice = mealChoice,
            )
        }
    }
    fun setSidePrice(price: Double) {
        if(sumOfPrices.size>= MAX_SIZE){
          sumOfPrices[1]=price
        }
        else{
            sumOfPrices.add(price)
        }
        _uiState.update {
            it.copy(
                sidePrices = price,
            )
        }
    }
    fun setAccomp(mealChoice: String) {
        _uiState.update {
            it.copy(
                accompChoice = mealChoice,
            )
        }
    }
    fun setAccompPrice(price: Double) {
        if(sumOfPrices.size>= MAX_SIZE){
           sumOfPrices[2]=price
        }
        else{
            sumOfPrices.add(price)
        }

        _uiState.update {
            it.copy(
                accompPrices = price,
            )
        }
    }
    fun resetOrder() {
        _uiState.value = UiState(subtotal = 0.0, totalPrice = 0.0)
        sumOfPrices.clear()
    }
}
