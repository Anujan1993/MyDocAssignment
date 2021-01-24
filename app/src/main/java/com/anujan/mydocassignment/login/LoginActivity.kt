package com.anujan.mydocassignment.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.anujan.mydocassignment.R
import com.anujan.mydocassignment.MyApplication
import com.anujan.mydocassignment.main.MainActivity
import com.anujan.mydocassignment.repository.FirebaseViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.emailID
import androidx.lifecycle.Observer
import com.anujan.mydocassignment.registration.RegisterActivity
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var firebaseViewModel: FirebaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        (application as MyApplication).appComponent.loginComponent().create().inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        login.setOnClickListener {
            firebaseViewModel.loginUserFromAuthWithEmailAndPassword(
                    emailID.text.toString(),
                    passwordLogin.text.toString()
            )
        }

        unregister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        firebaseViewModel?.currentUserLog?.observe(this, Observer {
            user -> user?.let {
                if (user != "Success"){
                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                            Intent.FLAG_ACTIVITY_CLEAR_TASK or
                            Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this,user,Toast.LENGTH_LONG)
                }
            }
        })
        firebaseViewModel?.toast?.observe(this, Observer { message ->
            message?.let {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                firebaseViewModel?.onToastShown()
            }
        })

    }
}
