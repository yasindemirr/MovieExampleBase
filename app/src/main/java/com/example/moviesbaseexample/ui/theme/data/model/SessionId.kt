package com.example.moviesbaseexample.ui.theme.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class SessionId(
    @SerializedName("session_id")
    val sessionId: String?=null,
    val success: Boolean?=null
)
