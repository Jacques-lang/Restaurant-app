package com.example.restaurantapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.restaurantapp.ui.commonUiComponents.Buttons
import java.text.NumberFormat
import java.util.Locale

@Preview
@Composable
fun SelectMenu(
    onCancelButtonClick:()->Unit={},
    onNextButtonClick:()->Unit={},
    onSelectionChangedString:(String)->Unit={},
    onSelectionChangedPrice:(Double)->Unit={},
    options:List<Triple<String,String,Double>> = emptyList()
){
    var selectedMeal by rememberSaveable {mutableStateOf("") }
    var selectedPrice by rememberSaveable { mutableDoubleStateOf(0.0) }

    Column(modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween) {
        Column(modifier = Modifier
            .padding(16.dp)){
            options.forEach { meal ->
                val dollarFormat=NumberFormat.getCurrencyInstance(Locale.US)
                val format=dollarFormat.format(meal.third)
                Row(
                    modifier = Modifier.selectable(
                        selected = selectedMeal == meal.first,
                        onClick = {
                            selectedMeal = meal.first
                            onSelectionChangedString(meal.first)
                            selectedPrice = meal.third
                            onSelectionChangedPrice(meal.third)
                        }
                    ), verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(selected = selectedMeal == meal.first,
                        onClick = {
                            selectedMeal = meal.first
                            onSelectionChangedString(meal.first)
                            selectedPrice = meal.third
                            onSelectionChangedPrice(meal.third)
                        })
                    Column {
                        Text(text=meal.first,
                            style = MaterialTheme.typography.headlineSmall)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = meal.second, fontWeight = FontWeight.Normal)
                        Text(text = format, fontWeight = FontWeight.Normal)
                        HorizontalDivider(
                            thickness = 1.dp,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }
                }
            }
               Buttons(onCancel = { onCancelButtonClick()}, onNext = { onNextButtonClick()},
                   selectedMeal =selectedMeal)
        }
    }
}
