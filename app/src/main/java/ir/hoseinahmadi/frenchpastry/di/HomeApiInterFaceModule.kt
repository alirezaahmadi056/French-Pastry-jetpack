package ir.hoseinahmadi.frenchpastry.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.hoseinahmadi.frenchpastry.data.remote.HomeApiInterFace
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeApiInterFaceModule {

    @Provides
    @Singleton
    fun provideHomeApiInterFace(retrofit: Retrofit) :HomeApiInterFace =
        retrofit.create(HomeApiInterFace::class.java)
}