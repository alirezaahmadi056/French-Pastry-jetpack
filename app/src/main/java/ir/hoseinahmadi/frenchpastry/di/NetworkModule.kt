package ir.hoseinahmadi.frenchpastry.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.hoseinahmadi.frenchpastry.util.Constants.BASE_URL
import ir.hoseinahmadi.frenchpastry.util.Constants.TIMEOUT_IN_SECOND
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    internal fun interceptor(): HttpLoggingInterceptor {
        val login = HttpLoggingInterceptor()
        login.setLevel(HttpLoggingInterceptor.Level.BODY)
        return login
    }

    @Provides
    @Singleton
    fun provideOkhttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_IN_SECOND, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_IN_SECOND, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_IN_SECOND, TimeUnit.SECONDS)
            .addInterceptor(interceptor())
            .build()
    }


    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) :Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()



}