package com.example.projekt_1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import androidx.compose.runtime.*
import androidx.compose.material3.*
import com.google.firebase.auth.FirebaseAuth




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenA(navigateToB: () -> Unit, navigateToC: () -> Unit) {
    val auth = FirebaseAuth.getInstance()
    val database = FirebaseDatabase.getInstance("https://test-d74a5-default-rtdb.europe-west1.firebasedatabase.app/").reference
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isAuthenticated by remember { mutableStateOf(false) }
    var loginError by remember { mutableStateOf("") }
    var data by remember { mutableStateOf("Loading...") }
    var inputText by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    // Logowanie użytkownika
    fun loginUser() {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                isAuthenticated = true
                loginError = ""
            }
            .addOnFailureListener {
                loginError = "Login failed: ${it.message}"
            }
    }

    // Pobieranie danych z Firebase
    LaunchedEffect(isAuthenticated) {
        if (isAuthenticated) {
            database.child("example_data").addValueEventListener(object : com.google.firebase.database.ValueEventListener {
                override fun onDataChange(snapshot: com.google.firebase.database.DataSnapshot) {
                    data = snapshot.getValue(String::class.java) ?: "No data found"
                }

                override fun onCancelled(error: com.google.firebase.database.DatabaseError) {
                    data = "Error: ${error.message}"
                }
            })
        }
    }

    // Scaffold - szkielet ekranu
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "ScreenA") }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (!isAuthenticated) {
                // Ekran logowania
                Text(text = "Log in to access data", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    visualTransformation = androidx.compose.ui.text.input.PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { loginUser() }) {
                    Text(text = "Log in")
                }
                Spacer(modifier = Modifier.height(8.dp))
                if (loginError.isNotEmpty()) {
                    Text(text = loginError, color = MaterialTheme.colorScheme.error)
                }
            } else {
                // Główny ekran aplikacji
                Text(text = "Firebase Data: $data")
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = inputText,
                    onValueChange = { inputText = it },
                    label = { Text("Enter data to send") }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    database.child("example_data").setValue(inputText)
                        .addOnSuccessListener {
                            message = "Data successfully sent!"
                        }
                        .addOnFailureListener {
                            message = "Failed to send data: ${it.message}"
                        }
                }) {
                    Text(text = "Send to Firebase")
                }
                Spacer(modifier = Modifier.height(8.dp))
                if (message.isNotEmpty()) {
                    Text(text = message, color = MaterialTheme.colorScheme.primary)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = navigateToB) {
                    Text(text = "Go to Screen B")
                }
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = navigateToC) {
                    Text(text = "Go to Screen C")
                }
            }
        }
    }
}