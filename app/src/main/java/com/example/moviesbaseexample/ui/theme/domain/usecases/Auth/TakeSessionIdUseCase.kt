package com.example.moviesbaseexample.ui.theme.domain.usecases.Auth

import com.example.moviesbaseexample.ui.theme.domain.model.SessionId
import com.example.moviesbaseexample.ui.theme.domain.repository.Repository
import com.example.moviesbaseexample.ui.theme.util.dataresource.RepoResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class TakeSessionIdUseCase @Inject constructor(
    private val authRepository: Repository.AuthRepository
){
    suspend operator fun invoke(token:String?) : Flow<RepoResult<SessionId>> {

        return   authRepository.getSessionId(token)

        }
    }

