package ir.hoseinahmadi.frenchpastry.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.hoseinahmadi.frenchpastry.data.remote.ProductDetailApiInterFace
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProductDetailInterFaceModule {

    @Singleton
    @Provides
    fun provideProductApiInterFaceModule(retrofit: Retrofit): ProductDetailApiInterFace =
        retrofit.create(ProductDetailApiInterFace::class.java)
}