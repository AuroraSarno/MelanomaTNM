package com.example.melanomatnm.calcolatore

import com.example.melanomatnm.modello.CategoriaM
import com.example.melanomatnm.modello.CategoriaN
import com.example.melanomatnm.modello.CategoriaT
import com.example.melanomatnm.modello.StadioFinale

//funzione per calcolare la categoria di T
fun calcolaCategoriaT (
    breslow: Double,
    ulcerazioni: Boolean
): CategoriaT {
    //costrutto when per tabella di decisione, si ferma alla prima condizione vera, se non c'è nessuna condizione vera restituisce l'else
    return when{
        breslow == 0.0 -> CategoriaT.Tis
        breslow <0.8 && !ulcerazioni -> CategoriaT.T1a
        breslow <=1.0 -> CategoriaT.T1b
        breslow <= 2.0 && !ulcerazioni -> CategoriaT.T2a
        breslow <= 2.0 && ulcerazioni -> CategoriaT.T2b
        breslow <= 4.0 && !ulcerazioni -> CategoriaT.T3a
        breslow <= 4.0 && ulcerazioni -> CategoriaT.T3b
        breslow > 4.0 && !ulcerazioni -> CategoriaT.T4a
        breslow > 4.0 && ulcerazioni -> CategoriaT.T4b
        else -> throw IllegalArgumentException("Valore Breslow non valido")
    }
}

//funzione per calcolare la categoria di N
fun calcolaCategoriaN(
    linfonodi: Int
): CategoriaN{
    return when{
        linfonodi==0 -> CategoriaN.N0
        linfonodi==1 -> CategoriaN.N1
        linfonodi==2 || linfonodi==3 -> CategoriaN.N2
        else -> CategoriaN.N3
    }
}

//funzione per calcolare la categoria di M
fun calcolaCategoriaM(
    metastasi: Boolean
): CategoriaM{
    return if(!metastasi) CategoriaM.M0 else CategoriaM.M1
}

fun calcolaStadio(
    T : CategoriaT,
    N : CategoriaN,
    M : CategoriaM
): StadioFinale{
    return when {
        M == CategoriaM.M1 -> StadioFinale.IV
        N != CategoriaN.N0 -> StadioFinale.III
        T == CategoriaT.T4b -> StadioFinale.IIC
        T == CategoriaT.T4a || T == CategoriaT.T3b -> StadioFinale.IIB
        T == CategoriaT.T3a || T == CategoriaT.T2b -> StadioFinale.IIA
        T == CategoriaT.T2a || T == CategoriaT.T1b -> StadioFinale.IB
        T == CategoriaT.T1a -> StadioFinale.IA
        T == CategoriaT.Tis -> StadioFinale.O
        else -> throw IllegalArgumentException("Combinazione TNM non valida per calcolo stadi")
    }
}

