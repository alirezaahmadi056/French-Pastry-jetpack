package ir.hoseinahmadi.frenchpastry.repository

import ir.hoseinahmadi.frenchpastry.data.db.dao.ShopDao
import ir.hoseinahmadi.frenchpastry.data.db.entites.ShopEntities
import ir.hoseinahmadi.frenchpastry.ui.screen.basket.TotalDiscountsAndPaid
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShopRepository @Inject constructor(
    private var dao: ShopDao
) {

    suspend fun addShopOrder(item: ShopEntities) {
        dao.addShopOrder(item)
    }

    suspend fun deleteShopOrder(item: ShopEntities) {
        dao.deleteShopOrder(item)
    }

   suspend fun changeCartItem(id: Int, newCount: Int) {
        dao.changeCartItem(id, newCount)
    }

    fun getAllItemShop(): Flow<List<ShopEntities>> = dao.getAllItemInShop()

    fun getAllPriceAndDiscount(): Flow<TotalDiscountsAndPaid> = dao.getTotalDiscountsAndPaid()
    suspend fun deleteAllItem(){
        dao.deleteAllItem()
    }
    fun isHasIsCart(itemId: Int): Flow<Boolean> = dao.isHasIsCart(itemId)


}