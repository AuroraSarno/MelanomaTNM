package com.example.melanomatnm.componenti

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


//vado a creare le componenti generiche da usare nella UI

//bottone
@Composable
fun Bottone(text: String, onClick: ()-> Unit, modifier: Modifier =Modifier){
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor= MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = modifier.height(70.dp)
    ){
        Text(text = text, style = MaterialTheme.typography.titleLarge)
    }
}

@Composable
fun TestoCard(text: String, modifier: Modifier = Modifier){
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onBackground,
        //fontWeight = FontWeight.Bold
    )
}

@Composable
fun InputTesto(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    testoErrore: String?,
    onFocusPerso: () -> Unit,
    modifier: Modifier = Modifier
){
    var giaFocalizzato by remember{mutableStateOf(false)}
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {Text(
            label,
            style= MaterialTheme.typography.titleMedium,
            color= MaterialTheme.colorScheme.onPrimary)
                },
        isError= testoErrore!=null,
        supportingText = {if(testoErrore!=null){Text(text=testoErrore, color=MaterialTheme.colorScheme.error)}},
        modifier =modifier
            .fillMaxWidth()
            .onFocusChanged{
                if(it.isFocused){
                    giaFocalizzato = true
                }else if(!it.isFocused) {
                    onFocusPerso()
                    giaFocalizzato=false
                }},
        shape =RoundedCornerShape(12.dp)
    )
}

@Composable
fun Spazio(
    modifier: Modifier = Modifier
){
    Spacer(modifier.height(50.dp))
}

@Composable
fun MenuSiNo(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    testoErrore: String?,
    opzione1: String,
    opzione2: String,
    onFocusPerso: () -> Unit,
    modifier: Modifier = Modifier
){
    //per evitare che l'errore venga mostrato prima ancora che iniziamo a digitare qualcosa
    var giaFocalizzato by remember { mutableStateOf(false) }
    var expanded by remember {mutableStateOf(false)}
    val focusManager = LocalFocusManager.current

    Box(modifier = modifier.width(200.dp)){
        OutlinedTextField(
            value = value,
            onValueChange = {},
            readOnly = true, //non fa aprire la tastiera
            label= {Text(label, style= MaterialTheme.typography.titleMedium,
                color= MaterialTheme.colorScheme.onPrimary)},
            isError= testoErrore != null,
            supportingText = {if(testoErrore != null){Text(text=testoErrore, color=MaterialTheme.colorScheme.error)} },
            modifier = Modifier
                .width(200.dp)
                .onFocusChanged{
                    if(it.isFocused){
                        giaFocalizzato=true
                    }else if(!it.isFocused && giaFocalizzato)
                               onFocusPerso()
                               giaFocalizzato=false //resetto per la prossima volta
                               },
            shape =RoundedCornerShape(12.dp)
        )

        //sovrappongo un box vuoto per evitare che si apra la tastiera, quando provo
        // a cliccare l'input testo clicco il box e apro il menu
        Box(
            modifier = Modifier.matchParentSize().clickable{
                focusManager.clearFocus() //chiudo la tastiera se aperta
                expanded = true}
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {expanded = false},
            modifier=Modifier.background(MaterialTheme.colorScheme.primary)

        ) {
            DropdownMenuItem(
                text = { Text(opzione1) },
                onClick = {
                    onValueChange(opzione1)
                    expanded = false
                }
            )
            HorizontalDivider()
            DropdownMenuItem(
                text = { Text(opzione2) },
                onClick = {
                    onValueChange(opzione2)
                    expanded = false
                }
            )
        }


    }
}

@Composable
fun MenuATendinaLingua(){
    var expanded by remember { mutableStateOf(false) }
    Box(
    ){
        IconButton(
            onClick = {expanded = !expanded},
            modifier = Modifier.background(MaterialTheme.colorScheme.primary, CircleShape)
        ){
            Icon(
                imageVector = Icons.Filled.Language,
                contentDescription = "Cambia lingua",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {expanded = false},
            modifier = Modifier.background(MaterialTheme.colorScheme.primary)
        ){
            DropdownMenuItem(
                text = {Text("Italiano")},
                onClick = {
                    AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("it"))
                    expanded = false
                }
            )
            HorizontalDivider()
            DropdownMenuItem(
                text = {Text("English")},
                onClick = {
                    AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("en"))
                    expanded = false
                }
            )
        }
    }
}

