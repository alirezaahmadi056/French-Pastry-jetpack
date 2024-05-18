package ir.hoseinahmadi.frenchpastry.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.hoseinahmadi.frenchpastry.data.db.PastryDataBase
import ir.hoseinahmadi.frenchpastry.data.db.dao.FaveDao
import ir.hoseinahmadi.frenchpastry.data.db.dao.ShopDao
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBaseModule(
        @ApplicationContext context: Context,
    ) =
        Room.databaseBuilder(
            context = context,
            klass = PastryDataBase::class.java,
            name = "pastry_db"
        ).build()

    @Provides
    @Singleton
    fun provideFaveDao(dataBase: PastryDataBase): FaveDao = dataBase.FaveDao()

    @Provides
    @Singleton
    fun provideShopDao(dataBase: PastryDataBase): ShopDao = dataBase.ShopDao()

}