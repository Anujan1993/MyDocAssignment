package com.anujan.mydocassignment.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anujan.mydocassignment.login.LoginViewModel
import com.anujan.mydocassignment.main.MainViewModel
import com.anujan.mydocassignment.registration.RegistrationViewModel
import com.anujan.mydocassignment.registration.enterdetails.EnterDetailsViewModel
import com.anujan.mydocassignment.singleresult.SingleResultViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @IntoMap
    @Binds
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(RegistrationViewModel::class)
    abstract fun bindRegisterViewModel(registerViewModel: RegistrationViewModel):ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel):ViewModel


    @IntoMap
    @Binds
    @ViewModelKey(EnterDetailsViewModel::class)
    abstract fun bindEnterDetailsViewModel(enterDetailsViewModel: EnterDetailsViewModel):ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(SingleResultViewModel::class)
    abstract fun bindEnterSingleResultViewModel(singleResultViewModel: SingleResultViewModel):ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory

}