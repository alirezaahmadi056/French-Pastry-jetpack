package ir.hoseinahmadi.frenchpastry.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.hoseinahmadi.frenchpastry.data.dataStore.DataStoreRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class DatStoreViewModel @Inject constructor(
    private val repository: DataStoreRepositoryImpl
) : ViewModel() {

    companion object {
        const val USER_Api_KEY = "USER_Api_KEY"
        const val USER_NAME_KEY = "USER_NAME_KEY"
        const val USER_PHONE_KEY = "USER_PHONE_KEY"
        const val CHECKED_LOGIN ="CHECKED_LOGIN"
    }


    fun saveUserLogin(value: Boolean) {
        viewModelScope.launch {
            repository.putBoolean(CHECKED_LOGIN, value)
        }
    }

    fun getUserLogin(): Boolean = runBlocking {
        repository.getBoolean(CHECKED_LOGIN) ?:false
    }


    fun saveUserApiKey(value: String) {
        viewModelScope.launch {
            repository.putString(USER_Api_KEY, value)
        }
    }

    fun getUserApiKey(): String = runBlocking {
        repository.getString(USER_Api_KEY) ?:""
    }


    fun saveUserName(value: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.putString(USER_NAME_KEY, value)
        }
    }

    fun getUserName(): String = runBlocking {
        repository.getString(USER_NAME_KEY)?:""
    }

    fun saveUserPhone(value: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.putString(USER_PHONE_KEY, value)
        }
    }

    fun getUserPhone(): String = runBlocking {
        repository.getString(USER_PHONE_KEY)?:""
    }




}