package com.example.projekt_1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.math.abs
import androidx.compose.runtime.*



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenC(
    navigateToA: () -> Unit,
    navigateToB: () -> Unit,
    xAccel: Float,
    yAccel: Float,
    zAccel: Float){
    val backgroundColor by remember(zAccel) {
        val normalizedZ = abs(zAccel).coerceIn(0f, 10f) / 10f
        mutableStateOf(
            Color(
                red = normalizedZ,
                green = normalizedZ,
                blue = normalizedZ
            ) // Gray scale color
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Akcelerometr") })
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
            Text(text = "Przyspieszenie w osi:")
            Text("X: %.2f [m/s^2]".format(xAccel))
            Text("Y: %.2f [m/s^2]".format(yAccel))
            Text("Z: %.2f [m/s^2]".format(zAccel))
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = navigateToA) { Text("Żyroskop") }
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = navigateToB) { Text("Luksomierz") }
        }
    }
}
