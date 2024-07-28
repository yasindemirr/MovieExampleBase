package com.example.moviesbaseexample.ui.theme.di

import com.example.moviesbaseexample.ui.theme.data.repository.Auth.AuthRepositoryImpl
import com.example.moviesbaseexample.ui.theme.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Provides
    @Singleton
    abstract fun provideMoviesRepository(
        impl: AuthRepositoryImpl,
    ): Repository.AuthRepository
}*/
