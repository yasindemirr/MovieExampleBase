package com.example.moviesbaseexample.ui.theme.util

import com.example.moviesbaseexample.ui.theme.data.shared.AccessTokenSharedProperty
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(
    private val accessTokenSharedProperty : AccessTokenSharedProperty
) {

    fun updateAccessToken(accessToken : String?) {

        if (accessToken != null) {

            accessTokenSharedProperty.saveValue(accessToken)
            println(accessToken)

        } else {

            accessTokenSharedProperty.delete()
        }
    }

    fun getToken() : String? = accessTokenSharedProperty.getValue()

    fun hasSession() : Boolean {
        println(getToken())

        return getToken() != null
    }
}