package ir.hoseinahmadi.frenchpastry.data.db.entites

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ShopEntities(
    @PrimaryKey
    val id:Int,
    val title:String,
    val price:Int,
    val discount :Int,
    val img:String,
    val count:Int,
)
