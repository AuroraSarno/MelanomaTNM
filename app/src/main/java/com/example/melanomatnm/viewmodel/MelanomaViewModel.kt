package com.example.melanomatnm.viewmodel

import androidx.compose.runtime.Composable
import com.example.melanomatnm.R
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import com.example.melanomatnm.calcolatore.calcolaCategoriaM
import com.example.melanomatnm.calcolatore.calcolaCategoriaN
import com.example.melanomatnm.calcolatore.calcolaCategoriaT
import com.example.melanomatnm.calcolatore.calcolaStadio
import com.example.melanomatnm.modello.StadioFinale
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
class MelanomaViewModel: ViewModel() {
    //variabile che viewmodel usa per salvare input nella view
    private val statoView = MutableStateFlow(StatoView())
    //stato permette alla view di leggere i dati che dovrà usare per aggiornarsi costantemente
    val stato = statoView.asStateFlow()

    //funzioni per la gestione di eventi interni alla UI
    fun aggiornaUlcerazioni(nuovoValore:Boolean?){
       //aggiorna lo stato per mostrarlo sulla view, resetta anche l'errore per evitare di mostrarlo durante la scrittura dell'utente
        statoView.update {it.copy(ulcerazioni = nuovoValore, erroreUlcerazioni = null) }
    }
    fun aggiornaBreslow(nuovoValore: String){ statoView.update {it.copy(breslow = nuovoValore)} }
    fun aggiornaLinfonodi(nuovoValore: String){ statoView.update {it.copy(linfonodi = nuovoValore)} }
    fun aggiornaMetastatsi(nuovoValore: Boolean?){ statoView.update {it.copy(metastasi = nuovoValore, erroreMetastasi = null) } }

    //funzioni di aggiornamento per gli errori (validazione dell'input)
    fun aggiornaErrBreslow() {
        val statoAttualeView = statoView.value
        val testoBreslow = statoAttualeView.breslow
        val idErrore = if(testoBreslow.isEmpty()){
            null
        }else{
            val breslow = testoBreslow.toDoubleOrNull()
            if(breslow==null || breslow <0.0){
                R.string.erroreBreslow
            }else{
                null
            }
        }
        statoView.update { it.copy(erroreBreslow = idErrore) }
    }
    fun aggiornaErrLinfonodi() {
        val statoAttualeView = statoView.value
        val testoLinfonodi = statoAttualeView.linfonodi
        val idErrore = if(testoLinfonodi.isEmpty()){
            null
        }else{
            val linfonodi = testoLinfonodi.toIntOrNull()
            if(linfonodi==null||linfonodi<0){
                R.string.erroreLinfonodi
            }else null
        }
        statoView.update { it.copy(erroreLinfonodi = idErrore) }
    }
    fun aggiornaErrMetastasi(){
        val idErrore = if (statoView.value.metastasi==null){
            R.string.erroreOpzioni
        }else null
        statoView.update { it.copy(erroreMetastasi = idErrore) }
    }
    fun aggiornaErrUlcerazioni(){
        val idErrore = if (statoView.value.ulcerazioni==null){
            R.string.erroreOpzioni
        }else null
        statoView.update { it.copy(erroreUlcerazioni = idErrore) }
    }

    //logica del calcolo
    fun calcolaStadioFinale(){
        //leggo lo stato attuale, a questo punto è stato aggiornato in base a cosa ha inserito l'utente
        //useremo statoAttualeView per leggere i dati del momento in cui utente ha cliccato sul bottone
        val statoAttualeView = statoView.value

        //convertiamo i dati letti in modo da poterli usare nelle nostre funzioni
        val breslow= statoAttualeView.breslow.toDoubleOrNull()
        val linfonodi= statoAttualeView.linfonodi.toIntOrNull()
        val ulcerazione= statoAttualeView.ulcerazioni
        val metastasi = statoAttualeView.metastasi
        //controlliamo eventuali errori e lo salviamo
        val errBreslow = if(breslow==null || breslow <0.0) R.string.erroreBreslow else null
        val errLinfonodi = if(linfonodi==null || linfonodi <0) R.string.erroreLinfonodi else null
        val errUlcerazioni = if(ulcerazione==null) R.string.erroreOpzioni else null
        val errMetastasi = if(metastasi==null) R.string.erroreOpzioni else null
        //aggiornamento dell'UI così vengono mostrati eventuali errori trovati
        statoView.update { it.copy(erroreBreslow = errBreslow, erroreLinfonodi = errLinfonodi, erroreUlcerazioni = errUlcerazioni, erroreMetastasi = errMetastasi) }
        //eseguiamo i controlli per sapere se possiamo andare al calcolo
        if(breslow!=null && breslow >=0.0 && linfonodi!=null && linfonodi>= 0 && metastasi!=null && ulcerazione!=null){
            val T = calcolaCategoriaT(breslow, ulcerazione )
            val N = calcolaCategoriaN(linfonodi)
            val M = calcolaCategoriaM(metastasi)
            val stadio = calcolaStadio(T,N,M)
            val followUp = calcolaFollowUp(stadio)
            statoView.update { it.copy(categoriaT = T, categoriaN = N, categoriaM = M, stadioFinale = stadio, followUp = followUp) }
        }
    }
    fun calcolaFollowUp(stadio: StadioFinale): Int?{
        return when (stadio) {
            StadioFinale.O, StadioFinale.IA -> R.string.followUp_0
            StadioFinale.IB, StadioFinale.IIA -> R.string.followUp_IB_IIA
            StadioFinale.IIB, StadioFinale.IIC, StadioFinale.III -> R.string.followUp_IIB_IIC_III
            StadioFinale.IV -> R.string.followUp_IV
        }
    }
    fun chiudiRisultati(){
        statoView.update { it.copy(categoriaM = null, categoriaN = null, categoriaT = null, stadioFinale=null) }
    }
}