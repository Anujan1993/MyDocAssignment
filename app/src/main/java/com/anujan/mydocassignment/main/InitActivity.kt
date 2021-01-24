package com.anujan.mydocassignment.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anujan.mydocassignment.MyApplication
import com.anujan.mydocassignment.login.LoginActivity
import com.anujan.mydocassignment.repository.FirebaseViewModel
import javax.inject.Inject

class InitActivity : AppCompatActivity() {

    @Inject
    lateinit var firebaseViewModel: FirebaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as MyApplication).appComponent.registrationComponent().create().inject(this)

        val username = firebaseViewModel.username
        if (username.isNullOrEmpty()){
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        else{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}