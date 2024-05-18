package ir.hoseinahmadi.frenchpastry.repository

import ir.hoseinahmadi.frenchpastry.data.db.dao.FaveDao
import ir.hoseinahmadi.frenchpastry.data.db.entites.FaveEntities
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FaveRepository @Inject constructor(
    private val dao: FaveDao
) {

    val allData =dao.allData()
    suspend fun addFaveItem(item: FaveEntities) {
        dao.addFaveItem(item)
    }

    suspend fun removeFaveItem(item: FaveEntities) {
        dao.removeFaveItem(item)
    }

    fun isHasBookmark(itemId: Int):Flow<Boolean> = dao.isHasBookmark(itemId)

}