package ir.hoseinahmadi.frenchpastry.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.hoseinahmadi.frenchpastry.data.model.product_detail.ProductResponse
import ir.hoseinahmadi.frenchpastry.repository.ProductDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val repository: ProductDetailRepository
) : ViewModel() {

    val productItem = repository.productItem
    val resultSendComment:Flow<Boolean> = repository.resultSendComment
    suspend fun getProductById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getProductByID(id)
        }
    }


    fun setNewComment(
        apiKey: String,
        deviceUid: String,
        publicKey: String,
        postId: Int,
        content: String,
        rate: Int,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.setNewComment(apiKey, deviceUid, publicKey, postId, content, rate)
        }
    }
}