package ir.hoseinahmadi.frenchpastry.data.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import ir.hoseinahmadi.frenchpastry.util.Constants
import kotlinx.coroutines.flow.first
import javax.inject.Inject

private val Context.MyDataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.DATASTORE_NAME)

class DataStoreRepositoryImpl @Inject constructor(
    private val context: Context,
) : DatStoreRepository {

    override suspend fun putString(key: String, value: String) {
        val preferenceKey = stringPreferencesKey(key)
        context.MyDataStore.edit { preference ->
            preference[preferenceKey] = value
        }
    }

    override suspend fun putInt(key: String, value: Int) {
        val preferenceKey = intPreferencesKey(key)
        context.MyDataStore.edit { preference ->
            preference[preferenceKey] = value
        }
    }

    override suspend fun putBoolean(key: String, value: Boolean) {
        val preferenceKey = booleanPreferencesKey(key)
        context.MyDataStore.edit { preference ->
            preference[preferenceKey] = value
        }
    }

    override suspend fun getString(key: String): String? {
        return try {
            val preferencesKey = stringPreferencesKey(key)
            val preferences = context.MyDataStore.data.first()
            preferences[preferencesKey]
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun getInt(key: String): Int? {
        return try {
            val preferenceKey = intPreferencesKey(key)
            val preference = context.MyDataStore.data.first()
            preference[preferenceKey]
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun getBoolean(key: String): Boolean? {
        return try {
            val preferenceKey = booleanPreferencesKey(key)
            val preference = context.MyDataStore.data.first()
            preference[preferenceKey]
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }


}