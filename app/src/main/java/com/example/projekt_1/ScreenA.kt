package com.example.projekt_1

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenA(
    navigateToB: () -> Unit,
    navigateToC: () -> Unit,
    xRotation: Float,
    yRotation: Float,
    zRotation: Float
) {
    Scaffold(
        topBar = {
            androidx.compose.material3.TopAppBar(title = { Text(text = "Żyroskop") })
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Aktualne położenie w osi:")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "X: $xRotation [rad/s]")
            Text(text = "Y: $yRotation [rad/s]")
            Text(text = "Z: $zRotation [rad/s]")
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = navigateToB) {
                Text(text = "Luksomierz")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = navigateToC) {
                Text(text = "Akcelerometr")
            }
        }
    }
}
