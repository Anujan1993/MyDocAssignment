package com.anujan.mydocassignment.repository

import android.util.Log
import androidx.lifecycle.*
import com.anujan.mydocassignment.keyName
import com.anujan.mydocassignment.model.User
import com.google.firebase.auth.*
import kotlinx.coroutines.*
import com.anujan.mydocassignment.repository.implementation.UserRepositoryImpl
import com.anujan.mydocassignment.storage.Storage
import com.anujan.mydocassignment.util.Result
import javax.inject.Inject


private val TAG = "FirebaseViewModel"
class FirebaseViewModel @Inject constructor(
private val storage: Storage,
): ViewModel()
{
    private val _toast = MutableLiveData<String?>()
    val toast: LiveData<String?>
        get() = _toast

    private val _spinner = MutableLiveData<Boolean>(false)
    val spinner: LiveData<Boolean>
        get() = _spinner

    private val _currentUserMLD = MutableLiveData<User>(User())
    val currentUserLD: LiveData<User>
        get() = _currentUserMLD

    private val _currentUserLog = MutableLiveData<String?>()
    val currentUserLog: LiveData<String?>
        get() = _currentUserLog

    val userRepository: UserRepository = UserRepositoryImpl(storage)

    val username: String
        get() = storage.getString(keyName.REGISTERED_USER)
    //Email
    fun registerUserFromAuthWithEmailAndPassword(name: String, email: String, password: String)
    {
        launchDataLoad {
            viewModelScope.launch {
                when(val result = userRepository.registerUserFromAuthWithEmailAndPassword(email, password))
                {
                    is Result.Success<*> -> {
                        Log.e(TAG, "Result.Success")
                        result.data?.let {firebaseUser ->
                            createUserInFirestore(createUserObject(firebaseUser as FirebaseUser, name),name)
                        }!!
                    }
                    is Result.Error -> {
                        Log.e(TAG, "${result.exception.message}")
                        _toast.value = result.exception.message
                    }
                    is Result.Canceled -> {
                        Log.e(TAG, "${result.exception!!.message}")
                        _toast.value = "Request canceled"
                    }
                }
            }
        }
    }
    private suspend fun createUserInFirestore(user: User,name: String)
    {
        Log.d(TAG, "Result - ${user.name}")
        when(val result = userRepository.createUserInFirestore(user))
        {
            is Result.Success -> {
                _toast.value = "Registraion successful!"
                storage.setString(keyName.REGISTERED_USER,name)
                Log.d(TAG, "Result.Error - ${user.name}")
                _currentUserMLD.value = user
            }
            is Result.Error -> {
                _toast.value = result.exception.message
            }
            is Result.Canceled -> {
                _toast.value = "Request canceled"
            }
        }
    }


    fun loginUserFromAuthWithEmailAndPassword( email: String, password: String)
    {
        launchDataLoad {
            viewModelScope.launch {
                when(val result = userRepository.loginUserFromAuthWithEmailAndPassword(email, password))
                {
                    is Result.Success<*> -> {
                        Log.e(TAG, "Result.Success")
                        result.data?.let {firebaseUser ->
                            getUser(firebaseUser as FirebaseUser)
                        }!!
                    }
                    is Result.Error -> {
                        Log.e(TAG, "${result.exception.message}")
                        _toast.value = result.exception.message
                    }
                    is Result.Canceled -> {
                        Log.e(TAG, "${result.exception!!.message}")
                        _toast.value = "Request canceled"
                    }
                }
            }
        }
    }
    suspend fun getUser(firebaseUserID:FirebaseUser){
        val userID = firebaseUserID.uid

        launchDataLoad {
            viewModelScope.launch {
                when(val result = userRepository.getUserInFirestore(userID))
                {
                    "Success" -> {
                        _currentUserLog.value = result
                        _toast.value = result
                    }
                    else-> {
                        _currentUserLog.value = result
                        _toast.value = result
                    }
                }
            }
        }

    }

    fun createUserObject(firebaseUser: FirebaseUser, name: String): User
    {
        val currentUser = User(
            id =  firebaseUser.uid,
            name = name
        )

        return currentUser
    }
    fun onToastShown()
    {
        _toast.value = null
    }
    private fun launchDataLoad(block: suspend () -> Unit): Job
    {
        return viewModelScope.launch {
            try
            {
                _spinner.value = true
                block()
            }
            catch (error: Throwable)
            {
                _toast.value = error.message
            }
            finally
            {
                _spinner.value = false
            }
        }
    }
}