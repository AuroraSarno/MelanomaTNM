package com.example.melanomatnm

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import com.example.melanomatnm.schermate.NavigazioneApp
import com.example.melanomatnm.ui.theme.MelanomaTNMTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.saveable.rememberSaveable


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val temaDelSistema = isSystemInDarkTheme()
            var isDarkMode by rememberSaveable { mutableStateOf(temaDelSistema) }
            MelanomaTNMTheme (isDarkMode){
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigazioneApp(
                        isDarkMode,
                        //passiamo onThemeToggle dalla main activity così che valga per tutte le schermate
                        onThemeToggle = {isDarkMode = !isDarkMode}
                    )
                }
            }
        }
    }
}