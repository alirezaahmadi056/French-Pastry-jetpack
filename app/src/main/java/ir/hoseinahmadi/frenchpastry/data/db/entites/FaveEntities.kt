package ir.hoseinahmadi.frenchpastry.data.db.entites

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FaveEntities(
    @PrimaryKey
    val id:Int,
    val name:String,
    val salePrice:Int,
    val imgAddress:String
)
