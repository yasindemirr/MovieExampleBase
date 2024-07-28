package com.example.moviesbaseexample.ui.theme.data.remote

import com.example.moviesbaseexample.ui.theme.data.model.TokenResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

sealed interface Services{
    interface Auth{
        @GET("authentication/token/new")
        suspend fun getToken(
            @Query("api_key") apiKey: String
        ):Response<TokenResponse>
    }
}

