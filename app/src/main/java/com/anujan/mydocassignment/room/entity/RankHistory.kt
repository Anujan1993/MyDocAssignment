package com.anujan.mydocassignment.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "RankHistory")
data class RankHistory (
    val bookName:String,
    val list_name:String,
    val display_name:String,
    val published_date:String,
    val bestsellers_date:String,
    val rank:String,
    val weeks_on_list:String
):Serializable{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0 //**do not made it val**
}