package com.example.moviesbaseexample.ui.theme.domain.usecases.Auth

import com.example.moviesbaseexample.ui.theme.data.repository.Auth.AuthRepositoryImpl
import com.example.moviesbaseexample.ui.theme.domain.repository.Repository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

/*
class AuthUseCase @Inject constructor(
    private val authRepository: AuthRepositoryImpl
){
    operator fun invoke() = flow {
        emit(
            authRepository.getAuth()
        )
    }
}*/
