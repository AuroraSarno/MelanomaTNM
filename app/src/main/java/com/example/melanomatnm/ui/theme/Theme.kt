package com.example.melanomatnm.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
private val DarkColorScheme = darkColorScheme(
    primary = beigeBottoniNotte,
    onPrimary = bianco,
    secondary = marroneChiaroNotte,
    onSecondary = bianco,
    background = sfondoNotte,
    onBackground = bianco,
    surface = beigeCardNotte,
    onSurface = bianco,
    error =rossoErroreNotte
)
private val LightColorScheme = lightColorScheme(
    primary = beigeBottoniGiorno,
    onPrimary = marroneGiorno,
    secondary = marroneChiaroGiorno,
    onSecondary = bianco,
    background = sfondoGiorno,
    onBackground = marroneGiorno,
    surface = cremaCardGiorno,
    onSurface = marroneGiorno,
    error = rossoErroreGiorno
)

@Composable
fun MelanomaTNMTheme(
    //uso la funzione isSystemDarkTheme, che in base a modalità di sistema restituisce true se notte, false altrimenti,
    //per definire lo schema colori da utilizzare nella view
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
   val colorScheme = if(darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}