package mx.delasalle.apppaises.ui

import CountryListScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import mx.delasalle.apppaises.ui.screens.CountryNewScreen

@Composable
fun NavigationApp(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "first_screen"){
        composable ("first_screen"){
            CountryListScreen(navController)
        }
        composable ("second_screen") {
            CountryNewScreen( navController)
        }

    }
}