package com.anujan.mydocassignment.di

import android.content.Context
import com.anujan.mydocassignment.login.LoginComponent
import com.anujan.mydocassignment.registration.RegistrationComponent
import com.anujan.mydocassignment.user.UserManager
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [StorageModule::class,AppSubcomponents::class, NetworkModule::class,AppModule::class])
interface  AppComponent {
//
//    fun inject(loginActivity: LoginActivity)
//    fun inject(registrationActivity: RegistrationActivity)
//    fun inject(mainActivity: MainActivity)
//    fun inject(singleResultActivity: SingleResultActivity)

    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }

    // Types that can be retrieved from the graph
    fun registrationComponent(): RegistrationComponent.Factory
    fun loginComponent(): LoginComponent.Factory
    fun userManager(): UserManager
}
