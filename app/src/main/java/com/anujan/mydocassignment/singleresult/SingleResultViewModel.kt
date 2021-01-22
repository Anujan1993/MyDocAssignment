package com.anujan.mydocassignment.singleresult

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.anujan.mydocassignment.ErrorMessages
import com.anujan.mydocassignment.repository.RoomRepository
import com.anujan.mydocassignment.util.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class SingleResultViewModel @Inject constructor(
    private val roomRepository: RoomRepository
):ViewModel() {

    fun getRankHistory(bookName:String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = roomRepository.getRankHistory(bookName)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: ErrorMessages.SERVER_ERROR))
        }
    }
}