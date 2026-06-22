package com.example.melanomatnm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.melanomatnm.schermate.NavigazioneApp
import com.example.melanomatnm.ui.theme.MelanomaTNMTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val temaDelSistema = isSystemInDarkTheme()
            var isDarkMode by remember { mutableStateOf(temaDelSistema) }
            //tutta l'app deve essere dentro il tema, altrimenti non verranno mostrati i colori giusti
            MelanomaTNMTheme (isDarkMode){
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigazioneApp(
                        isDarkMode, onThemeToggle = {isDarkMode = !isDarkMode}
                    )
                }
            }
        }
    }
}