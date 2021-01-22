package com.anujan.mydocassignment

object ErrorMessages{
    const val SERVER_ERROR = "Error Occurred!"
    const val EMAIL_ERROR = "Please Enter the Correct Email"
    const val PASSWORD_ERROR = "Please Enter the Correct Password"
    const val NAME_ERROR = "Please Enter the Name"
    const val PHONE_ERROR = "Please Enter the Phone Number"
    const val CONFORM_PASSWORD_ERROR = "Please Enter the Conform Password"
    const val PASSWORD_NOT_MATCHED = "Passwords are not matched"
    const val USER_NOT_EXIST = "User not exist!"
}
object EndPoints {
    const val GET_BOOKS = "history.json"
    const val GET_USER = "users/{id}"
    const val API_KEY = "YSkqoh9LvOt48No49bD1tlOfkzDc2rZJ"
    const val DATABASE_NAME = "mydoc.db"
    const val BASE_URL = "https://api.nytimes.com/svc/books/v3/lists/best-sellers/"
}
