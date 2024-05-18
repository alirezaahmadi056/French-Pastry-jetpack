package ir.hoseinahmadi.frenchpastry.viewModel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.hoseinahmadi.frenchpastry.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    var userPhone by mutableStateOf("")
    var userCode by mutableStateOf("")


    val mainResponse = repository.main
    val loading = repository.loading
    val sendCodeResponse = repository.sendCodeResponse
    val verifyCodeResponse = repository.verifyCodeResponse
    val errorVerifyCode = repository.errorVerifyCode
    suspend fun getMain() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getMain()
        }
    }

    fun login(context: Context) {
        viewModelScope.launch {
            repository.senCodePhone(userPhone,context)
        }
    }

    fun verifyCode(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.verifyCode(code = userCode, phone = userPhone,context)
        }
    }

}