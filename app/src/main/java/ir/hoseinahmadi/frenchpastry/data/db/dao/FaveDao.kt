package ir.hoseinahmadi.frenchpastry.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.hoseinahmadi.frenchpastry.data.db.entites.FaveEntities
import kotlinx.coroutines.flow.Flow

@Dao
interface FaveDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFaveItem(item: FaveEntities)

    @Delete
    suspend fun removeFaveItem(item: FaveEntities)

    @Query("select exists(select * from faveentities where id=:itemId) ")
    fun isHasBookmark(itemId: Int): Flow<Boolean>

    @Query("SELECT  * FROM faveentities")
    fun allData(): Flow<List<FaveEntities>>

}