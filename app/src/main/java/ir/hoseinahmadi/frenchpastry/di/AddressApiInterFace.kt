package ir.hoseinahmadi.frenchpastry.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.hoseinahmadi.frenchpastry.data.remote.AddressApiInterface
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AddressApiInterFace {

    @Provides
    @Singleton
    fun provideAddressApiInterFace(retrofit: Retrofit): AddressApiInterface =
        retrofit.create(AddressApiInterface::class.java)

}