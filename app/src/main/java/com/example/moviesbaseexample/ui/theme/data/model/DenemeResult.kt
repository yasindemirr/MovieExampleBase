package com.example.moviesbaseexample.ui.theme.data.model

import com.example.moviesbaseexample.ui.theme.util.dataresource.RepoResult

sealed interface DenemeResult<out D,out E:DenemeError>{
    data class Succes<out D, out E:DenemeError>(val data:D):DenemeResult<D,E>
    data class Error<out D, out E:DenemeError>(val error:E):DenemeResult<D,E>

    suspend fun onSuccess(successHandler: suspend (D) -> Unit): DenemeResult<D,E> = this.also {
        if (this is DenemeResult.Succes) successHandler(data)
    }
    suspend fun onFail(successHandler: suspend (E) -> Unit): DenemeResult<D,E> = this.also {
        if (this is DenemeResult.Error) successHandler(error)
    }
}
