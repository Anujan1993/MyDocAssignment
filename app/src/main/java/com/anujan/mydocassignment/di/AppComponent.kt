package com.anujan.mydocassignment.di

import android.content.Context
import com.anujan.mydocassignment.login.LoginComponent
import com.anujan.mydocassignment.registration.RegisterComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [StorageModule::class,AppSubcomponents::class, NetworkModule::class,AppModule::class])
interface  AppComponent {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): AppComponent
    }

    fun registrationComponent(): RegisterComponent.Factory
    fun loginComponent(): LoginComponent.Factory
}
