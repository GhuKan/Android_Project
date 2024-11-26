package com.example.projekt_1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenB(navigateToC: () -> Unit,
            navigateToA: () -> Unit,
            lightLevel: Float){
    val backgroundColor by remember(lightLevel) {
        mutableStateOf(
            if (lightLevel < 100f) {
                Color.Black
            } else {
                Color.White


            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Luksomierz") })
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(top = it.calculateTopPadding()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Natężenie światła:")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "$lightLevel [lux]")
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = navigateToC) {
                Text(text = "Akcelerometr")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = navigateToA) {
                Text(text = "Żyroskop")
            }
        }
    }
}
