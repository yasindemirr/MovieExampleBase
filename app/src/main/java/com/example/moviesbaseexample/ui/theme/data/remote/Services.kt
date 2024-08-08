package com.example.moviesbaseexample.ui.theme.data.remote

import com.example.moviesbaseexample.ui.theme.data.model.CreateSession
import com.example.moviesbaseexample.ui.theme.data.model.SessionId
import com.example.moviesbaseexample.ui.theme.data.model.TokenResponse
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
        ):Response<TokenResponse>
        @POST("$base/token/validate_with_login")
        suspend fun getId(
            @Query("api_key") apiKey: String,
            @Body body : CreateSession?
        ):Response<TokenResponse>
        @POST("$base/session/new")
        suspend fun getSession(
            @Query("api_key") apiKey: String,
            @Query("request_token") token: String?
        ):Response<SessionId>
    }

}



