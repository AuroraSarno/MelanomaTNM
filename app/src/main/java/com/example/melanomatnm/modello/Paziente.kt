package com.example.melanomatnm.modello

data class Paziente(
    val breslow: Double,
    val ulcerazioni: Boolean,
    val linfonodi: Int,
    val metastasi: Boolean,
    val categoriaT: CategoriaT,
    val categoriaN: CategoriaN,
    val categoriaM: CategoriaM,
    val stadioFinale: StadioFinale
)
