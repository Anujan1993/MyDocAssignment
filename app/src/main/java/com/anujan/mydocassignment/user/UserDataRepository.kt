package com.anujan.mydocassignment.user

import android.util.Log
import com.anujan.mydocassignment.EndPoints
import com.anujan.mydocassignment.api.ApiInterface
import com.anujan.mydocassignment.entity.BestSellers
import com.anujan.mydocassignment.entity.Results
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.random.Random

/**
 * UserDataRepository contains user-specific data such as username and unread notifications.
 *
 * This object will have a unique instance in a Component that is annotated with
 * @LoggedUserScope (i.e. only UserComponent in this case).
 */
@LoggedUserScope
class UserDataRepository @Inject constructor(
    private val userManager: UserManager
    ,private val apiInterface: ApiInterface
    ) {

    suspend fun getBooksData() = apiInterface.getBooks(EndPoints.API_KEY)

    val username: String
        get() = userManager.username

}
