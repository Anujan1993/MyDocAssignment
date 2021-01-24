package com.anujan.mydocassignment.main

import androidx.lifecycle.*
import com.anujan.mydocassignment.ErrorMessages
import com.anujan.mydocassignment.entity.BestSellers
import com.anujan.mydocassignment.keyName
import com.anujan.mydocassignment.repository.RoomRepository
import com.anujan.mydocassignment.room.entity.BestSellerList
import com.anujan.mydocassignment.room.entity.RankHistory
import com.anujan.mydocassignment.repository.UserDataRepository
import com.anujan.mydocassignment.repository.UserRepository
import com.anujan.mydocassignment.repository.implementation.UserRepositoryImpl
import com.anujan.mydocassignment.storage.Storage
import com.anujan.mydocassignment.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
        private val userDataRepository: UserDataRepository,
        private val roomRepository: RoomRepository,
        private val storage: Storage)
    :ViewModel() {
    val userRepository: UserRepository = UserRepositoryImpl(storage)
    var bestSellersList:ArrayList<BestSellerList> = ArrayList()
    var rankHistory:ArrayList<RankHistory> =ArrayList()


    val username: String
        get() = storage.getString(keyName.REGISTERED_USER)

    private var _userName = MutableLiveData<String?>()
    val userName: LiveData<String?>
        get() = _userName

    fun getUserName(){
        viewModelScope.launch {
            when(val user = storage.getString(keyName.REGISTERED_USER)){
                "" -> {
                    _userName.value = user
                }
                else -> _userName.value = user
            }
        }
    }

    fun getBooks() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = userDataRepository.getBooksData()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: ErrorMessages.SERVER_ERROR))
        }
    }
    fun storeInRoom(bestSellers: BestSellers): ArrayList<BestSellerList>? {
        for (item in bestSellers.results) {
            val bestSellerList2 = BestSellerList(
                item.title ?: "",
                item.description?: "",
                item.publisher?: "",
                item.contributor?: "",
                item.author?: "",
                item.price.toString()?: ""
            )
            for (ite in item.ranks_history) {
               val roomRepositoryRankHistory= RankHistory(
                    item.title ?: "",
                    ite.list_name ?: "",
                    ite.display_name ?: "",
                    ite.published_date ?: "",
                    ite.bestsellers_date ?: "",
                    ite.rank.toString() ?: "",
                    ite.weeks_on_list.toString() ?: "",
                )
                rankHistory.add(roomRepositoryRankHistory)
            }
            bestSellersList.add(bestSellerList2)
        }
        storeData(bestSellersList,rankHistory)
        return bestSellersList
    }
    fun storeData(bestSellerList: ArrayList<BestSellerList>,rankHistorys: ArrayList<RankHistory>){
        viewModelScope.launch {
            roomRepository.deleteBestSellers()
            roomRepository.deleteRankHistory()
            roomRepository.bestSellers(bestSellerList)
            roomRepository.storeRankHistory(rankHistorys)

        }
    }
    fun getBestSellers() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = roomRepository.getBestSellers()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: ErrorMessages.SERVER_ERROR))
        }
    }

    fun logOut(){
        userRepository.logOut()
    }

}
