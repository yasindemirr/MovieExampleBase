package com.example.moviesbaseexample.ui.theme.data.model

import com.example.moviesbaseexample.ui.theme.domain.model.SessionId
import com.example.moviesbaseexample.ui.theme.domain.model.TokenResponse
import com.google.gson.annotations.SerializedName

data class TokenResponseDto(
    @SerializedName("expires_at")
    val expires: String?=null,
    @SerializedName("request_token")
    val requestToken: String?=null,
    val success: Boolean?=null
)

fun TokenResponseDto.toTokenResponse(): TokenResponse {
    return TokenResponse(
        expires = expires,
        requestToken=requestToken
    )
}