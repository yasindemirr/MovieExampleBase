package com.example.moviesbaseexample.ui.theme.di

import com.example.moviesbaseexample.ui.theme.data.repository.Auth.AuthRepositoryImpl
import com.example.moviesbaseexample.ui.theme.data.repository.Home.HomeRepositoryImpl
import com.example.moviesbaseexample.ui.theme.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideMoviesLoginRepository(
        impl: AuthRepositoryImpl,
    ): Repository.AuthRepository

    @Binds
    abstract fun provideMoviesHomeRepository(
        impl: HomeRepositoryImpl,
    ): Repository.HomeRepository
}
