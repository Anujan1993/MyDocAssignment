package com.anujan.mydocassignment.di

import com.anujan.mydocassignment.storage.SharedPreferencesStorage
import com.anujan.mydocassignment.storage.Storage
import dagger.Binds
import dagger.Module

// Tells Dagger this is a Dagger module
@Module
abstract class StorageModule {

    // Makes Dagger provide SharedPreferencesStorage when a Storage type is requested
    @Binds
    abstract fun provideStorage(storage: SharedPreferencesStorage): Storage

}
