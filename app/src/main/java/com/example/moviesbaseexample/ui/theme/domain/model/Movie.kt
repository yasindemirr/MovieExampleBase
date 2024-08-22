package com.example.moviesbaseexample.ui.theme.domain.model

import java.io.Serializable

data class Movie(
    val adult: Boolean? = null,
    val backdropPath: String? = null,
    val firstAirDate: String? = null,
    val genreIds: List<Int>? = null,
    val id: Int? = null,
    val name: String? = null,
    val originCountry: List<String>? = null,
    val originalLanguage: String? = null,
    val originalName: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val posterPath: String? = null,
    val voteAverage: Float? = null,
    val voteCount: Int? = null,
) :Serializable{
}