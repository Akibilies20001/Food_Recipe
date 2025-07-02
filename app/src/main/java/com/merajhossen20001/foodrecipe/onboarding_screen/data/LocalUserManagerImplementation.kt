package com.merajhossen20001.foodrecipe.onboarding_screen.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.edit
import com.merajhossen20001.foodrecipe.onboarding_screen.domain.LocalUserManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManagerImplementation (private val context: Context): LocalUserManager{
    override suspend fun saveAppEntry() {
        context.datastore.edit{settings->
            settings[PreferencesKeys.APP_ENTRY] = true

        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return context.datastore.data.map { preferences->
            preferences[PreferencesKeys.APP_ENTRY]?:false

        }
    }
}

private val Context.datastore : DataStore<Preferences> by preferencesDataStore(name = "userSettings")

private object PreferencesKeys{
    val APP_ENTRY = booleanPreferencesKey("appEntry")
}