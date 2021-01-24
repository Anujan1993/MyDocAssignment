package com.anujan.mydocassignment

import android.app.Application
import com.anujan.mydocassignment.di.AppComponent
import com.anujan.mydocassignment.di.DaggerAppComponent

open class MyApplication : Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}
