package com.example.melanomatnm.schermate

import com.example.melanomatnm.viewmodel.MelanomaViewModel
import com.example.melanomatnm.R
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.melanomatnm.componenti.Bottone
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.melanomatnm.componenti.MenuATendinaLingua
import com.example.melanomatnm.ui.theme.MelanomaTNMTheme


@Composable
fun SchermataHome(
    isDarkMode: Boolean,
    onThemeToggle: () -> Unit,
    NavigazioneACalcolatore: () -> Unit
){
    //la funzione funge da memoria interna, permette di mantenere la schermata nel punto in cui era prima della rotazione
    val StatoSchermata = rememberScrollState()

    Surface(modifier= Modifier.fillMaxSize()) {}
    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start //lo attacco a sinistra
        ){
            Box(){
                Surface(
                    //grandezza della forma
                    modifier=Modifier.width(350.dp).height(100.dp),
                    //arrotondo per bellezza i 2 angoli a destra (end)
                    shape =RoundedCornerShape (topEnd=16.dp, bottomEnd = 16.dp),
                    color= MaterialTheme.colorScheme.secondary
                ){}
                Text(
                    text=stringResource(R.string.benvenuto),
                    style= MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier.padding(30.dp,30.dp)
                )

            }

        }
        Column(
            //align permette di tenere al centro questa colonna
            modifier = Modifier.align(Alignment.Center).verticalScroll(StatoSchermata),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            //bottone centrale
            Bottone(text=stringResource(R.string.btn_calcolaHome), onClick = NavigazioneACalcolatore, modifier=Modifier.width(270.dp))

        }

        Row(
            //align attacca la riga alla fine dello schermo, in larghezza riempie tutto lo spazio e padding lo stacca dai bordi di 24 dp
            modifier= Modifier.align(Alignment.BottomCenter).fillMaxWidth().padding(24.dp),
            horizontalArrangement = Arrangement.SpaceBetween //in questo modo le 2 icone sono sperata da uno spazio centrale e rimangono ai 2 angoli
        ){
           MenuATendinaLingua()

            IconButton(
                onClick = onThemeToggle, // Inverte la modalità al click, nella mainActiviy (così cambia ovunque)
                modifier = Modifier.background(MaterialTheme.colorScheme.primary, CircleShape)
            ) {
               Icon(
                   imageVector = if(isDarkMode) Icons.Filled.DarkMode else Icons.Filled.LightMode,
                   contentDescription = "Modalità giorno/notte",
                   tint = MaterialTheme.colorScheme.onPrimary
               )
            }
            }
        }
    }


/*funzione per visualizzare la preview della schermata
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    // Chiamiamo la tua schermata passandole un'azione vuota {}
    // visto che nel preview i bottoni non devono fare nulla di reale
    MelanomaTNMTheme() {
        SchermataHome(NavigazioneACalcolatore = {})
    }
}*/