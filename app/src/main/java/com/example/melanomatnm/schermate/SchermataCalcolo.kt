package com.example.melanomatnm.schermate

import androidx.compose.material.icons.Icons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.melanomatnm.R
import com.example.melanomatnm.componenti.Bottone
import com.example.melanomatnm.componenti.InputTesto
import com.example.melanomatnm.componenti.Spazio
import com.example.melanomatnm.ui.theme.MelanomaTNMTheme

@Composable
fun SchermataCalcolo(tornaIndietro: () -> Unit){
    val statoSchermata = rememberScrollState()

    Surface(modifier = Modifier.fillMaxSize()){}
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ){
        IconButton(
            onClick = tornaIndietro
        ){
           Icon(
               imageVector = Icons.AutoMirrored.Filled.ArrowBack,
               contentDescription = "Indietro",
               tint = MaterialTheme.colorScheme.onPrimary
           )
        }
    }
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(statoSchermata),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spazio(modifier =Modifier.height(100.dp))
        InputTesto(
            value = "",
            onValueChange = {},
            label = stringResource(R.string.label_breslow),
            modifier = Modifier.width(200.dp)
        )
        Spazio()
        InputTesto(
            value = "",
            onValueChange = {},
            label = stringResource(R.string.label_ulcerazione),
            modifier = Modifier.width(200.dp)
        )
        Spazio()
        InputTesto(
            value = "",
            onValueChange = {},
            label = stringResource(R.string.label_linfonodi),
            modifier = Modifier.width(200.dp)
        )
        Spazio()
        InputTesto(
            value = "",
            onValueChange = {},
            label = stringResource(R.string.label_metastasi),
            modifier = Modifier.width(200.dp)
        )
        Spazio(modifier = Modifier.height(100.dp))
        Bottone(
            text = stringResource(R.string.btn_calcolaStadio),
            onClick = {},

        )
    }


}

//funzione per visualizzare la preview della schermata
@Preview(showBackground = true)
@Composable
fun SchermataCalcoloPreview() {

    MelanomaTNMTheme() {
        SchermataCalcolo({})
    }
}