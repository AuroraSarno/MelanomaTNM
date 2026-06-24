package com.example.melanomatnm.viewmodel

import com.example.melanomatnm.modello.CategoriaM
import com.example.melanomatnm.modello.CategoriaN
import com.example.melanomatnm.modello.CategoriaT
import com.example.melanomatnm.modello.StadioFinale

data class StatoView(
    val breslow: String = "",
    val ulcerazioni: Boolean? = null,
    val metastasi: Boolean? = null,
    val linfonodi: String = "",
    //risultati
    val categoriaT: CategoriaT? = null,
    val categoriaM: CategoriaM? = null,
    val categoriaN: CategoriaN? = null,
    val stadioFinale: StadioFinale? = null,
    val followUp: Int? = null,
    //gestione errori, salviamo gli id delle string in modo che la traduzione possa avvenire nella view
    val erroreBreslow: Int? = null,
    val erroreLinfonodi: Int? = null,
    val erroreMetastasi: Int? = null,
    val erroreUlcerazioni: Int? = null
)
