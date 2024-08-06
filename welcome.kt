package com.example.restaurantapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.restaurantapp.R

@Preview
@Composable
fun Welcome(
    onClick:()->Unit={},
){
    Column( modifier=Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween) {

        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,) {
            Image(
                painter = painterResource(R.drawable.restauranthome),
                contentDescription = null,
                modifier=Modifier
                    .size(700.dp),
                contentScale = ContentScale.FillHeight
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = { onClick() },
                modifier = Modifier
                    .width(350.dp)
            ) {
                Text(text = stringResource(R.string.start_order))
            }
        }
    }
}
