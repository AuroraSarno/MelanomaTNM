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
            horizontalAlignment = Alignment.Start
        ){
            Box(){
                Surface(
                    modifier=Modifier.width(350.dp).height(100.dp),
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
            modifier = Modifier.align(Alignment.Center).verticalScroll(StatoSchermata),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Bottone(text=stringResource(R.string.btn_calcolaHome), onClick = NavigazioneACalcolatore, modifier=Modifier.width(270.dp))
        }

        Row(
            modifier= Modifier.align(Alignment.BottomCenter).fillMaxWidth().padding(24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
           MenuATendinaLingua()

            IconButton(
                onClick = onThemeToggle,
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