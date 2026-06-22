package com.example.melanomatnm.viewmodel

import com.example.melanomatnm.modello.CategoriaM
import com.example.melanomatnm.modello.CategoriaN
import com.example.melanomatnm.modello.CategoriaT
import com.example.melanomatnm.modello.StadioFinale

data class StatoView(
    //input utente
    val breslow: String = "", //inputTesto parte da vuoto
    val ulcerazioni: Boolean? = null, //costringe l'utente a scegliere uno dei 2 valori
    val metastasi: Boolean? = null,
    val linfonodi: String = "",
    //risultati
    val categoriaT: CategoriaT? = null, //all'inizio non ci saranno calcoli, i risultati sono null
    val categoriaM: CategoriaM? = null,
    val categoriaN: CategoriaN? = null,
    val stadioFinale: StadioFinale? = null,
    //gestione errori, salviamo gli id delle string in modo che la traduzione possa avvenire nella view
    val erroreBreslow: Int? = null,
    val erroreLinfonodi: Int? = null,
    val erroreMetastasi: Int? = null,
    val erroreUlcerazioni: Int? = null
)
