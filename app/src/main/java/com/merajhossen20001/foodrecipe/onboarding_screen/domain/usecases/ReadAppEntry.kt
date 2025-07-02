package com.merajhossen20001.foodrecipe.onboarding_screen.domain.usecases

import com.merajhossen20001.foodrecipe.onboarding_screen.domain.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUserManager: LocalUserManager
) {
    operator fun invoke():Flow<Boolean>{
        return localUserManager.readAppEntry()

    }
}