package com.anujan.mydocassignment.main

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.anujan.mydocassignment.MyApplication
import com.anujan.mydocassignment.R
import com.anujan.mydocassignment.adapter.BestSellersAdapter
import com.anujan.mydocassignment.login.LoginActivity
import com.anujan.mydocassignment.room.entity.BestSellerList
import com.anujan.mydocassignment.util.Status
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    // @Inject annotated fields will be provided by Dagger

    @Inject
    lateinit var mainViewModel: MainViewModel
    private lateinit var adapter : BestSellersAdapter
    var bestSellers: ArrayList<BestSellerList> = ArrayList()
    private lateinit var progress: ProgressBar

    /**
     * If the User is not registered, RegistrationActivity will be launched,
     * If the User is not logged in, LoginActivity will be launched,
     * else carry on with MainActivity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Grabs instance of UserManager from the application graph
        val userManager = (application as MyApplication).appComponent.userManager()

        setContentView(R.layout.activity_main)
        userManager.userComponent!!.inject(this)
        progress = findViewById(R.id.progressBar)
       // setupViews()
        findViewById<TextView>(R.id.hello).text = mainViewModel.welcomeText
        bookRv.layoutManager = LinearLayoutManager(this)
        adapter = BestSellersAdapter(this, bestSellers)


        val cm = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo

        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting) {
            setupObservers()
        }
        else{
            dialogBox(
                    "Your internet is off so this data is not updated, This data when you came last time with internet loaded data",
                    false
            )
            roomDataObservers()
        }
    }
    private fun setupObservers() {
        this.let {
            mainViewModel.getBooks().observe(it, Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            resource.data?.let { it1 ->
                                val returnSuccess: String = mainViewModel.storeInRoom(it1)
                                if (returnSuccess == "Success") {
                                    roomDataObservers()
                                }
                            }
                        }
                        Status.ERROR -> {
                            bookRv.visibility = View.VISIBLE
                            progress.visibility = View.GONE
                            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        }
                        Status.LOADING -> {
                            progress.visibility = View.VISIBLE
                            bookRv.visibility = View.GONE
                        }
                    }
                }
            })
        }
    }
    private fun roomDataObservers() {
        this.let {
            mainViewModel.getBestSellers().observe(it, Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            bookRv.visibility = View.VISIBLE
                            progress.visibility = View.GONE
                            resource.data?.let { it1 ->
                                resource.data.let { bestSeller -> retrieveList(bestSeller) }
                            }
                        }
                        Status.ERROR -> {
                            bookRv.visibility = View.VISIBLE
                            progress.visibility = View.GONE
                            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        }
                        Status.LOADING -> {
                            progress.visibility = View.VISIBLE
                            bookRv.visibility = View.GONE
                        }
                    }
                }
            })
        }
    }
    fun retrieveList(bestSeller: List<BestSellerList>){
        bestSellers = bestSeller as ArrayList<BestSellerList>
        adapter = BestSellersAdapter(this, bestSellers)
        bookRv.adapter = adapter
        adapter.notifyDataSetChanged()
    }
    fun dialogBox(message: String, finish: Boolean){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("No Internet")
        builder.setMessage(message)
        builder.setPositiveButton("Ok") { dialog, which ->
            if(finish){
                finish()
            }
            else{
                dialog.dismiss()
            }
        }
        builder.show()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        if (id == R.id.action_logout) {
            mainViewModel.logout()
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK or
                    Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}

