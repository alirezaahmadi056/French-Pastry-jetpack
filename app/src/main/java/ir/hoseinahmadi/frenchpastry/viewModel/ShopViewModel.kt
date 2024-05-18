package ir.hoseinahmadi.frenchpastry.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.hoseinahmadi.frenchpastry.data.db.entites.ShopEntities
import ir.hoseinahmadi.frenchpastry.repository.ShopRepository
import ir.hoseinahmadi.frenchpastry.ui.screen.basket.TotalDiscountsAndPaid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val repository: ShopRepository
) : ViewModel() {

    val allItemShop: Flow<List<ShopEntities>> = repository.getAllItemShop()

    val allPriceAndDiscount: Flow<TotalDiscountsAndPaid> = repository.getAllPriceAndDiscount()
    fun addShopOrder(item: ShopEntities) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addShopOrder(item)
        }
    }

    fun deleteShopOrder(item: ShopEntities) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteShopOrder(item)
        }


    }

    fun changeCartItem(id: Int, newCount: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.changeCartItem(id, newCount)
        }

    }

    fun deleteAllItem() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllItem()
        }
    }

    fun isHasIsCart(itemId: Int): Flow<Boolean> = repository.isHasIsCart(itemId)

}