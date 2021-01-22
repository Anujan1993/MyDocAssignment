package com.anujan.mydocassignment.user

import com.anujan.mydocassignment.main.MainActivity
import com.anujan.mydocassignment.singleresult.SingleResultActivity
import dagger.Subcomponent

// Scope annotation that the UserComponent uses
// Classes annotated with @LoggedUserScope will have a unique instance in this Component
@LoggedUserScope
// Definition of a Dagger subcomponent
@Subcomponent
interface UserComponent {

    // Factory to create instances of UserComponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): UserComponent
    }

    // Classes that can be injected by this Component
    fun inject(activity: MainActivity)
    fun inject(activity: SingleResultActivity)
}
