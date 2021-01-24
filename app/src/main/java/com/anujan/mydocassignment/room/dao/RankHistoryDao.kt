package com.anujan.mydocassignment.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.anujan.mydocassignment.room.entity.RankHistory

@Dao
interface RankHistoryDao {

    @Query("SELECT * FROM RankHistory WHERE bookName = :bookName")
    fun findRankHistory(bookName: String): List<RankHistory>?

    @Insert
    fun insertAll(rankHistory: List<RankHistory>)

    @Query("DELETE FROM RankHistory")
    suspend fun delete()
}