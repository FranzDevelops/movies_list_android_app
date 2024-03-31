package com.example.movieslistapp.features.authentication

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {
    //private val firebaseAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    // Function to perform login with email and password
    fun loginWithEmailAndPassword(email: String, password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { onSuccess.invoke() }
            .addOnFailureListener { e -> onFailure.invoke(e.message ?: "Unknown error occurred") }
    }

    // Function to perform sign up with email and password
    fun signUpWithEmailAndPassword(email: String, password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { onSuccess.invoke() }
            .addOnFailureListener { e -> onFailure.invoke(e.message ?: "Unknown error occurred") }
    }
}