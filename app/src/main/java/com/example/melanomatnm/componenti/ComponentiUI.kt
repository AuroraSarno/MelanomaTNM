package com.example.melanomatnm.componenti

import androidx.annotation.experimental.Experimental
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
import androidx.compose.ui.draw.blur
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
fun Titoli(text: String, modifier: Modifier = Modifier){
    Text(
        text = text,
        style = MaterialTheme.typography.headlineMedium,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.padding(bottom = 24.dp)
    )
}

@Composable
fun InputTesto(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier
){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {Text(
            label,
            style= MaterialTheme.typography.titleMedium,
            color= MaterialTheme.colorScheme.onPrimary)
                },
        modifier =modifier.fillMaxWidth(),
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
    opzione1: String,
    opzione2: String,
    modifier: Modifier = Modifier
){
    var expanded by remember {mutableStateOf(false)}

    Box(modifier = modifier.width(200.dp)){
        OutlinedTextField(
            value = value,
            onValueChange = {},
            readOnly = true, //non fa aprire la tastiera
            label= {Text(label, style= MaterialTheme.typography.titleMedium,
                color= MaterialTheme.colorScheme.onPrimary)},
            modifier = Modifier.width(200.dp),
            shape =RoundedCornerShape(12.dp)
        )

        //sovrappongo un box vuoto per evitare che si apra la tastiera, quando provo
        // a cliccare l'input testo clicco il box e apro il menu
        Box(
            modifier = Modifier.matchParentSize().clickable{expanded = true}
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
                onClick = {}
            )
            DropdownMenuItem(
                text = {Text("Inlgese")},
                onClick = {}
            )
        }
    }
}

