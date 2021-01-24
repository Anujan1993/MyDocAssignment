package com.anujan.mydocassignment.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.anujan.mydocassignment.room.dao.BestSellerDao
import com.anujan.mydocassignment.room.dao.RankHistoryDao
import com.anujan.mydocassignment.room.entity.BestSellerList
import com.anujan.mydocassignment.room.entity.RankHistory

@Database(entities = [BestSellerList::class,RankHistory::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun bestSellerDao(): BestSellerDao
    abstract fun rankHistoryDao(): RankHistoryDao
}