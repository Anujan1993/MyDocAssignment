package com.anujan.mydocassignment.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anujan.mydocassignment.room.entity.RankHistory

@Dao
interface RankHistoryDao {

    @Query("SELECT * FROM RankHistory WHERE bookName = :bookName")
    fun findRankHistory(bookName: String): List<RankHistory>?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(rankHistory: RankHistory): Long

    @Query("DELETE FROM RankHistory WHERE bookName = :bookName")
    suspend fun delete(bookName: String)
}