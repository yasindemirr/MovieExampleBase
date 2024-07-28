package com.example.moviesbaseexample.ui.theme.di

import com.example.moviesbaseexample.BuildConfig
import com.example.moviesbaseexample.ui.theme.data.service.interceptor.AuthInterceptor
import com.example.moviesbaseexample.ui.theme.data.service.interceptor.ErrorInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @ErrorInterceptorOkHttpClient
    @Singleton
    @Provides
    fun providesErrorInterceptor() : Interceptor = ErrorInterceptor()

    @Singleton
    @Provides
    fun provideLoginRetrofitService( okHttpClient : OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    }
    @Singleton
    @Provides
    fun provideOkHttpClientBuilder(
        @ErrorInterceptorOkHttpClient errorInterceptor: Interceptor,
        authInterceptor : AuthInterceptor
    ): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(errorInterceptor)
            .addInterceptor(authInterceptor)
            .addNetworkInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY
                else
                    HttpLoggingInterceptor.Level.NONE
            })
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
    }
}