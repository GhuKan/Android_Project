package com.example.projekt_1

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projekt_1.ui.theme.Projekt_1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager


        val gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        val lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        val accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        setContent {
            Projekt_1Theme {
                val navController = rememberNavController()


                var xRotation by remember { mutableStateOf(0f) }
                var yRotation by remember { mutableStateOf(0f) }
                var zRotation by remember { mutableStateOf(0f) }
                var lightLevel by remember { mutableStateOf(0f) }
                var xAccel by remember { mutableStateOf(0f) }
                var yAccel by remember { mutableStateOf(0f) }
                var zAccel by remember { mutableStateOf(0f) }

                val sensorEventListener = object : SensorEventListener {
                    override fun onSensorChanged(event: SensorEvent?) {
                        event?.let {
                            when (it.sensor.type) {
                                Sensor.TYPE_GYROSCOPE -> {
                                    xRotation = it.values[0]
                                    yRotation = it.values[1]
                                    zRotation = it.values[2]
                                }
                                Sensor.TYPE_LIGHT -> {
                                    lightLevel = it.values[0]
                                }
                                Sensor.TYPE_ACCELEROMETER -> {
                                    xAccel = it.values[0]
                                    yAccel = it.values[1]
                                    zAccel = it.values[2]
                                }
                            }
                        }
                    }

                    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
                }

                DisposableEffect(Unit) {
                    gyroscopeSensor?.let {
                        sensorManager.registerListener(sensorEventListener, it, SensorManager.SENSOR_DELAY_NORMAL)
                    }
                    lightSensor?.let {
                        sensorManager.registerListener(sensorEventListener, it, SensorManager.SENSOR_DELAY_NORMAL)
                    }
                    accelerometerSensor?.let {
                        sensorManager.registerListener(sensorEventListener, it, SensorManager.SENSOR_DELAY_UI)
                    }

                    onDispose {
                        sensorManager.unregisterListener(sensorEventListener)
                    }
                }

                NavHost(navController = navController, startDestination = "screen_a") {
                    composable(route = "screen_a") {
                        ScreenA(
                            navigateToB = { navController.navigate("screen_b") },
                            navigateToC = { navController.navigate("screen_c") },
                            xRotation = xRotation,
                            yRotation = yRotation,
                            zRotation = zRotation
                        )
                    }
                    composable(route = "screen_b") {
                        ScreenB(
                            navigateToC = { navController.navigate("screen_c") },
                            navigateToA = { navController.navigate("screen_a") },
                            lightLevel = lightLevel
                        )
                    }
                    composable(route = "screen_c") {
                        ScreenC(
                            navigateToA = { navController.navigate("screen_a") },
                            navigateToB = { navController.navigate("screen_b") },
                            xAccel = xAccel,
                            yAccel = yAccel,
                            zAccel = zAccel
                        )
                    }
                }
            }
        }
    }
}
