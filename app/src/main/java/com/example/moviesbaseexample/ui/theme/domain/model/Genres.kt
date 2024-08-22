package com.example.moviesbaseexample.ui.theme.domain.model
data class Genres(
    val genres: List<Genre>
)

data class Genre(
    val id: Int?,
    val name: String?
)