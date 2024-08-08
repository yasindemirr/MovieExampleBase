package com.example.moviesbaseexample.ui.theme.data.service.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ErrorInterceptor @Inject constructor():Interceptor {
    override fun intercept(chain : Interceptor.Chain) : Response {
        val request=chain.request()
        return chain.proceed(request).also {response ->
            if (!response.isSuccessful){
                    ErrorEvent.errorEvent.postValue(convert(response.message,response.code))
            }
        }
    }
    private fun convert(errorMessage:String?,code:Int): ErrorTypes {
       return when(code){
           400 -> ErrorTypes.NetworkErrorTypes.ServerMessageErrorTypes
           401 -> ErrorTypes.NetworkErrorTypes.AuthErrorTypes
           403 -> ErrorTypes.NetworkErrorTypes.MethodNotAllowedErrorTypes
           500 -> ErrorTypes.NetworkErrorTypes.ServerErrorTypes
           503 -> ErrorTypes.NetworkErrorTypes.ServerUnavailableErrorTypes



           else-> ErrorTypes.HoustonWeHaveAProblemErrorTypes()
       }
    }
}