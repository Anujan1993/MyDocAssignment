package com.anujan.mydocassignment.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "BestSeller")
data class BestSellerList (
    val title:String,
    val description:String,
    val publisher:String,
    val contributor:String,
    val author:String,
    val price:String
):Serializable{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0 //**do not made it val**
}