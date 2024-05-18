package ir.hoseinahmadi.frenchpastry.repository

import android.content.Context
import android.util.Log
import ir.hoseinahmadi.frenchpastry.data.model.addres.addredResponse
import ir.hoseinahmadi.frenchpastry.data.remote.AddressApiInterface
import ir.hoseinahmadi.frenchpastry.util.Constants
import ir.hoseinahmadi.frenchpastry.wrapper.DeviceInfo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class AddressRepository @Inject constructor(
    private val apiInterface: AddressApiInterface
) {
    val loading = MutableStateFlow(false)
    val allAddress = MutableStateFlow<addredResponse>(addredResponse())
    suspend fun getAllAddress(context: Context) {
        loading.emit(true)
        val response = try {
            apiInterface.getAllAddress(
                apiKey = Constants.API_KEY,
                deviceUid = DeviceInfo.getDeviceID(context),
                publicKey = DeviceInfo.getPublicKey(context),
            )
        } catch (e: Exception) {
            Log.e("pasi", "getAllAddress error : ${e.message}")
            return
        }
        if (response.isSuccessful) {
            val body = response.body()
            body?.let {
                allAddress.emit(it)
                loading.emit(false)
            }
        }
    }

    suspend fun addAddress(
        context: Context,
        address: String,
        receiver: String,
        phone: String,
    ) {
        loading.emit(true)
        val response = try {
            apiInterface.addAddress(
                apiKey = Constants.API_KEY,
                deviceUid = DeviceInfo.getDeviceID(context),
                publicKey = DeviceInfo.getPublicKey(context),
                address = address,
                receiver = receiver,
                phone = phone
            )
        } catch (e: Exception) {
            Log.e("pasi", "addAddress error : ${e.message}")
            loading.emit(false)
            return
        }
        if (response.isSuccessful) {
            loading.emit(false)
        }
    }

    suspend fun deleteAddress(
        context: Context,
        id:String
    ){
        loading.emit(true)
        val response = try {
            apiInterface.deleteAddress(
                apiKey = Constants.API_KEY,
                deviceUid = DeviceInfo.getDeviceID(context),
                publicKey = DeviceInfo.getPublicKey(context),
                id = id,
            )
        } catch (e: Exception) {
            Log.e("pasi", "deleteOrder error : ${e.message}")
            return
        }

        if (response.isSuccessful) {
            val body = response.body()
            body?.let {
                allAddress.emit(it)
                Log.e("pasi", "deleteOrder success : ${body.message}")

            }

            loading.emit(false)
        }

    }

     suspend fun editAddress(
        context: Context,
        id:String,
        address: String,
        receiver: String,
        phone: String,

    ){
        loading.emit(true)
        val response = try {
            apiInterface.editAddress(
                apiKey = Constants.API_KEY,
                deviceUid = DeviceInfo.getDeviceID(context),
                publicKey = DeviceInfo.getPublicKey(context),
                id = id,
                address = address,
                receiver = receiver,
                phone = phone
            )
        } catch (e: Exception) {
            Log.e("pasi", "editAddress error : ${e.message}")
            return
        }

        if (response.isSuccessful) {
            loading.emit(false)
        }

    }



}