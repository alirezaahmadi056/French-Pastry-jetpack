package ir.hoseinahmadi.frenchpastry.repository

import android.util.Log
import ir.hoseinahmadi.frenchpastry.data.model.product_detail.ProductResponse
import ir.hoseinahmadi.frenchpastry.data.remote.ProductDetailApiInterFace
import ir.hoseinahmadi.frenchpastry.util.Constants
import ir.hoseinahmadi.frenchpastry.wrapper.DeviceInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import retrofit2.http.Field
import retrofit2.http.Header
import javax.inject.Inject

class ProductDetailRepository @Inject constructor(
    private val apiInterFace: ProductDetailApiInterFace
) {
    val productItem = MutableStateFlow(ProductResponse())
    val resultSendComment = MutableStateFlow(false)
    suspend fun getProductByID(id: Int) {
        withContext(Dispatchers.IO) {
            try {
                val response = apiInterFace.getProductById(id)
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        productItem.emit(it)
                    }
                } else {
                    Log.e("pasi", "getProductByID  notSuccess")
                }
            } catch (e: Exception) {
                Log.e("pasi", "getProductByID  error ${e.message}")
                return@withContext
            }
        }

    }

    suspend fun setNewComment(
        apiKey: String,
        deviceUid: String,
        publicKey: String,
        postId: Int,
        content: String,
        rate: Int,
    ) {
        val response = try {
            apiInterFace.setNewComment(apiKey, deviceUid, publicKey, postId, content, rate)
        } catch (e: Exception) {
            Log.e("pasi",e.message.toString())
            return
        }
        if (response.isSuccessful){
            response.body()?.let {
                if (it.success==1){
                    resultSendComment.emit(true)
                }

            }
        }else{
            Log.e("pasi",response.body()?.message?:"")
        }
    }
}
