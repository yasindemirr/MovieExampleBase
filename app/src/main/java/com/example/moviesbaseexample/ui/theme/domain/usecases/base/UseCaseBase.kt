package com.example.moviesbaseexample.ui.theme.domain.usecases.base

import com.example.moviesbaseexample.ui.theme.util.dataresource.RepoResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class BaseUseCase<P, R, D> {
    protected abstract suspend fun execute(param: P): Flow<RepoResult<R>>

    protected abstract fun mapper(response: R): D
    operator fun invoke(req: P): Flow<RepoResult<D>> = flow {
        execute(req).collect {dataResource->
            when (dataResource) {
                is RepoResult.Success -> {
                    emit(RepoResult.Success(mapper(dataResource.data)))
                }

                is RepoResult.Error -> {
                    emit(RepoResult.Error(dataResource.error))
                }

                else -> {}
            }
        }
    }
}