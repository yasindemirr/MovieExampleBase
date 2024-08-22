package com.example.moviesbaseexample.ui.theme.domain.usecases.Auth

import javax.inject.Inject

data class AuthUseCases @Inject constructor(
    val createTokenUsaCase : CreateTokenUsaCase,
    val getIdUseCase : GetIdUseCase,
    val takeSessionIdUseCase : TakeSessionIdUseCase
)
