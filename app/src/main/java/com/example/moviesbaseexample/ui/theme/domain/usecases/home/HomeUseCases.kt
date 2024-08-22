package com.example.moviesbaseexample.ui.theme.domain.usecases.home

import javax.inject.Inject

data class HomeUseCases @Inject constructor(
    val getMovieUseCases:GetMovieUseCase,
    val getGenreUseCases:GetGenreUseCase
) {
}