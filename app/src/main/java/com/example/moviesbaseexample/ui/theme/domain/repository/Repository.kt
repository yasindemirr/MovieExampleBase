package com.example.moviesbaseexample.ui.theme.domain.repository

import com.example.moviesbaseexample.ui.theme.data.model.CreateSession
import com.example.moviesbaseexample.ui.theme.data.model.SessionId
import com.example.moviesbaseexample.ui.theme.data.model.TokenResponse
import com.example.moviesbaseexample.ui.theme.util.dataresource.RepoResult

sealed interface Repository{
    interface AuthRepository{
        suspend fun getAuth():RepoResult<TokenResponse>
        suspend fun getId(model: CreateSession?):RepoResult<TokenResponse>
        suspend fun getSessionId(token: String?):RepoResult<SessionId>
    }
}