package com.merajhossen20001.foodrecipe.onboarding_screen.domain.usecases

import com.merajhossen20001.foodrecipe.onboarding_screen.domain.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke(){
        localUserManager.saveAppEntry()

    }
}