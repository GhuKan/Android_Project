package com.example.projekt_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projekt_1.ui.theme.Projekt_1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Projekt_1Theme {

                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "screen_a") {
                    composable(
                        route = "screen_a"
                    ) {
                        ScreenA(
                            navigateToB = {navController.navigate("screen_b")}, navigateToC = {navController.navigate("screen_c")})

                    }
                    composable(
                        route = "screen_b"
                    ) {
                        ScreenB(
                            navigateToC = {navController.navigate("screen_c")}, navigateToA = {navController.navigate("screen_a")})
                    }
                    composable(
                        route = "screen_c"
                    ) {
                        ScreenC(
                            navigateToA = {navController.navigate("screen_a")}, navigateToB = {navController.navigate("screen_b")})
                    }
                }
            }
        }
    }
}



