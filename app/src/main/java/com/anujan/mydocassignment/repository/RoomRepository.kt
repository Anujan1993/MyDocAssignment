package com.anujan.mydocassignment.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anujan.mydocassignment.EndPoints
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

    //val bestSellerList: LiveData<List<BestSellerList>> = bestSellersDao.findBestSeller()

    fun getBestSellers() = bestSellersDao.findBestSeller()

    suspend fun deleteBestSellers(title:String){
        bestSellersDao.deleteAll(title)
    }

    suspend fun bestSellers(
        title: String,
        description:String,
        publisher:String,
        contributor:String,
        author:String,
        price:String
    ){
        val registerResult = MutableLiveData<Result<String>>()
        withContext(Dispatchers.IO) {
            try {
                bestSellersDao.insert(BestSellerList(title,description,publisher,contributor,author,price))
                registerResult.postValue(Result.Success("Interst Success"))
            }
            catch (exception : Exception){
                registerResult.postValue(Result.Error(Exception("Error")))
            }
        }
       // return registerResult
    }

    suspend fun storeRankHistory(
        bookName:String,
        list_name:String,
        display_name:String,
        published_date:String,
        bestsellers_date:String,
        rank:String,
        weeks_on_list:String
    ){
        val registerResult = MutableLiveData<Result<String>>()
        withContext(Dispatchers.IO) {
            try {
                rankHistoryDao.insert(RankHistory(bookName,list_name,display_name,published_date,bestsellers_date,rank,weeks_on_list))
                registerResult.postValue(Result.Success("Interst Success"))
            }
            catch (exception : Exception){
                registerResult.postValue(Result.Error(Exception("Error")))
            }
        }
    }


    suspend fun deleteRankHistory(title:String){
        rankHistoryDao.delete(title)
    }

    fun getRankHistory(bookName:String) = rankHistoryDao.findRankHistory(bookName)

}
