package com.anujan.mydocassignment.api

import com.anujan.mydocassignment.EndPoints
import com.anujan.mydocassignment.entity.BestSellers
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {
    @GET(EndPoints.GET_BOOKS)
    suspend fun getBooks(@Query("api-key") key: String?): BestSellers
}