package com.example.moviesbaseexample.ui.theme.data.repository.Auth

import com.example.moviesbaseexample.BuildConfig
import com.example.moviesbaseexample.ui.theme.data.model.CreateSession
import com.example.moviesbaseexample.ui.theme.data.model.SessionId
import com.example.moviesbaseexample.ui.theme.data.model.TokenResponse
import com.example.moviesbaseexample.ui.theme.data.remote.Services
import com.example.moviesbaseexample.ui.theme.data.repository.base.BaseRepository
import com.example.moviesbaseexample.ui.theme.domain.repository.Repository
import com.example.moviesbaseexample.ui.theme.util.dataresource.RepoResult
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor (
    private val services : Services.Auth
):BaseRepository(),Repository.AuthRepository {
    override suspend fun getAuth() : RepoResult<TokenResponse> {
        return safeCall {
                services.getToken(BuildConfig.API_KEY)
            }
        }

    override suspend fun getId(model:CreateSession?) : RepoResult<TokenResponse> {
        return safeCall {
            services.getId(BuildConfig.API_KEY,model)
        }
    }
    override suspend fun getSessionId(token:String?) : RepoResult<SessionId> {
        return safeCall {
            services.getSession(BuildConfig.API_KEY,token)
        }
    }
    }


