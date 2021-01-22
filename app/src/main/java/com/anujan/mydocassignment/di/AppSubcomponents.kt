package com.anujan.mydocassignment.di

import com.anujan.mydocassignment.login.LoginComponent
import com.anujan.mydocassignment.registration.RegistrationComponent
import com.anujan.mydocassignment.user.UserComponent
import dagger.Module

// This module tells a Component which are its subcomponents
@Module(
    subcomponents = [
        RegistrationComponent::class,
        LoginComponent::class,
        UserComponent::class
    ]
)
class AppSubcomponents
