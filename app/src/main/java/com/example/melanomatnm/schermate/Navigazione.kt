package com.example.melanomatnm.schermate

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.melanomatnm.viewmodel.MelanomaViewModel
@Composable
fun NavigazioneApp(isDarkMode: Boolean, onThemeToggle: ()->Unit){
    //inizializzazione del controller per la gestione del backstack
    val navController=rememberNavController();

    NavHost(
        navController=navController,
        startDestination = "Home"
    ){
        composable("Home"){
             SchermataHome(
                //Seguendo il pattern MVVM la schermata non deve conoscere alcun dettaglio delle regole
                //di navigazione, passo direttamente una lamba (e non il controller) così che possa delegare le sue intenzioni
                isDarkMode=isDarkMode,
                onThemeToggle = onThemeToggle,
                NavigazioneACalcolatore = {navController.navigate("Calcolatore")}
            )
        }

        composable("Calcolatore"){
            //istanzio il viewModel così che dipenda dalla vita del calcolatore (chiusa la schermata si chiude il viewModel)
            val viewModel: MelanomaViewModel= viewModel()
            SchermataCalcolo(
                tornaIndietro = {navController.popBackStack()},
                viewModel = viewModel
            )
        }

    }
}