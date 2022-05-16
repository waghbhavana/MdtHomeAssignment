package com.example.mdthomeassignment.data

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences(context: Context) {
    private val appContext = context.applicationContext
    private val dataStore: DataStore<Preferences> =
        appContext.createDataStore(name = "user_datastore")


    val authToken: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[KEY_AUTH]
        }

    val username: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[USERNAME]
        }

    suspend fun saveAuthToken(authToken: String) {
        dataStore.edit { preferences ->
            preferences[KEY_AUTH] = authToken
        }
    }
        suspend fun saveUsername(username: String) {
            dataStore.edit { preferences ->
                preferences[USERNAME] = username
            }
        }

        companion object {
            private val KEY_AUTH = preferencesKey<String>("auth_token")
            private val USERNAME = preferencesKey<String>("username")
        }
    }