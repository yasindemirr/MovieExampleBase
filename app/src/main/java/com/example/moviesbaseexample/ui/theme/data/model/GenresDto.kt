package com.example.moviesbaseexample.ui.theme.data.model

import com.example.moviesbaseexample.ui.theme.domain.model.Genre
import com.example.moviesbaseexample.ui.theme.domain.model.Genres

data class GenresDto(
    val genres: List<GenreDto>? = null
)

data class GenreDto(
    val id: Int? = null,
    val name: String? = null
)

fun List<GenreDto>?.toGenre()=this?.map {
    Genre(
        id=it.id,
        name =it.name
    )
}.orEmpty()