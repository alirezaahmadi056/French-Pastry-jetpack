package ir.hoseinahmadi.frenchpastry.repository

import android.content.Context
import android.util.Log
import ir.hoseinahmadi.frenchpastry.data.model.home.HomeResponse
import ir.hoseinahmadi.frenchpastry.data.model.login.SendCodeResponse
import ir.hoseinahmadi.frenchpastry.data.model.login.VerifyCodeResponse
import ir.hoseinahmadi.frenchpastry.data.model.product_detail.ProductResponse
import ir.hoseinahmadi.frenchpastry.data.remote.HomeApiInterFace
import ir.hoseinahmadi.frenchpastry.wrapper.DeviceInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val apiInterFace: HomeApiInterFace
) {

    val main = MutableStateFlow<HomeResponse>(HomeResponse())
    val sendCodeResponse = MutableStateFlow<SendCodeResponse>(SendCodeResponse())
    val verifyCodeResponse = MutableStateFlow<VerifyCodeResponse>(VerifyCodeResponse())
    val loading = MutableStateFlow(false)
    val errorVerifyCode =MutableStateFlow(false)
    suspend fun getMain() {
        val response = try {
            apiInterFace.getMain()
        } catch (e: Exception) {
            return
        }
        if (response.isSuccessful) {
            val body = response.body()
            body?.let {
                main.emit(it)
            }
        }


    }

    suspend fun senCodePhone(phone: String,context: Context) {
        loading.emit(true)
        val response = try {
            apiInterFace.sendCodeWithEmail(
                phone = phone,
                deviceId = DeviceInfo.getDeviceID(context),
                publicId = DeviceInfo.getPublicKey(context)
            )
        } catch (e: Exception) {
            Log.e("pasi", "senCodePhone error :${e.message.toString()}")
            return
        }
        if (response.isSuccessful) {
            val body = response.body()
            body?.let {
                sendCodeResponse.emit(it)
                loading.emit(false)
            }
        } else {
            loading.emit(false)
            Log.e("pasi", "senCodePhone not succes ")

        }
    }

    suspend fun verifyCode(code:String ,phone: String,context: Context){
        loading.emit(true)
        val response = try {
            apiInterFace.verifyCode(code = code, phone = phone,
                deviceId = DeviceInfo.getDeviceID(context),
                publicId = DeviceInfo.getPublicKey(context)
            )
        } catch (e: Exception) {
            Log.e("pasi", "verifyCode error :${e.message.toString()}")
            return
        }
        if (response.isSuccessful) {
            loading.emit(false)
            val body = response.body()
            body?.let {
                verifyCodeResponse.emit(it)
            }
        } else {
            loading.emit(false)
            errorVerifyCode.emit(true)
            Log.e("pasi", "verifyCode not succes ")

        }

    }


}