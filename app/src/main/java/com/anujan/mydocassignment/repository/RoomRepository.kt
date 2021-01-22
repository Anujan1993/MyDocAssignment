package com.anujan.mydocassignment.repository

import androidx.lifecycle.MutableLiveData
import com.anujan.mydocassignment.room.dao.BestSellerDao
import com.anujan.mydocassignment.room.dao.RankHistoryDao
import com.anujan.mydocassignment.room.entity.BestSellerList
import com.anujan.mydocassignment.room.entity.RankHistory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.anujan.mydocassignment.util.Result
import java.util.concurrent.Flow
import javax.inject.Inject

class RoomRepository  @Inject constructor(
    var bestSellersDao: BestSellerDao,var rankHistoryDao: RankHistoryDao
) {

    fun getBestSellers() = bestSellersDao.findBestSeller()

    suspend fun deleteBestSellers(){
        bestSellersDao.deleteAll()
    }

    suspend fun bestSellers(bestSellerList: ArrayList<BestSellerList>){
        val registerResult = MutableLiveData<Result<String>>()
        withContext(Dispatchers.IO) {
            try {
                bestSellersDao.insertAll(bestSellerList)
                registerResult.postValue(Result.Success("Interst Success"))
            }
            catch (exception : Exception){
                registerResult.postValue(Result.Error(Exception("Error")))
            }
        }
    }

    suspend fun storeRankHistory(
            rankHistory: ArrayList<RankHistory>
    ){
        val registerResult = MutableLiveData<Result<String>>()
        withContext(Dispatchers.IO) {
            try {
                rankHistoryDao.insertAll(rankHistory)
                registerResult.postValue(Result.Success("Interst Success"))
            }
            catch (exception : Exception){
                registerResult.postValue(Result.Error(Exception("Error")))
            }
        }
    }


    suspend fun deleteRankHistory(){
        rankHistoryDao.delete()
    }

    fun getRankHistory(bookName:String) = rankHistoryDao.findRankHistory(bookName)

}
