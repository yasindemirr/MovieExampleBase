package com.example.moviesbaseexample.ui.theme.datastore

import com.example.moviesbaseexample.ui.theme.data.model.SessionId
import kotlinx.serialization.Serializable

@Serializable
data  class SessionIdStore(
   val sessionId : String?=""
)