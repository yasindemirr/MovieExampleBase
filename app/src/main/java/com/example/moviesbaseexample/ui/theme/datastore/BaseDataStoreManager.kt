package com.example.moviesbaseexample.ui.theme.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import kotlinx.coroutines.flow.Flow

abstract class BaseDataStoreManager<W>(
    protected val context : Context
) {
    abstract suspend fun save(w : W?)


    abstract suspend  fun get() :W?
}