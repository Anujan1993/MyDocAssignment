package com.anujan.mydocassignment.settings

import androidx.lifecycle.ViewModel
import com.anujan.mydocassignment.user.UserDataRepository
import com.anujan.mydocassignment.user.UserManager
import javax.inject.Inject

/**
 * SettingsViewModel is the ViewModel that [SettingsActivity] uses to handle complex logic.
 */
class SettingsViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository,
    private val userManager: UserManager
):ViewModel() {
    fun logout() {
        userManager.logout()
    }
}
