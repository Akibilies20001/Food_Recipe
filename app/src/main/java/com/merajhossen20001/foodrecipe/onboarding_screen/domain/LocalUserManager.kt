package com.merajhossen20001.foodrecipe.onboarding_screen.domain

import kotlinx.coroutines.flow.Flow


interface LocalUserManager {
    suspend fun saveAppEntry()

    fun readAppEntry(): Flow<Boolean>
}