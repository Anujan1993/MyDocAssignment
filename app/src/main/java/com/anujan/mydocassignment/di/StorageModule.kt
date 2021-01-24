package com.anujan.mydocassignment.di

import com.anujan.mydocassignment.storage.SharedPreferencesStorage
import com.anujan.mydocassignment.storage.Storage
import dagger.Binds
import dagger.Module

@Module
abstract class StorageModule {

    @Binds
    abstract fun provideStorage(storage: SharedPreferencesStorage): Storage

}
