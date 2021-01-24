package com.anujan.mydocassignment.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.anujan.mydocassignment.MyApplication
import com.anujan.mydocassignment.R
import com.anujan.mydocassignment.main.MainActivity
import com.anujan.mydocassignment.repository.FirebaseViewModel
import kotlinx.android.synthetic.main.activity_registration.*
import javax.inject.Inject

private val TAG = "RegisterActivity"
class RegisterActivity : AppCompatActivity()
{
    @Inject
    lateinit var firebaseViewModel: FirebaseViewModel

    override fun onCreate(savedInstanceState: Bundle?)
    {
        (application as MyApplication).appComponent.registrationComponent().create().inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        supportActionBar?.hide()

        register.setOnClickListener {
            if(validateName() && validateEmail() && validatePassword())
            {
                firebaseViewModel?.registerUserFromAuthWithEmailAndPassword(
                    username.text.toString(),
                    emailID.text.toString(),
                    passwordIn.text.toString()
                )
            }
        }

        firebaseViewModel?.toast?.observe(this, Observer { message ->
            message?.let {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                firebaseViewModel?.onToastShown()
            }
        })
        firebaseViewModel?.currentUserLD?.observe(this, Observer {
            user -> user?.let {
                if (user.name != ""){
                    Log.d("SDSSDDSDSDS "+user.name +"  JKKNSSMK","SDS")
                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                            Intent.FLAG_ACTIVITY_CLEAR_TASK or
                            Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                }
            }
        })
        firebaseViewModel?.spinner?.observe(this, Observer { value ->
            value.let {show ->
                spinner_register.visibility = if (show) View.VISIBLE else View.GONE
                Log.i(TAG, "$show")
            }
        })
    }


    private fun validateName(): Boolean
    {
        val name = username.text.toString().trim()

        return if(name.length < 6)
        {
            username.error = "Use at least 5 characters"
            false
        }
        else
        {
            true
        }
    }
    private fun validateEmail(): Boolean
    {
        val email = emailID.text.toString().trim()

        return if(!email.contains("@") && !email.contains("."))
        {
            emailID.error = "Enter a valid email"
            false
        }
        else if (email.length < 6)
        {
            emailID.error = "Use at least 5 characters"
            false
        }
        else
        {
            true
        }
    }
    private fun validatePassword(): Boolean
    {
        val password = passwordIn.text.toString().trim()

        return if(password.length < 6)
        {
            passwordIn.error = "Use at least 5 characters"
            false
        }
        else
        {
            true
        }
    }
}
