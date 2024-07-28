package com.example.moviesbaseexample.ui.theme.data.model

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("expires_at")
    val expires: String?=null,
    @SerializedName("request_token")
    val requestToken: String?=null,
    val success: Boolean?=null
)