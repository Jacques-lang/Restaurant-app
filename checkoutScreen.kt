package com.example.restaurantapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.restaurantapp.R
import com.example.restaurantapp.data.UiState
import com.example.restaurantapp.ui.commonUiComponents.OrderSummaryButton
import com.example.restaurantapp.ui.commonUiComponents.Summary
import java.text.NumberFormat
import java.util.Locale

val dollarFormat: NumberFormat = NumberFormat.getCurrencyInstance(Locale.US)

@Composable
fun Checkout(
    subtotal:()->Double,
    total:()->Double,
    uiState: UiState,
    onCancelClicked:()->Unit={},
    onSubmitClicked:()->Unit={},

    ) {
    val listOfMeals = listOf(
        Pair(uiState.entreeChoice, uiState.entreePrices),
        Pair(uiState.sideChoice, uiState.sidePrices),
        Pair(uiState.accompChoice, uiState.accompPrices)
    )

    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.order_summary),
                fontWeight = FontWeight.Bold
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    listOfMeals.forEach {

                        Text(
                            text = it.first,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
                Column {
                    listOfMeals.forEach { meals ->
                        val formattedPrice = dollarFormat.format(meals.second)
                        Text(
                            text = formattedPrice,
                            modifier = Modifier.padding(top = 8.dp, end = 5.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(thickness = 1.dp)
            Column {
                Summary(subtotal = subtotal(), total = total())
            }
            OrderSummaryButton(onCancelClicked ={onCancelClicked()},onSubmitClicked={onSubmitClicked()})


        }
    }
}
