package com.anujan.mydocassignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.anujan.mydocassignment.R
import com.anujan.mydocassignment.entity.RanksHistory
import com.anujan.mydocassignment.room.entity.RankHistory

class RanksHistoryAdapter (
    val context: Context,
    private val list: List<RankHistory>
): RecyclerView.Adapter<RanksHistoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RanksHistoryViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.ranks_history_row, parent, false)
        return RanksHistoryViewHolder(view)
    }
    override fun onBindViewHolder(holder: RanksHistoryViewHolder, position: Int) {

        holder.list_name?.text = list[position].list_name
        holder.display_name?.text =list[position].display_name
        holder.published_date?.text = list[position].published_date
        holder.bestsellers_date?.text = list[position].bestsellers_date
        holder.rank?.text =list[position].rank
        holder.weeks_on_list?.text = list[position].weeks_on_list
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class RanksHistoryViewHolder(itemView: View):
    RecyclerView.ViewHolder(itemView) {

    var cardView: CardView? = null
    var list_name: TextView? = null
    var display_name: TextView? = null
    var published_date: TextView? = null
    var bestsellers_date: TextView? = null
    var rank: TextView? = null
    var weeks_on_list: TextView? = null

    init {
        cardView = itemView.findViewById(R.id.clickCard)
        list_name = itemView.findViewById(R.id.list_name)
        display_name = itemView.findViewById(R.id.description)
        published_date = itemView.findViewById(R.id.published_date)
        bestsellers_date = itemView.findViewById(R.id.bestsellers_date)
        rank = itemView.findViewById(R.id.rank)
        weeks_on_list = itemView.findViewById(R.id.weeks_on_list)
    }

}
