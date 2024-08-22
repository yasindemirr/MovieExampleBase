package com.example.moviesbaseexample.ui.theme.domain.usecases.Auth


import com.example.moviesbaseexample.ui.theme.data.model.toTokenResponse
import com.example.moviesbaseexample.ui.theme.domain.model.TokenResponse
import com.example.moviesbaseexample.ui.theme.domain.repository.Repository
import com.example.moviesbaseexample.ui.theme.util.dataresource.RepoResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class CreateTokenUsaCase @Inject constructor(
    private val authRepository: Repository.AuthRepository
){
    suspend operator fun invoke() : Flow<RepoResult<TokenResponse>> {

        return authRepository.getAuth()

        }
    }

