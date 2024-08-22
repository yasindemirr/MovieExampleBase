package com.example.moviesbaseexample.ui.theme.data.model

import com.example.moviesbaseexample.ui.theme.domain.model.SessionId
import com.google.gson.annotations.SerializedName

data class SessionIdDto(
    @SerializedName("session_id")
    val sessionId: String?=null,
    val success: Boolean?=null
)
fun SessionIdDto.toSessionId(): SessionId {
    return SessionId(
        sessionId = sessionId
    )
}