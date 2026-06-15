package com.example.melanomatnm.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable


//schema colori modalità notte
private val DarkColorScheme = darkColorScheme(
    primary = beigeBottoniNotte, //bottoni
    onPrimary = bianco,  //scritte bottoni
    secondary = rosaNotte,
    onSecondary = bianco,
    background = sfondoNotte, //sfondo
    onBackground = bianco, //scritte libere
    surface = beigeCardNotte, //sfondo card
    onSurface = bianco //scritte su card
)

//schema colori modalità giorno
private val LightColorScheme = lightColorScheme(
    primary = beigeBottoniGiorno, //bottoni
    onPrimary = marroneGiorno, //scritte bottoni
    secondary = rosaGiorno,
    onSecondary = bianco,
    background = sfondoGiorno, //sfondo giorno
    onBackground = marroneGiorno, //scritte libere
    surface = cremaCardGiorno, //sfondo card
    onSurface = marroneGiorno //scritte card
)

@Composable
fun MelanomaTNMTheme(
    //funzione che in base a modalità di sistema restituisce true se notte, false altrimenti
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    //in base alla modalità attuale giorno/notte definisco lo schema colori usando quello notte se notte, giorno altrimenti
   val colorScheme = if(darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}