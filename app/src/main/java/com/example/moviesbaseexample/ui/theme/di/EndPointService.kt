package com.example.moviesbaseexample.ui.theme.di

import com.example.moviesbaseexample.ui.theme.data.remote.Services
import com.example.moviesbaseexample.ui.theme.data.repository.Auth.AuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EndPointService{


    @Provides
    @Singleton
    fun provideParameterServiceAuthEndpoints(
       retrofit : Retrofit
    ) : Services.Auth = retrofit.create(Services.Auth::class.java)

    @Provides
    @Singleton
    fun provideParameterServiceHomeEndpoints(
        retrofit : Retrofit
    ) : Services.Home = retrofit.create(Services.Home::class.java)

}