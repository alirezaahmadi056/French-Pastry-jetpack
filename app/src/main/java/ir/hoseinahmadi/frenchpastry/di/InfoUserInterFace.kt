package ir.hoseinahmadi.frenchpastry.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.hoseinahmadi.frenchpastry.data.remote.InfoUserInterFace
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InfoUserInterFace {

    @Provides
    @Singleton
    fun provideInfoUserApiInterFace(retrofit: Retrofit): InfoUserInterFace =
        retrofit.create(InfoUserInterFace::class.java)

}