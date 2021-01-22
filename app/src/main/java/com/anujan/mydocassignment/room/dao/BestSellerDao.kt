package com.anujan.mydocassignment.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.anujan.mydocassignment.room.entity.BestSellerList
import java.util.concurrent.Flow

@Dao
interface BestSellerDao {
    @Query("SELECT * FROM BestSeller")
    fun findBestSeller(): List<BestSellerList>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(bestSellerList: BestSellerList): Long

    @Query("DELETE FROM BestSeller WHERE title=:title")
    suspend fun deleteAll(title: String)
}