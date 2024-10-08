package com.example.moviesbaseexample.ui.theme.util.dataresource


sealed class RepoResult<out T> {
    data class Success<out T>(val data: T) : RepoResult<T>()
    data class Error(val error :Exception) : RepoResult<Nothing>()
    object None : RepoResult<Nothing>()

    suspend fun onSuccess(successHandler: suspend (T) -> Unit): RepoResult<T> = this.also {
        if (this is Success) successHandler(this.data)
    }
    suspend fun onError(myHandler:suspend (Exception) -> Unit): RepoResult<T> = this.also {
        if (this is Error) myHandler(error)
    }
    suspend fun onResponed(responsedHandler : suspend ()->Unit):RepoResult<T> =this.also{
        if (this is Success || this is Error) responsedHandler()
    }

    suspend fun <T : Any, N : Any> RepoResult<T>.transformData(data: suspend (T) -> N): RepoResult<N> {
        return when (this) {
            is Success -> {
                try {
                    Success(data(this.data))
                } catch (e: Exception) {
                   Error(e)
                }
            }

            is Error -> Error(this.error)

            else ->  None
        }
    }
}