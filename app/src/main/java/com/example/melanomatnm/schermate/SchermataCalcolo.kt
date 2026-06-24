package com.example.melanomatnm.schermate

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.melanomatnm.R
import com.example.melanomatnm.componenti.Bottone
import com.example.melanomatnm.componenti.InputTesto
import com.example.melanomatnm.componenti.MenuSiNo
import com.example.melanomatnm.componenti.Spazio
import com.example.melanomatnm.viewmodel.MelanomaViewModel
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import com.example.melanomatnm.componenti.TestoCard
import com.example.melanomatnm.componenti.TestoTitoliCard

@Composable
fun SchermataCalcolo(tornaIndietro: () -> Unit, viewModel: MelanomaViewModel) {
    val statoSchermata = rememberScrollState()
    val statoUI by viewModel.stato.collectAsState()
    //variabile per togliere il focus dalla tastiera
    val focusManager = LocalFocusManager.current
    val testoSi = stringResource(R.string.si)
    val testoNo = stringResource(R.string.no)
    val testoSeleziona = stringResource(R.string.seleziona)
    fun booleanAString(valore: Boolean?):String{
        return when(valore) {
            true -> testoSi
            false -> testoNo
            else -> testoSeleziona
        }
    }
    fun stringABoolean(valore: String): Boolean{
        return valore == testoSi
    }
    //variabili che chiedono ad android l'orientamento attuale del telefono, per gestire la view in base all'orientamento
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    //do colore allo sfondo
    Surface(modifier = Modifier.fillMaxSize()) {}

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
           modifier = Modifier.fillMaxSize().statusBarsPadding().verticalScroll(statoSchermata)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = { focusManager.clearFocus() })
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(
                    onClick = { tornaIndietro() }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Indietro",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
            if (isPortrait) {
                Spazio(modifier = Modifier.height(70.dp))
            } else {
                Spazio(modifier = Modifier.height(24.dp))
            }
            InputTesto(
                value = statoUI.breslow,
                onValueChange = { viewModel.aggiornaBreslow(it) },
                label = stringResource(R.string.label_breslow),
                testoErrore = if (statoUI.erroreBreslow != null) stringResource(statoUI.erroreBreslow!!) else null,
                onFocusPerso = { viewModel.aggiornaErrBreslow() },
                modifier = Modifier.width(200.dp)
            )
            Spazio()
            MenuSiNo(
                value = booleanAString(statoUI.ulcerazioni),
                onValueChange = { viewModel.aggiornaUlcerazioni(stringABoolean(it)) },
                label = stringResource(R.string.label_ulcerazione),
                opzione1 = testoSi,
                opzione2 = testoNo,
                testoErrore = if (statoUI.erroreUlcerazioni != null) stringResource(statoUI.erroreUlcerazioni!!) else null,
                onFocusPerso = { viewModel.aggiornaErrUlcerazioni() }
            )
            Spazio()
            InputTesto(
                value = statoUI.linfonodi,
                onValueChange = { viewModel.aggiornaLinfonodi(it) },
                label = stringResource(R.string.label_linfonodi),
                testoErrore = if (statoUI.erroreLinfonodi != null) stringResource(statoUI.erroreLinfonodi!!) else null,
                onFocusPerso = { viewModel.aggiornaErrLinfonodi() },
                modifier = Modifier.width(200.dp)
            )
            Spazio()
            MenuSiNo(
                value = booleanAString(statoUI.metastasi),
                onValueChange = { viewModel.aggiornaMetastatsi(stringABoolean(it)) },
                label = stringResource(R.string.label_metastasi),
                opzione1 = stringResource(R.string.si),
                opzione2 = stringResource(R.string.no),
                testoErrore = if (statoUI.erroreMetastasi != null) stringResource(statoUI.erroreMetastasi!!) else null,
                onFocusPerso = { viewModel.aggiornaErrMetastasi() }
            )
            if (isPortrait) {
                Spazio(modifier = Modifier.height(70.dp))
            } else {
                Spazio()
            }

            Bottone(
                text = stringResource(R.string.btn_calcolaStadio),
                onClick = {viewModel.calcolaStadioFinale()},
            )
        }
        //faccio apparire la card dei risultati solo se lo stadiofinale è stato aggiornato (significa che è andato tutto bene)
        if(statoUI.stadioFinale != null){
            Box(modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .clickable(interactionSource = remember{ MutableInteractionSource()}, indication = null){}
            ){
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                        .align(Alignment.Center),
                    elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp).verticalScroll(rememberScrollState()), //verticalScroll permette di scorrere se lo spazio non basta a tenere tutti i componenti
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Row(
                            modifier=Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ){
                            IconButton(onClick = {viewModel.chiudiRisultati()}) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Chiudi la finestra"
                                )
                            }
                        }
                        TestoTitoliCard(" ${stringResource(R.string.categoriaT)} ${statoUI.categoriaT}")
                        Spazio(modifier = Modifier.height(24.dp))
                        HorizontalDivider()
                        Spazio(modifier = Modifier.height(24.dp))
                        TestoTitoliCard("${stringResource(R.string.categoriaN)} ${statoUI.categoriaN}")
                        Spazio(modifier = Modifier.height(24.dp))
                        HorizontalDivider()
                        Spazio(modifier = Modifier.height(24.dp))
                        TestoTitoliCard("${stringResource(R.string.categoriaM)} ${statoUI.categoriaM}")
                        Spazio(modifier = Modifier.height(24.dp))
                        HorizontalDivider()
                        Spazio(modifier = Modifier.height(24.dp))
                        Text(text="${stringResource(R.string.stadioFinale)} ${statoUI.stadioFinale}",style=MaterialTheme.typography.titleLarge)
                        Spazio(modifier = Modifier.height(24.dp))
                        HorizontalDivider()
                        Spazio(modifier = Modifier.height(24.dp))
                        TestoCard("FollowUp: ${stringResource(statoUI.followUp!!)}")
                    }
                }
            }
        }
    }
}
