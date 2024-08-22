package com.example.moviesbaseexample.ui.theme.data.model

import com.example.moviesbaseexample.ui.theme.domain.model.Movie
import com.example.moviesbaseexample.ui.theme.util.constants.Constants.IMAGE_URL
import com.google.gson.annotations.SerializedName
import java.util.Locale

data class MovieDto(
    val page: Int? = null,
    val results: List<ResultDto>? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
    @SerializedName("total_results")
    val totalResults: Int? = null,
)

fun List<ResultDto>?.toMovie() = this?.map { dto ->
    Movie(
        id = dto.id,
        name = dto.name.orEmpty(),
        originalLanguage = dto.originalLanguage.orEmpty(),
        originalName = dto.originalName.orEmpty(),
        overview = dto.overview.orEmpty(),
        popularity = dto.popularity,
        backdropPath = IMAGE_URL + dto.backdropPath.orEmpty(),
        firstAirDate = dto.firstAirDate.orEmpty(),
        genreIds = dto.genreIds.orEmpty(),
        originCountry = dto.originCountry.orEmpty(),
        posterPath = getImageUrl(dto.posterPath.orEmpty()),
        voteAverage = getVoteAverage(dto.voteAverage),
        voteCount = dto.voteCount
    )
}.orEmpty()

private fun getVoteAverage(vote:Double?):Float{

    return String.format(Locale.US, "%.1f",((vote?:1.0) / 2)).toFloat()
}

private fun getImageUrl(posterImage: String) = "https://image.tmdb.org/t/p/w500/$posterImage"