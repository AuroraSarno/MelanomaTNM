package com.example.melanomatnm.componenti

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.melanomatnm.ui.theme.MelanomaTNMTheme


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