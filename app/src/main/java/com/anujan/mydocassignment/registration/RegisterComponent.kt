package com.anujan.mydocassignment.registration

import com.anujan.mydocassignment.main.InitActivity
import com.anujan.mydocassignment.main.MainActivity
import com.anujan.mydocassignment.singleresult.SingleResultActivity
import dagger.Subcomponent

@Subcomponent
interface RegisterComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): RegisterComponent
    }

    fun inject(activity: RegisterActivity)
    fun inject(activity: MainActivity)
    fun inject(activity: SingleResultActivity)
    fun inject(activity: InitActivity)
}