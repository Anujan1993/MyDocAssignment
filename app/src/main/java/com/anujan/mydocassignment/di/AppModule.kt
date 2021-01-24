package com.anujan.mydocassignment.di

import android.content.Context
import androidx.room.Room
import com.anujan.mydocassignment.EndPoints
import com.anujan.mydocassignment.room.AppDataBase
import com.anujan.mydocassignment.room.dao.BestSellerDao
import com.anujan.mydocassignment.room.dao.RankHistoryDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): AppDataBase {
        return Room.databaseBuilder(context, AppDataBase::class.java, EndPoints.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesRankHistoryDao(database: AppDataBase): RankHistoryDao {
        return database.rankHistoryDao()
    }

    @Singleton
    @Provides
    fun providesBestSellerDao(database: AppDataBase): BestSellerDao {
        return database.bestSellerDao()
    }
}