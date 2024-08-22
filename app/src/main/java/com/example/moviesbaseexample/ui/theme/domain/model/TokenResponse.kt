package com.example.moviesbaseexample.ui.theme.domain.model

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    val expires: String?=null,
    val requestToken: String?=null,
)