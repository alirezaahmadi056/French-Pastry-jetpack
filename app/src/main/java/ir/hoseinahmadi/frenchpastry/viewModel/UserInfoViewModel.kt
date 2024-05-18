package ir.hoseinahmadi.frenchpastry.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.hoseinahmadi.frenchpastry.data.model.userInfo.UserInfoResponse
import ir.hoseinahmadi.frenchpastry.repository.UserInfoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel @Inject constructor(
    private var repository: UserInfoRepository
) : ViewModel() {

    val userInfo: Flow<UserInfoResponse> = repository.userInfo
    val loading: Flow<Boolean> = repository.loading
    val userSendInfo = repository.userSendInfo
    fun getUserInfo(
        context: Context,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUserInfo(
                context = context
            )
        }
    }

    fun sendUserInfo(
        deviceUid: String,
        publicKey: String,
        apiKey: String,
        fullName: String,
        nationalCode: String,
        email: String,
        sex: String,
        birthdate: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.sendUserInfo(
                deviceUid = deviceUid,
                publicKey = publicKey,
                apiKey = apiKey,
                fullName = fullName,
                nationalCode = nationalCode,
                email = email,
                sex = sex,
                birthdate = birthdate,
            )
        }
    }


}