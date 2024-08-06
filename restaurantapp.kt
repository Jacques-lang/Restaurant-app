package com.example.restaurantapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.restaurantapp.data.Entree
import com.example.restaurantapp.ui.commonUiComponents.FinalDialog
import com.example.restaurantapp.ui.commonUiComponents.Titles
import com.example.restaurantapp.ui.commonUiComponents.TopOfApp

@Preview
@Composable
fun RestaurantApp(
    viewModel:ViewModel = viewModel(),
    navController: NavHostController= rememberNavController()
){
    var showDialog by remember { mutableStateOf(false) }
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentTitle = Titles.valueOf(backStackEntry?.destination?.route ?: Titles.Welcome.name)
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
                TopOfApp(title =currentTitle,
                    canNavigateBack =navController.previousBackStackEntry!=null,
                    navigateBack = {navController.navigateUp()})
            }
    ) {innerPadding->
        NavHost(navController =navController,
            startDestination =Titles.Welcome.name,
            modifier = Modifier.padding(innerPadding)) {
            composable(route = Titles.Welcome.name){
                Welcome(onClick = {navController.navigate(Titles.ChooseEntree.name)})
            }
            composable(route = Titles.ChooseEntree.name){
                SelectMenu(options = Entree.entree,
                    onNextButtonClick = {navController.navigate(Titles.ChooseSide.name)},
                    onSelectionChangedString = {viewModel.setEntreeChoice(it)},
                    onSelectionChangedPrice = {viewModel.setEntreePrice(it)},
                    onCancelButtonClick = {onCancel(viewModel,navController)})
            }
            composable(route=Titles.ChooseSide.name){
                SelectMenu(options = Entree.side,
                    onNextButtonClick = {navController.navigate(Titles.ChooseAccomp.name)},
                    onSelectionChangedString = {viewModel.setSideDish(it)},
                    onSelectionChangedPrice = {viewModel.setSidePrice(it)},
                    onCancelButtonClick = {onCancel(viewModel,navController)})
            }
           composable(route=Titles.ChooseAccomp.name){
                SelectMenu(options = Entree.accomp,
                    onNextButtonClick = {navController.navigate(Titles.OrderCheckout.name)},
                    onSelectionChangedString = {viewModel.setAccomp(it)},
                    onSelectionChangedPrice = {viewModel.setAccompPrice(it)},
                    onCancelButtonClick = { onCancel(viewModel,navController) })
            }
            composable(route=Titles.OrderCheckout.name){
                Checkout(subtotal ={viewModel.getSubTotal()},
                        total ={viewModel.getTotalWithTaxes()},
                        uiState =uiState,
                        onCancelClicked = { onCancel(viewModel,navController)},
                        onSubmitClicked = { if(uiState.isOrderOver){showDialog=true} }
                )
                if(showDialog){
                    FinalDialog(onPlaceOrder = {onCancel(viewModel,navController)},
                      )

                }
            }
        }
    }
}
fun onCancel(
    viewModel: ViewModel,
    navController: NavHostController
){
    viewModel.resetOrder()
    navController.popBackStack(Titles.Welcome.name, inclusive = false)
}
