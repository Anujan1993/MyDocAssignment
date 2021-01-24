package com.anujan.mydocassignment.repository

import com.anujan.mydocassignment.EndPoints
import com.anujan.mydocassignment.api.ApiInterface
import javax.inject.Inject

class UserDataRepository @Inject constructor( private val apiInterface: ApiInterface ) {

    suspend fun getBooksData() = apiInterface.getBooks(EndPoints.API_KEY)

}
