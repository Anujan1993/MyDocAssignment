package com.anujan.mydocassignment.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.anujan.mydocassignment.R
import com.anujan.mydocassignment.entity.Results
import com.anujan.mydocassignment.room.entity.BestSellerList
import com.anujan.mydocassignment.singleresult.SingleResultActivity
import java.io.Serializable

class BestSellersAdapter(
    val context: Context,
    private val list: List<BestSellerList>
): RecyclerView.Adapter<BestSellersViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSellersViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_row_layout, parent, false)
        return BestSellersViewHolder(view)
    }
    override fun onBindViewHolder(holder: BestSellersViewHolder, position: Int) {

        val results:BestSellerList = list[position]
        holder.nameOfBook?.text = list[position].title
        holder.description?.text =list[position].description
        holder.copyRights?.text = list[position].publisher
        holder.cardView!!.setOnClickListener {
            val intent = Intent(context, SingleResultActivity::class.java)
            intent.putExtra("extra_object", results as Serializable)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class BestSellersViewHolder(itemView: View):
    RecyclerView.ViewHolder(itemView) {

    var cardView: CardView? = null
    var nameOfBook: TextView? = null
    var description: TextView? = null
    var copyRights: TextView? = null

    init {
        cardView = itemView.findViewById(R.id.clickCard)
        nameOfBook = itemView.findViewById(R.id.list_name)
        description = itemView.findViewById(R.id.description)
        copyRights = itemView.findViewById(R.id.published_date)
    }

}
