package com.example.moviesbaseexample.ui.theme.data.remote

import com.example.moviesbaseexample.BuildConfig
import com.example.moviesbaseexample.ui.theme.data.model.CreateSession
import com.example.moviesbaseexample.ui.theme.data.model.GenresDto
import com.example.moviesbaseexample.ui.theme.data.model.MovieDto
import com.example.moviesbaseexample.ui.theme.data.model.SessionIdDto
import com.example.moviesbaseexample.ui.theme.data.model.TokenResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

sealed interface Services{
    interface Auth{
      private  companion object {
            const val base="authentication"
        }
        @GET("$base/token/new")
        suspend fun getToken(
            @Query("api_key") apiKey: String
        ):Response<TokenResponseDto>
        @POST("$base/token/validate_with_login")
        suspend fun getId(
            @Query("api_key") apiKey: String,
            @Body body : CreateSession?
        ):Response<TokenResponseDto>
        @POST("$base/session/new")
        suspend fun getSession(
            @Query("api_key") apiKey: String,
            @Query("request_token") token: String?
        ):Response<SessionIdDto>
    }
    interface Home{
        @GET("discover/tv")
        suspend fun getMovies(
            @Query("api_key") apiKey: String=BuildConfig.API_KEY,
            @Query("include_adult") includeAdult: Boolean = false,
            @Query("include_null_first_air_dates") includeNullFirstAirDates: Boolean = false,
            @Query("language") language: String = "en-US",
            @Query("page") page: Int = 1,
            @Query("sort_by") sortBy: String = "popularity.desc",
        ): Response<MovieDto>

        @GET("genre/tv/list")
        suspend fun getGenres(
            @Query("api_key") apiKey: String=BuildConfig.API_KEY,
            @Query("language") language: String = "en-US",
        ): Response<GenresDto>
    }

}



