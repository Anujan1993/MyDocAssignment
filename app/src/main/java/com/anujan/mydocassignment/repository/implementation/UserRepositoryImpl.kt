package com.anujan.mydocassignment.repository.implementation

import android.util.Log
import com.anujan.mydocassignment.extension.await
import com.anujan.mydocassignment.keyName
import com.anujan.mydocassignment.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.anujan.mydocassignment.repository.UserRepository
import com.anujan.mydocassignment.storage.Storage
import java.lang.Exception
import com.anujan.mydocassignment.util.Result
import javax.inject.Inject


private val TAG = "UserRepositoryImpl"
class UserRepositoryImpl @Inject constructor(
        private val storage: Storage) : UserRepository
{
    //CONST
    private val USER_COLLECTION_NAME = "users"
    private val firestoreInstance = FirebaseFirestore.getInstance()
    private val userCollection = firestoreInstance.collection(USER_COLLECTION_NAME)
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override suspend fun registerUserFromAuthWithEmailAndPassword(
        email: String, password: String): Result<FirebaseUser?>
    {
        try
        {
            return when(val resultDocumentSnapshot = firebaseAuth.createUserWithEmailAndPassword(email, password).await())
            {
                is Result.Success -> {
                    Log.i(TAG, "Result.Success")
                    val firebaseUser = resultDocumentSnapshot.data.user
                    Result.Success(firebaseUser)
                }
                is Result.Error -> {
                    Log.e(TAG, "${resultDocumentSnapshot.exception}")
                    Result.Error(resultDocumentSnapshot.exception)
                }
                is Result.Canceled ->  {
                    Log.e(TAG, "${resultDocumentSnapshot.exception}")
                    Result.Canceled(resultDocumentSnapshot.exception)
                }
                else -> {
                    Result.Loading
                }
            }
        }
        catch (exception: Exception)
        {
            return Result.Error(exception)
        }
    }

    override suspend fun createUserInFirestore(user: User): Result<Void?>
    {
        return try
        {
            userCollection.document(user.id).set(user).await()
        }
        catch (exception: Exception)
        {
            Result.Error(exception)
        }
    }

    override suspend fun getUserInFirestore(userID: String): String?
    {
        var message = String()
        val docRef = userCollection.document(userID)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                   // val user:User = document.data?.get("name")
                    message = "Success"
                    storage.setString(keyName.REGISTERED_USER,document.data?.get("name").toString())
                } else {
                    Log.d(TAG, "No such document")
                    message = "No User"
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
                message = exception.message.toString()
            }
        return message
    }

    override suspend fun loginUserFromAuthWithEmailAndPassword(
            email: String, password: String): Result<FirebaseUser?>
    {
        try
        {
            return when(val resultDocumentSnapshot = firebaseAuth.signInWithEmailAndPassword(email, password).await())
            {
                is Result.Success -> {
                    Log.i(TAG, "Result.Success")
                    val firebaseUser = resultDocumentSnapshot.data.user
                    Result.Success(firebaseUser)
                }
                is Result.Error -> {
                    Log.e(TAG, "${resultDocumentSnapshot.exception}")
                    Result.Error(resultDocumentSnapshot.exception)
                }
                is Result.Canceled ->  {
                    Log.e(TAG, "${resultDocumentSnapshot.exception}")
                    Result.Canceled(resultDocumentSnapshot.exception)
                }
                else -> {
                    Result.Loading
                }
            }
        }
        catch (exception: Exception)
        {
            return Result.Error(exception)
        }
    }
    override fun logOut():Boolean{
        storage.setString(keyName.REGISTERED_USER,"")
        firebaseAuth.signOut()
        return true
    }
}