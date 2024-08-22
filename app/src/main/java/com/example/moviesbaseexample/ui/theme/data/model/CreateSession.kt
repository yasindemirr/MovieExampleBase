package com.example.moviesbaseexample.ui.theme.data.model

import com.google.gson.annotations.SerializedName

data class CreateSession(
    @SerializedName("username")
    val userName:String,
    val password:String,
    @SerializedName("request_token")
    val requestToken:String?
)

