package com.example.projekt_1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenB(navigateToC: () -> Unit, navigateToA: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "ScreenB") })
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(top = it.calculateTopPadding()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            Button(onClick = navigateToC)
            {
                Text(text = "Go to C")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = navigateToA)
            {
                Text(text = "Go to A")
            }
        }
    }
}