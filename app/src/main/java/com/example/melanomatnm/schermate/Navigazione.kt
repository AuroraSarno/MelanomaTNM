package com.example.melanomatnm.schermate

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

//definisco una funzione composable
@Composable
fun NavigazioneApp(){
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
                NavigazioneACalcolatore = {navController.navigate("Calcolatore")}
            )
        }

        composable("Calcolatore"){
            //essendo una schermata finale viene disegnata e non c'è possibilità di movimento
            SchermataCalcolo(
                //permette di tornare alla schermata precedente
                tornaIndietro = {navController.popBackStack()}
            )
        }

    }
}