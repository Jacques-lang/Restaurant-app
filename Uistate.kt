package com.example.restaurantapp.data

data class UiState(
    val entreePrices:Double=0.0,
    val sidePrices:Double=0.0,
    val accompPrices:Double=0.0,
    val entreeChoice:String="",
    val sideChoice:String="",
    val accompChoice:String="",
    val totalPrice:Double=0.0,
    val subtotal:Double=0.0,
    val isOrderOver:Boolean=true


    )
