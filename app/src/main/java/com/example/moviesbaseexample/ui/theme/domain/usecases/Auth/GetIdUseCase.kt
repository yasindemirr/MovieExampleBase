package com.example.moviesbaseexample.ui.theme.domain.usecases.Auth

import com.example.moviesbaseexample.ui.theme.data.model.CreateSession
import com.example.moviesbaseexample.ui.theme.domain.model.TokenResponse
import com.example.moviesbaseexample.ui.theme.domain.repository.Repository
import com.example.moviesbaseexample.ui.theme.util.dataresource.RepoResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetIdUseCase @Inject constructor(
    private val authRepository: Repository.AuthRepository
){
    suspend operator fun invoke(model: CreateSession) : Flow<RepoResult<TokenResponse>> {

        return    authRepository.getId(model)

    }
}

