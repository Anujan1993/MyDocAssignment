package com.anujan.mydocassignment.repository

import com.anujan.mydocassignment.model.User
import com.anujan.mydocassignment.util.Result
import com.google.firebase.auth.FirebaseUser

interface UserRepository{
    suspend fun registerUserFromAuthWithEmailAndPassword(email: String, password: String): Result<FirebaseUser?>
    suspend fun createUserInFirestore(user: User): Result<Void?>
    suspend fun getUserInFirestore(userID: String): String?
    suspend fun loginUserFromAuthWithEmailAndPassword(email: String, password: String): Result<FirebaseUser?>
    fun logOut():Boolean

}