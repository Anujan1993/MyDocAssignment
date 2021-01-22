package com.anujan.mydocassignment.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.anujan.mydocassignment.room.entity.BestSellerList
import java.util.concurrent.Flow

@Dao
interface BestSellerDao {
    @Query("SELECT * FROM BestSeller")
    fun findBestSeller(): List<BestSellerList>

    @Insert
    fun insertAll(bestSellerList: List<BestSellerList>)

    @Query("DELETE FROM BestSeller")
    suspend fun deleteAll()
}