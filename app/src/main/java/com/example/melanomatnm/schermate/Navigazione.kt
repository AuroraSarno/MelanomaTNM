package com.example.melanomatnm.schermate

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.melanomatnm.viewmodel.MelanomaViewModel

//definisco una funzione composable
@Composable
fun NavigazioneApp(isDarkMode: Boolean, onThemeToggle: ()->Unit){
    //tiene traccia di dove mi trovo nelle schermate, dei percorsi possibili
    // da una schermata all'altra e permette il funzionamento del tasto "indietro"
    val navController=rememberNavController();

    //il contenitore (lo stack) delle schermate, gestito da navController
    NavHost(
        navController=navController,
        //la schermata di partenza è sempre home
        startDestination = "Home"
    ){
        //definisco i percorsi possibili
        composable("Home"){
            //in questo modo dico che quando la schermata è "Home"
            // devo disegnare la funzione SchermataHome
            SchermataHome(
                //Seguendo MVVM la schermata non deve conoscere alcun dettaglio delle regole
                //di navigazione, le dico direttamente cosa fare senza passare il controller
                isDarkMode=isDarkMode,
                onThemeToggle = onThemeToggle,
                NavigazioneACalcolatore = {navController.navigate("Calcolatore")}
            )
        }

        composable("Calcolatore"){
            //essendo una schermata finale viene disegnata e non c'è possibilità di movimento
            val viewModel: MelanomaViewModel= viewModel()
            SchermataCalcolo(
                //permette di tornare alla schermata precedente
                tornaIndietro = {navController.popBackStack()},
                viewModel = viewModel
            )
        }

    }
}