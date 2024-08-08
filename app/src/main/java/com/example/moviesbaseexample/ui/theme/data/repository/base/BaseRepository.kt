package com.example.moviesbaseexample.ui.theme.data.repository.base

import com.example.moviesbaseexample.ui.theme.data.service.interceptor.ErrorEvent
import com.example.moviesbaseexample.ui.theme.data.service.interceptor.ErrorTypes
import com.example.moviesbaseexample.ui.theme.util.dataresource.RepoResult
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class BaseRepository {
    suspend fun <T> safeCall(call: suspend () -> Response<T>): RepoResult<T>{
        return withContext(Dispatchers.IO){
            try {
                RepoResult.Success(call().body()!!)
            } catch (e: Exception) {
                if (e !is retrofit2.HttpException){
                    when (e) {
                        is JsonSyntaxException    -> ErrorEvent.errorEvent.postValue(ErrorTypes.ParsingErrorTypes)
                        is SocketTimeoutException -> ErrorEvent.errorEvent.postValue(ErrorTypes.TimeoutErrorTypes)
                        is UnknownHostException   -> ErrorEvent.errorEvent.postValue(ErrorTypes.NoNetworkErrorTypes)
                    }
                }
                RepoResult.Error(e)
            }
        }
    }


}