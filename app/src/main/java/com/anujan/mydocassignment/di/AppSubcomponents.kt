package com.anujan.mydocassignment.di

import com.anujan.mydocassignment.login.LoginComponent
import com.anujan.mydocassignment.registration.RegisterComponent
import dagger.Module

@Module(
    subcomponents = [
        RegisterComponent::class,
        LoginComponent::class
    ]
)
class AppSubcomponents
