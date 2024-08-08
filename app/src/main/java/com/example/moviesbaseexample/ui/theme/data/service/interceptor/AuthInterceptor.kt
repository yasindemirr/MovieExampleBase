package com.example.moviesbaseexample.ui.theme.data.service.interceptor

import com.example.moviesbaseexample.BuildConfig
import com.example.moviesbaseexample.ui.theme.util.SessionManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val sessionManager : SessionManager
) : Interceptor {

    override fun intercept(chain : Interceptor.Chain) : Response {

        val requestBuilder = chain.request().newBuilder()

            requestBuilder.apply {
                addHeader("accept", "application/json")
                addHeader("Authorization", "Bearer" +BuildConfig.ACCES_TOKEN)
            }

        return chain.proceed(requestBuilder.build())
    }
}