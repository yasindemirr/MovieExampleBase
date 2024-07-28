package com.example.moviesbaseexample.ui.theme.di

import android.content.Context
import android.content.SharedPreferences
import com.example.moviesbaseexample.ui.theme.data.shared.AccessTokenSharedProperty
import com.example.moviesbaseexample.ui.theme.util.SessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("movieapp_prefs", Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun provideAccesTokenShared( preferences: SharedPreferences) =
        AccessTokenSharedProperty(preferences)

    @Singleton
    @Provides
    fun provideSessionManager(shared: AccessTokenSharedProperty) =
        SessionManager(shared)
}