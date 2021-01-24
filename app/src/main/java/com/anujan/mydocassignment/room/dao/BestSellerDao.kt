package com.anujan.mydocassignment.room.dao

import androidx.room.*
import com.anujan.mydocassignment.room.entity.BestSellerList

@Dao
interface BestSellerDao {
    @Query("SELECT * FROM BestSeller")
    fun findBestSeller(): List<BestSellerList>

    @Insert
    fun insertAll(bestSellerList: List<BestSellerList>)

    @Query("DELETE FROM BestSeller")
    suspend fun deleteAll()
}