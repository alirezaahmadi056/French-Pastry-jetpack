package ir.hoseinahmadi.frenchpastry.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.hoseinahmadi.frenchpastry.data.db.dao.FaveDao
import ir.hoseinahmadi.frenchpastry.data.db.dao.ShopDao
import ir.hoseinahmadi.frenchpastry.data.db.entites.FaveEntities
import ir.hoseinahmadi.frenchpastry.data.db.entites.ShopEntities

@Database(entities = [FaveEntities::class,ShopEntities::class], version = 1, exportSchema = false)
abstract class PastryDataBase : RoomDatabase() {

    abstract fun FaveDao(): FaveDao
    abstract fun ShopDao(): ShopDao
}