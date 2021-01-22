package com.anujan.mydocassignment.main

import androidx.lifecycle.*
import com.anujan.mydocassignment.ErrorMessages
import com.anujan.mydocassignment.entity.BestSellers
import com.anujan.mydocassignment.repository.RoomRepository
import com.anujan.mydocassignment.room.entity.BestSellerList
import com.anujan.mydocassignment.user.UserDataRepository
import com.anujan.mydocassignment.user.UserManager
import com.anujan.mydocassignment.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import javax.inject.Inject

/**
 * MainViewModel is the ViewModel that [MainActivity] uses to
 * obtain information of what to show on the screen.
 *
 * @Inject tells Dagger how to provide instances of this type. Dagger also knows
 * that UserDataRepository is a dependency.
 */
class MainViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository,
    private val roomRepository: RoomRepository,
    private val userManager: UserManager)
    :ViewModel() {

    val welcomeText: String
        get() = "Hello ${userDataRepository.username}!"

    fun getBooks() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = userDataRepository.getBooksData()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: ErrorMessages.SERVER_ERROR))
        }
    }
    fun storeInRoom(bestSellers: BestSellers):String{
        for (item in bestSellers.results) {
            viewModelScope.launch {
                roomRepository.deleteBestSellers(item.title)
                roomRepository.deleteRankHistory(item.title)
                roomRepository.bestSellers(
                    item.title ?: "",
                    item.description?: "",
                    item.publisher?: "",
                    item.contributor?: "",
                    item.author?: "",
                    item.price.toString()?: ""
                )
                for (ite in item.ranks_history) {
                    roomRepository.storeRankHistory(
                        item.title ?: "",
                        ite.list_name ?: "",
                        ite.display_name ?: "",
                        ite.published_date ?: "",
                        ite.bestsellers_date ?: "",
                        ite.rank.toString() ?: "",
                        ite.weeks_on_list.toString() ?: "",
                    )
                }
            }
        }
        return "Success"
    }
    fun getBestSellers() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = roomRepository.getBestSellers()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: ErrorMessages.SERVER_ERROR))
        }
    }
    fun logout() {
        userManager.logout()
    }
}
