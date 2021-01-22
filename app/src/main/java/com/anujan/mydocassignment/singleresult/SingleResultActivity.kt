package com.anujan.mydocassignment.singleresult

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.anujan.mydocassignment.MyApplication
import com.anujan.mydocassignment.R
import com.anujan.mydocassignment.adapter.BestSellersAdapter
import com.anujan.mydocassignment.adapter.RanksHistoryAdapter
import com.anujan.mydocassignment.room.entity.RankHistory
import com.anujan.mydocassignment.room.entity.BestSellerList
import com.anujan.mydocassignment.util.Status
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_single_result.*
import javax.inject.Inject

class SingleResultActivity : AppCompatActivity() {
    @Inject
    lateinit var singleResultViewModel: SingleResultViewModel
    private lateinit var adapter : RanksHistoryAdapter
    var ranksHistory: ArrayList<RankHistory> = ArrayList()
    private lateinit var progress: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        val userManager = (application as MyApplication).appComponent.userManager()
        userManager.userComponent!!.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_result)
        progress = findViewById(R.id.progressBar)


        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Book Details.."
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)


        rankRV.layoutManager = LinearLayoutManager(this)

        val bestSellerList:BestSellerList = intent.extras?.getSerializable("extra_object") as BestSellerList
        bookTitle.text = bestSellerList.title
        description.text = bestSellerList.description
        contributor.text = bestSellerList.contributor
        author.text = bestSellerList.author
        publisher.text = bestSellerList.publisher
        price.text = bestSellerList.price
        //ranksHistory = singleResults.ranks_history as ArrayList<RankHistory>

        adapter = RanksHistoryAdapter(this, ranksHistory)
        rankRV.adapter = adapter

        roomDataObservers(bestSellerList.title)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    private fun roomDataObservers(bookName:String) {
        this.let {
            singleResultViewModel.getRankHistory(bookName).observe(it, Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            rankRV.visibility = View.VISIBLE
                            progress.visibility = View.GONE
                            resource.data?.let { it1 ->
                                resource.data.let { rankHistory -> retrieveList(rankHistory) }
                            }
                        }
                        Status.ERROR -> {
                            rankRV.visibility = View.VISIBLE
                            progress.visibility = View.GONE
                            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        }
                        Status.LOADING -> {
                            progress.visibility = View.VISIBLE
                            rankRV.visibility = View.GONE
                        }
                    }
                }
            })
        }
    }
    fun retrieveList(rankHistory: List<RankHistory>){
        ranksHistory = rankHistory as ArrayList<RankHistory>
        adapter = RanksHistoryAdapter(this, ranksHistory)
        rankRV.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}