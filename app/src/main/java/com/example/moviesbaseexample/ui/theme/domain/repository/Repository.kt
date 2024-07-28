package com.example.moviesbaseexample.ui.theme.domain.repository

import com.example.moviesbaseexample.ui.theme.data.model.TokenResponse
import com.example.moviesbaseexample.ui.theme.util.dataresource.RepoResult

sealed interface Repository{
    interface AuthRepository{
        suspend fun getAuth():RepoResult<TokenResponse>
    }
}