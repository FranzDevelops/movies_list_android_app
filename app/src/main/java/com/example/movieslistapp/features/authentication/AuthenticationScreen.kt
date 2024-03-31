package com.example.movieslistapp.features.authentication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieslistapp.navigation.AppScreens
import kotlinx.coroutines.launch

@Composable
fun AuthenticationScreen(navController: NavController, viewModel: AuthenticationViewModel = viewModel()) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope() // Moved this line here

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("Username") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = { /* Handle next action */ })
        )
        TextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() })
        )

        Button(
            onClick = {
                viewModel.loginWithEmailAndPassword(
                    email.value,
                    password.value,
                    onSuccess = {
                        navController.navigate(AppScreens.PopularMoviesScreen.route)
                    },
                    onFailure = {
                        scope.launch {
                            snackbarHostState.showSnackbar("Login failed. Try with Sign In.")
                        }
                    }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("Login")
        }

        TextButton(
            onClick = {
                viewModel.signUpWithEmailAndPassword(
                    email.value,
                    password.value,
                    onSuccess = {
                        navController.navigate(AppScreens.PopularMoviesScreen.route)
                    },
                    onFailure = {
                        scope.launch {
                            snackbarHostState.showSnackbar("Sign Up failed. Please try again.")
                        }
                    }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("Sign Up")
        }
    }
    SnackbarHost(hostState = snackbarHostState)
}