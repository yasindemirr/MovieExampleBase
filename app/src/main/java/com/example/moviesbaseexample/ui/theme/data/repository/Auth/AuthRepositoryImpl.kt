package com.example.moviesbaseexample.ui.theme.data.repository.Auth

import com.example.moviesbaseexample.BuildConfig
import com.example.moviesbaseexample.ui.theme.data.model.CreateSession
import com.example.moviesbaseexample.ui.theme.data.model.toSessionId
import com.example.moviesbaseexample.ui.theme.data.model.toTokenResponse
import com.example.moviesbaseexample.ui.theme.data.remote.Services
import com.example.moviesbaseexample.ui.theme.data.repository.base.BaseRepository
import com.example.moviesbaseexample.ui.theme.domain.model.SessionId
import com.example.moviesbaseexample.ui.theme.domain.model.TokenResponse
import com.example.moviesbaseexample.ui.theme.domain.repository.Repository
import com.example.moviesbaseexample.ui.theme.util.dataresource.RepoResult
import com.example.moviesbaseexample.ui.theme.util.dataresource.RepoResult.None.transformData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor (
    private val services : Services.Auth
):BaseRepository(),Repository.AuthRepository {
    override suspend fun getAuth() : Flow<RepoResult<TokenResponse>> {

        return flow {

            safeCall {

                services.getToken(BuildConfig.API_KEY)

            }.transformData {

                it.toTokenResponse()
            }.also {
                emit(it)
            }
        }
    }

    override suspend fun getId(model: CreateSession?) :Flow<RepoResult<TokenResponse>>  {
        return flow {
            safeCall {
                services.getId(BuildConfig.API_KEY,model)
            }.transformData {
                it.toTokenResponse()
            }.also {
                emit(it)
            }
        }

    }
    override suspend fun getSessionId(token:String?) :Flow<RepoResult<SessionId>> {
        return flow{
            safeCall {
                services.getSession(BuildConfig.API_KEY, token)
            } .transformData { it.toSessionId() }.also {
                emit(it)
            }
        }
    }
    }


