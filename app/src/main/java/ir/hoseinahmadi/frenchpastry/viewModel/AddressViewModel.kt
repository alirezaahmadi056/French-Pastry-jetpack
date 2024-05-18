package ir.hoseinahmadi.frenchpastry.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.hoseinahmadi.frenchpastry.data.model.addres.addredResponse
import ir.hoseinahmadi.frenchpastry.repository.AddressRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(
    private val repository: AddressRepository
) : ViewModel() {

    val allAddress: Flow<addredResponse> = repository.allAddress
    val loading = repository.loading

    fun getAllAddress(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllAddress(context)
        }
    }

    fun addAddress(
        context: Context,
        address: String,
        receiver: String,
        phone: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAddress(
                context,
                address = address,
                receiver = receiver,
                phone = phone
            )
        }
    }

    fun deleteAddress(
        context: Context,
        id: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAddress(context, id)
        }
    }

    fun editAddress(
        context: Context,
        id: String,
        address: String,
        receiver: String,
        phone: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.editAddress(
                context,
                id = id,
                address = address,
                receiver = receiver,
                phone = phone
            )
        }
    }

}